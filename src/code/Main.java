package code;

import java.util.*;

public class Main {
    static int totNodes;
    static HashSet<String>states;
    public static Node generalSearch(SearchProblem problem,QingFunction qfunc,int maxDepth){
        states=new HashSet<>();
        PriorityQueue<Node>nodes=new PriorityQueue<>((x,y)->y.getPriority()-x.getPriority());
        Node initNode=new Node();
        initNode.setDepth(0);
        initNode.setState(problemStatetoNodeState(problem.getInitialState()));
        states.add(initNode.getState());
        qfunc.QingFunc(initNode,nodes,maxDepth);
        while(true){
            if(nodes.isEmpty())return null;//failure
            Node cur=nodes.poll();
            totNodes++;
            if(problem.goalTest(cur))return cur;
            ArrayList<Node>nxtNodes=problem.Expand(cur);
            for(Node nxt:nxtNodes){
                if(states.contains(nxt.getState()))continue;
                qfunc.QingFunc(nxt,nodes,maxDepth);
                states.add(nxt.getState());
            }
        }
    }
    public static Node greedySearch1(SearchProblem problem,int telephoneBoothX,int telephoneBoothY){
        HeuristicFunction1 func=new HeuristicFunction1();
        func.setTelephoneBoothx(telephoneBoothX);
        func.setTelephoneBoothy(telephoneBoothY);

        return bestFirstSearch(problem,func);
    }
    public static Node greedySearch2(SearchProblem problem,int telephoneBoothX,int telephoneBoothY){
        HeuristicFunction2 func=new HeuristicFunction2();
        func.setTelephoneBoothx(telephoneBoothX);
        func.setTelephoneBoothy(telephoneBoothY);

        return bestFirstSearch(problem,func);
    }
    public static Node bestFirstSearch(SearchProblem problem,QingFunction function){
        return generalSearch(problem,function,Integer.MAX_VALUE);
    }
    public static Node depthLimitedSearch(SearchProblem problem,int depth){
        return generalSearch(problem,new DepthLimitedQingFunction(),depth);
    }
    public static Node IterativeDeepeningSearch(SearchProblem problem){

        totNodes = 0;
        int prevTotNodes=0;
        for(int d=0;;d++){
            Node cur=depthLimitedSearch(problem,d);
            System.out.println("d = " + d);
            System.out.println("Nodes in current depth= "+(totNodes-prevTotNodes));
            prevTotNodes=totNodes;
            if(cur!=null){
                return cur;
            }
        }
    }
    static StringBuilder chosenPath(Node cur){
        if(cur.getParent()==null)return new StringBuilder();
        if(cur.getDepth()==1){
            StringBuilder ans=new StringBuilder();
            ans.append(cur.getOperator().toString());
            System.out.println(cur.getOperator()+" "+cur.getState()+" "+cur.agentKilled(2,1));
            return ans;
        }
        StringBuilder ans=new StringBuilder();
        ans.append(chosenPath(cur.getParent()));
        ans.append(",");
        ans.append(cur.getOperator().toString());
        System.out.println(cur.getOperator()+" "+cur.getState()+" "+cur.agentKilled(2,1));
        return ans;
    }
    public static String genGrid() {
        Random rand= new Random();
        int n = rand.nextInt(11)+5;
        int m = rand.nextInt(11)+5;
        int hostageCount = rand.nextInt(8)+3;
        int c =  rand.nextInt(4)+1;
        int pillCount = rand.nextInt(hostageCount);
        //maximum pad count can be the rest of the grid (devided tow because we generate in pairs)
        int maxPadCount = rand.nextInt(((n*m)-hostageCount-pillCount)/2);
        int padCount = rand.nextInt(maxPadCount)*2;
        //maximimum agent count  is the rest of the grid, didn't include neos place beacuse the upper bound is already excluded
        int agentCount = rand.nextInt((n*m)-hostageCount-pillCount-padCount);
        //Generating Grid
        String grid ="";
        //adding grid dimensions
        grid+=m+","+n+";";
        grid+=c+";";
        List<Pair> gridLlocation=new ArrayList<Pair>();
        int neoX = rand.nextInt(m);
        int neoY = rand.nextInt(n);
        Pair neoLocation = new Pair(neoX,neoY);
        gridLlocation.add(neoLocation);
        //adding Neo Location
        grid+=neoX+","+neoY+";";
        while (true){
            int telephoneX = rand.nextInt(m);
            int telephoneY = rand.nextInt(n);
            Pair teleLocation = new Pair(telephoneX,telephoneY);
            if(!cellTaken(gridLlocation,teleLocation)){
                gridLlocation.add(teleLocation);
                //adding telephone location
                grid+=telephoneX+","+telephoneY+";";
                break;
            }
        }
        for(int i=0 ;i<agentCount;i++){
            while (true){
                int x = rand.nextInt(m);
                int y = rand.nextInt(n);
                Pair location = new Pair(x,y);
                if(!cellTaken(gridLlocation,location)){
                    gridLlocation.add(location);
                    //adding telephone location
                    grid+=x+","+y;
                    break;
                }
            }
            if(i==agentCount-1) {

            }
            else
                grid+=",";
        }
        grid += ";";
        for(int i=0 ;i<pillCount;i++){
            while (true){
                int x = rand.nextInt(m);
                int y = rand.nextInt(n);
                Pair location = new Pair(x,y);
                if(!cellTaken(gridLlocation,location)){
                    gridLlocation.add(location);
                    //adding telephone location
                    grid+=x+","+y;
                    break;
                }
            }
            if(i==pillCount-1) {

            }
            else
                grid+=",";
        }
        grid += ";";
        for(int i=0 ;i<padCount;i++){
            while (true){
                int x = rand.nextInt(m);
                int y = rand.nextInt(n);
                Pair location = new Pair(x,y);
                if(!cellTaken(gridLlocation,location)){
                    gridLlocation.add(location);
                    //adding telephone location
                    grid+=x+","+y;
                    break;
                }
            }
            if(i==padCount-1) {
            }
            else
                grid+=",";
        }
        grid+=";";
        for(int i=0 ;i<hostageCount;i++){
            while (true){
                int x = rand.nextInt(m);
                int y = rand.nextInt(n);
                int damage = rand.nextInt(99)+1;
                Pair location = new Pair(x,y);
                if(!cellTaken(gridLlocation,location)){
                    gridLlocation.add(location);
                    //adding telephone location
                    grid+=x+","+y+","+damage;
                    break;
                }
            }
            if(i==hostageCount-1) {

            }
            else
                grid+=",";
        }
        System.out.println(agentCount);
        System.out.println(pillCount);
        System.out.println(padCount);
        System.out.println(hostageCount);
        System.out.println(grid);
        return grid;

    }

    public static boolean cellTaken(List<Pair> grid,Pair x) {
        for(int i=0; i<grid.size();i++) {
            if(grid.get(i).equals(x))
                return true;
        }
        return false;
    }
    public static String solve(String grid, String strategy, boolean visualize) {
        Node ans=null;
        Matrix problem = new Matrix();
        problem.setInitialState(grid);
        switch (strategy){
            case "ID":
                ans = IterativeDeepeningSearch(problem);
                break;
            case "GR1":
                ans = greedySearch1(problem,problem.getTelephoneBoothX(),problem.getTelephoneBoothY());
                break;
            case "GR2":
                ans = greedySearch2(problem,problem.getTelephoneBoothX(),problem.getTelephoneBoothY());
                break;
        }

        StringBuilder ret=chosenPath(ans);
        ret.append(";");
        ret.append(ans.getDeadHostagesNumber());
        ret.append(";");
        ret.append(ans.getKilledHostagesNumber()+ans.agentKilledCnt());
        ret.append(";");
        ret.append(totNodes);
        return ret.toString();
    }
    public static void main(String[] args) {
        String sample="5,5;2;0,4;1,4;0,1,1,1,2,1,3,1,3,3,3,4;1,0,2,4;0,3,4,3,4,3,0,3;0,0,30,3,0,80,4,4,80";
        System.out.println(solve(sample,"GR2",false));
    }
    public static String problemStatetoNodeState(String problemState){
        StringBuilder ans=new StringBuilder();
        int i=0;
        //skip 2 semi colons
        int cntSemiColons=0;
        while(true){
            if(problemState.charAt(i)==';'){
                cntSemiColons++;
                i++;
                if(cntSemiColons==2)break;
                continue;
            }
            i++;
        }

        //get x,y of Neo
        while(true){
            if(problemState.charAt(i)==';'){
                ans.append(";");
                i++;
                break;
            }
            ans.append(problemState.charAt(i));
            i++;
        }

        //initially no agents killed, so append nothing
        ans.append(";");
        //initiallt damage=0
        ans.append("0;");

        //skip 2 semi colons
        cntSemiColons=0;
        while(true){
            if(problemState.charAt(i)==';'){
                cntSemiColons++;
                i++;
                if(cntSemiColons==2)break;
                continue;
            }
            i++;
        }
        StringBuilder pillsInfo=new StringBuilder();
        //get info of pills
        while(true){
            if(problemState.charAt(i)==';'){
                i++;
                break;
            }
            pillsInfo.append(problemState.charAt(i));
            i++;
        }

        //skip 1 semi colon
        cntSemiColons=0;
        while(true){
            if(problemState.charAt(i)==';'){
                cntSemiColons++;
                i++;
                if(cntSemiColons==1)break;
                continue;
            }
            i++;
        }

        //get hostage info
        while(i<problemState.length()){
            int cntColons=0;
            while(i<problemState.length()){//3 colons for x,y,damage
                if(problemState.charAt(i)==','){
                    ans.append(problemState.charAt(i));
                    cntColons++;
                    i++;
                    if(cntColons==3)break;
                    continue;
                }
                ans.append(problemState.charAt(i));
                i++;
            }
            if(i==problemState.length())ans.append(",");
            ans.append("0");//initial state of hostage is 0
            if(i<problemState.length()){
                ans.append(",");
            }
        }
        ans.append(";");

        //append pills info
        ans.append(pillsInfo);

        return ans.toString();
    }
}
