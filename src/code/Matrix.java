package code;

import java.util.*;

;
class Pair{
	public int  x;
	public int y;
	public Pair(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public boolean equals(Pair p) {
		return this.x==p.x && this.y == p.y;
	}
}

public class Matrix extends SearchProblem {
	public boolean goalTest(Node cur){
		String hostagesInfo=cur.getHostageInfo();
		int i=0;
		while(true){
			int x=0,y=0,damage=0,hState=0;
			while(true){
				if(hostagesInfo.charAt(i)==','){
					i++;
					break;
				}
				x*=10;x+=hostagesInfo.charAt(i)-'0';
				i++;
			}
			while(true){
				if(hostagesInfo.charAt(i)==','){
					i++;
					break;
				}
				y*=10;y+=hostagesInfo.charAt(i)-'0';
				i++;
			}
			while(true){
				if(hostagesInfo.charAt(i)==','){
					i++;
					break;
				}
				damage*=10;damage+=hostagesInfo.charAt(i)-'0';i++;
			}
			while(true){
				if(i==hostagesInfo.length() || hostagesInfo.charAt(i)==','){
					if(i<hostagesInfo.length())i++;
					break;
				}
				hState*=10;hState+=hostagesInfo.charAt(i)-'0';i++;
			}
			if(hState!=2 && hState!=4 && hState!=6)return false;
			if(i==hostagesInfo.length())break;
		}
		return isTelephoneBooth(cur.getNeoLocationX(),cur.getNeoLocationY()) && cur.getNeoDamage()<100;
	}
	public int pathCost(Node curr, String action){

		return 0;
	}
	public boolean isTelephoneBooth(int x,int y){
		String initState=getInitialState();
		int i=0;
		//skip 3 semi colons
		int cntSemiColons=0;
		while(true){
			if(initState.charAt(i)==';'){
				cntSemiColons++;
				i++;
				if(cntSemiColons==3)break;
				continue;
			}
			i++;
		}
		int curx=0,cury=0;
		for(;i<initState.length();i++){
			if(initState.charAt(i)==','){
				i++;break;
			}
			curx*=10;
			curx+=(initState.charAt(i)-'0');
		}
		if(curx!=x)return false;
		for(;i<initState.length();i++){
			if(initState.charAt(i)==';'){
				break;
			}
			cury*=10;
			cury+=(initState.charAt(i)-'0');
		}
		return cury==y;
	}
	public int getTelephoneBoothX(){
		String initState=getInitialState();
		int i=0;
		//skip 3 semi colons
		int cntSemiColons=0;
		while(true){
			if(initState.charAt(i)==';'){
				cntSemiColons++;
				i++;
				if(cntSemiColons==3)break;
				continue;
			}
			i++;
		}
		int curx=0,cury=0;
		for(;i<initState.length();i++){
			if(initState.charAt(i)==','){
				i++;break;
			}
			curx*=10;
			curx+=(initState.charAt(i)-'0');
		}
		return curx;
	}
	public int getTelephoneBoothY(){
		String initState=getInitialState();
		int i=0;
		//skip 3 semi colons
		int cntSemiColons=0;
		while(true){
			if(initState.charAt(i)==';'){
				cntSemiColons++;
				i++;
				if(cntSemiColons==3)break;
				continue;
			}
			i++;
		}
		int curx=0,cury=0;
		for(;i<initState.length();i++){
			if(initState.charAt(i)==','){
				i++;break;
			}
			curx*=10;
			curx+=(initState.charAt(i)-'0');
		}
		for(;i<initState.length();i++){
			if(initState.charAt(i)==';'){
				break;
			}
			cury*=10;
			cury+=(initState.charAt(i)-'0');
		}
		return cury;
	}
	public int getM(){
		int ans=0;

		for(int i=0;i<getInitialState().length();i++){
			if(getInitialState().charAt(i)==',')break;
			ans*=10;
			ans+=(getInitialState().charAt(i)-'0');
		}
		return ans;
	}
	public int getN(){
		int i=0;
		while(true){
			if(getInitialState().charAt(i)==','){
				i++;
				break;
			}
			i++;
		}
		int ans=0;
		for(;i<getInitialState().length();i++){
			if(getInitialState().charAt(i)==';')break;
			ans*=10;
			ans+=(getInitialState().charAt(i)-'0');
		}
		return ans;
	}
	public int getC(){
		int i=0;
		while(true){
			if(getInitialState().charAt(i)==';'){
				i++;
				break;
			}
			i++;
		}
		int ans=0;
		for(;i<getInitialState().length();i++){
			if(getInitialState().charAt(i)==';')break;
			ans*=10;
			ans+=(getInitialState().charAt(i)-'0');
		}
		return ans;
	}
	public Pair flyFrom(int fromx,int fromy){
		String initState=getInitialState();
		int i=0;
		//skip 6 semi colons
		int cntSemiColons=0;
		while(true){
			if(initState.charAt(i)==';'){
				cntSemiColons++;
				i++;
				if(cntSemiColons==6)break;
				continue;
			}
			i++;
		}
		for(;i<initState.length();){
			if(initState.charAt(i)==';'){
				break;
			}
			int x=0,y=0,tox=0,toy=0;
			while(true){
				if(initState.charAt(i)==','){
					i++;
					break;
				}
				x*=10;x+=initState.charAt(i)-'0';
				i++;
			}
			while(true){
				if(initState.charAt(i)==','){
					i++;
					break;
				}
				y*=10;y+=initState.charAt(i)-'0';
				i++;
			}
			while(true){
				if(initState.charAt(i)==','){
					i++;
					break;
				}
				tox*=10;tox+=initState.charAt(i)-'0';
				i++;
			}
			while(true){
				if(i==initState.length() || initState.charAt(i)==',' || initState.charAt(i)==';'){
					if(initState.charAt(i)==',')i++;
					break;
				}
				toy*=10;toy+=initState.charAt(i)-'0';
				i++;
			}
			if(x==fromx && y==fromy)return new Pair(tox,toy);
		}
		return null;
	}
	public boolean agentExist(int x,int y){
		String initState=getInitialState();
		int i=0;
		//skip 4 semi colons
		int cntSemiColons=0;
		while(true){
			if(initState.charAt(i)==';'){
				cntSemiColons++;
				i++;
				if(cntSemiColons==4)break;
				continue;
			}
			i++;
		}
		int curx=0,cury=0;
		boolean readx=true;
		for(;i<initState.length();i++){
			if(initState.charAt(i)==';'){
				if(!readx && curx==x && cury==y)return true;
				break;
			}
			if(initState.charAt(i)==','){
				if(readx){
					readx=false;
					continue;
				}
				if(curx==x && cury==y)return true;
				curx=0;cury=0;
				readx=true;
				continue;
			}
			if(readx) {
				curx *= 10;
				curx += (initState.charAt(i) - '0');
			}
			else{
				cury *= 10;
				cury += (initState.charAt(i) - '0');
			}
		}
		return false;
	}
	static boolean neighbour(int x1,int y1,int x2,int y2){
		boolean sameRow=x1==x2 && Math.abs(y1-y2)==1;
		boolean sameCol=y1==y2 && Math.abs(x1-x2)==1;
		return sameCol || sameRow;
	}
	public String updateHostages(String hostagesInfo,boolean carry,int curx,int cury,boolean drop,boolean pillTaken,int killx,int killy){
		StringBuilder ans=new StringBuilder();

		int i=0;
		while(true){
			int x=0,y=0,damage=0,hState=0;
			while(true){
				if(hostagesInfo.charAt(i)==','){
					i++;
					break;
				}
				x*=10;x+=hostagesInfo.charAt(i)-'0';i++;
			}
			while(true){
				if(hostagesInfo.charAt(i)==','){
					i++;
					break;
				}
				y*=10;y+=hostagesInfo.charAt(i)-'0';i++;
			}
			while(true){
				if(hostagesInfo.charAt(i)==','){
					i++;
					break;
				}
				damage*=10;damage+=hostagesInfo.charAt(i)-'0';i++;
			}
			while(true){
				if(i==hostagesInfo.length() || hostagesInfo.charAt(i)==','){
					if(i!=hostagesInfo.length())i++;
					break;
				}
				hState*=10;hState+=hostagesInfo.charAt(i)-'0';i++;
			}
			if(neighbour(x,y,killx,killy) && hState==3){
				hState=4;
			}
			if(carry && curx==x && cury==y){
				hState=1;
			}
			if(drop && (hState==1 || hState==5)){
				if(hState==1) {
					hState = 2;
				}
				else{
					//hstate==5
					hState=6;
				}
			}
			if(hState<2){
				if(pillTaken){
					damage=Math.max(0,damage-20);
				}
				else {
					damage += 2;
				}
				if(damage>=100){
					if(hState==0){
						hState=3;
					}
					else{
						if(hState==1)hState=5;
					}
				}
			}
			ans.append(x+","+y+","+damage+","+hState);
			if(i==hostagesInfo.length())break;
			ans.append(",");
		}

		return ans.toString();
	}
	public boolean cellHasAgent(Node cur,int x,int y,boolean afterOneAction){
		return (agentExist(x,y) && !cur.agentKilled(x,y)) || cur.getHostageState(x,y,afterOneAction)==3;
	}
	public Node moveUp(Node cur,String agents,int neoDamage,String hostageInfo,String pillsInfo,int neoX,int neoY){
		int x=neoX,y=neoY;
		if(x==0){//can not move up
			return null;
		}
		if(cellHasAgent(cur,x-1,y,true)) {
			return null;
		}
		StringBuilder sb=new StringBuilder();
		sb.append((x-1)+","+y+";");
		sb.append(agents+";");
		sb.append(neoDamage+";");
		sb.append(updateHostages(hostageInfo,false,x,y,false,false,-1,-1)+";");
		sb.append(pillsInfo);
		Node ans=new Node();
		ans.setDepth(cur.getDepth()+1);
		ans.setState(sb.toString());
		ans.setCost(0);//TODO <--------------
		ans.setParent(cur);
		ans.setOperator(NeoOperator.up);
		return ans;
	}
	public Node moveDown(Node cur,String agents,int neoDamage,String hostageInfo,String pillsInfo,int neoX,int neoY){
		int x=neoX,y=neoY;
		if(x+1==getM()){//can not move down
			return null;
		}
		if(cellHasAgent(cur,x+1,y,true)) {
			return null;
		}
		StringBuilder sb=new StringBuilder();
		sb.append((x+1)+","+y+";");
		sb.append(agents+";");
		sb.append(neoDamage+";");
		sb.append(updateHostages(hostageInfo,false,x,y,false,false,-1,-1)+";");
		sb.append(pillsInfo);
		Node ans=new Node();
		ans.setDepth(cur.getDepth()+1);
		ans.setState(sb.toString());
		ans.setCost(0);//TODO <--------------
		ans.setParent(cur);
		ans.setOperator(NeoOperator.down);
		return ans;
	}
	public Node moveLeft(Node cur,String agents,int neoDamage,String hostageInfo,String pillsInfo,int neoX,int neoY){
		int x=neoX,y=neoY;
		if(y==0){//can not move Left
			return null;
		}
		if(cellHasAgent(cur,x,y-1,true)) {
			return null;
		}
		StringBuilder sb=new StringBuilder();
		sb.append((x)+","+(y-1)+";");
		sb.append(agents+";");
		sb.append(neoDamage+";");
		sb.append(updateHostages(hostageInfo,false,x,y,false,false,-1,-1)+";");
		sb.append(pillsInfo);
		Node ans=new Node();
		ans.setDepth(cur.getDepth()+1);
		ans.setState(sb.toString());
		ans.setCost(0);//TODO <--------------
		ans.setParent(cur);
		ans.setOperator(NeoOperator.left);
		return ans;
	}
	public Node moveRight(Node cur,String agents,int neoDamage,String hostageInfo,String pillsInfo,int neoX,int neoY){
		int x=neoX,y=neoY;
		if(y+1==getN()){//can not move right
			return null;
		}
		if(cellHasAgent(cur,x,y+1,true)) {
			return null;
		}
		StringBuilder sb=new StringBuilder();
		sb.append((x)+","+(y+1)+";");
		sb.append(agents+";");
		sb.append(neoDamage+";");
		sb.append(updateHostages(hostageInfo,false,x,y,false,false,-1,-1)+";");
		sb.append(pillsInfo);
		Node ans=new Node();
		ans.setDepth(cur.getDepth()+1);
		ans.setState(sb.toString());
		ans.setCost(0);//TODO <--------------
		ans.setParent(cur);
		ans.setOperator(NeoOperator.right);
		return ans;
	}
	public Node carry(Node cur,String agents,int neoDamage,String hostageInfo,String pillsInfo,int neoX,int neoY){
		int x=neoX,y=neoY;
		if(cur.getHostageState(x,y,false)!=0){//we can only carry hostages that are still hostage and not carried
			return null;
		}
		if(cur.getCarriedHostageCnt()>=getC()){//we can only carry if there is capacity
			return null;
		}
		StringBuilder sb=new StringBuilder();
		sb.append((x)+","+(y)+";");
		sb.append(agents+";");
		sb.append(neoDamage+";");
		sb.append(updateHostages(hostageInfo,true,x,y,false,false,-1,-1)+";");
		sb.append(pillsInfo);
		Node ans=new Node();
		ans.setDepth(cur.getDepth()+1);
		ans.setState(sb.toString());
		ans.setCost(0);//TODO <--------------
		ans.setParent(cur);
		ans.setOperator(NeoOperator.carry);
		return ans;
	}
	public Node drop(Node cur,String agents,int neoDamage,String hostageInfo,String pillsInfo,int neoX,int neoY){
		int x=neoX,y=neoY;
		if(!isTelephoneBooth(x,y) || cur.getCarriedHostageCnt()==0){//we can only drop hostages if neo at booth and neo is carrying hostage
			return null;
		}
		StringBuilder sb=new StringBuilder();
		sb.append((x)+","+(y)+";");
		sb.append(agents+";");
		sb.append(neoDamage+";");
		sb.append(updateHostages(hostageInfo,false,x,y,true,false,-1,-1)+";");
		sb.append(pillsInfo);
		Node ans=new Node();
		ans.setDepth(cur.getDepth()+1);
		ans.setState(sb.toString());
		ans.setCost(0);//TODO <--------------
		ans.setParent(cur);
		ans.setOperator(NeoOperator.drop);
		return ans;
	}
	public Node fly(Node cur,String agents,int neoDamage,String hostageInfo,String pillsInfo,int neoX,int neoY){
		int x=neoX,y=neoY;
		Pair to=flyFrom(x,y);
		if(to==null){//we can only fly if iy exists in the grid in x,y
			return null;
		}
		StringBuilder sb=new StringBuilder();
		sb.append((to.x)+","+(to.y)+";");
		sb.append(agents+";");
		sb.append(neoDamage+";");
		sb.append(updateHostages(hostageInfo,false,x,y,false,false,-1,-1)+";");
		sb.append(pillsInfo);
		Node ans=new Node();
		ans.setDepth(cur.getDepth()+1);
		ans.setState(sb.toString());
		ans.setCost(0);//TODO <--------------
		ans.setParent(cur);
		ans.setOperator(NeoOperator.fly);
		return ans;
	}
	public Node takePill(Node cur,String agents,int neoDamage,String hostageInfo,String pillsInfo,int neoX,int neoY){
		int x=neoX,y=neoY;

		if(!cur.pillExist(x,y)){//we can only take pill if it exists in the grid in x,y
			return null;
		}
		StringBuilder sb=new StringBuilder();
		sb.append((x)+","+(y)+";");
		sb.append(agents+";");
		sb.append(Math.max(0,neoDamage-20)+";");
		sb.append(updateHostages(hostageInfo,false,x,y,false,true,-1,-1)+";");
		sb.append(cur.getPillsWithout(x,y));
		Node ans=new Node();
		ans.setDepth(cur.getDepth()+1);
		ans.setState(sb.toString());
		ans.setCost(0);//TODO <--------------
		ans.setParent(cur);
		ans.setOperator(NeoOperator.takePill);
		return ans;
	}
	public Node kill(Node cur,String agents,int neoDamage,String hostageInfo,String pillsInfo,int neoX,int neoY
			,boolean agentLeft,boolean agentRight,boolean agentUp,boolean agentDown){
		int x=neoX,y=neoY;

		if((x==0 || !agentUp) && (x+1==getM() || !agentDown)
				&& (y==0 || !agentLeft) && (y+1==getN() || !agentRight)){
			//no neighbouring agent to be killed
			return null;
		}
		if(cur.getHostageState(x,y,true)==3){//if there is a hostage at x,y, and will be dead after one action, then this move is not valid
			return null;
		}
		StringBuilder sb=new StringBuilder();
		sb.append((x)+","+(y)+";");
		String prevAgents=agents;
		sb.append(prevAgents);
		boolean empty=prevAgents.length()==0;
		if(agentExist(x-1,y) && !cur.agentKilled(x-1,y)){
			if(!empty)sb.append(",");
			sb.append((x-1)+","+(y));
			empty=false;
		}
		if(agentExist(x+1,y) && !cur.agentKilled(x+1,y)){
			if(!empty)sb.append(",");
			sb.append((x+1)+","+(y));
			empty=false;
		}
		if(agentExist(x,y-1) && !cur.agentKilled(x,y-1)){
			if(!empty)sb.append(",");
			sb.append((x)+","+(y-1));
			empty=false;
		}
		if(agentExist(x,y+1) && !cur.agentKilled(x,y+1)){
			if(!empty)sb.append(",");
			sb.append((x)+","+(y+1));
		}
		sb.append(";");
		sb.append(Math.min(100,neoDamage+20)+";");
		sb.append(updateHostages(hostageInfo,false,x,y,false,false,x,y)+";");
		sb.append(pillsInfo);
		Node ans=new Node();
		ans.setDepth(cur.getDepth()+1);
		ans.setState(sb.toString());
		ans.setCost(0);//TODO <--------------
		ans.setParent(cur);
		ans.setOperator(NeoOperator.kill);
		return ans;
	}

	public ArrayList<Node> Expand(Node cur){
		ArrayList<Node>ans=new ArrayList<>();
		int x=cur.getNeoLocationX(),y=cur.getNeoLocationY();
		String hostageInfo=cur.getHostageInfo();
		boolean agentUp=cellHasAgent(cur,x-1,y,false),agentDown=cellHasAgent(cur,x+1,y,false);
		boolean agentLeft=cellHasAgent(cur,x,y-1,false),agentRight=cellHasAgent(cur,x,y+1,false);
		String agents=cur.getAgents();
		int neoDamage=cur.getNeoDamage();
		String pillsInfo=cur.getPills();

		if(neoDamage>=100)return ans;
		Node nxt;
		if((nxt=carry(cur,agents,neoDamage,hostageInfo,pillsInfo,x,y))!=null){
			ans.add(nxt);
		}
		if((nxt=drop(cur,agents,neoDamage,hostageInfo,pillsInfo,x,y))!=null){
			ans.add(nxt);
		}
		if(!agentLeft && (nxt=moveLeft(cur,agents,neoDamage,hostageInfo,pillsInfo,x,y))!=null){
			ans.add(nxt);
		}
		if(!agentRight && (nxt=moveRight(cur,agents,neoDamage,hostageInfo,pillsInfo,x,y))!=null){
			ans.add(nxt);
		}
		if(!agentUp && (nxt=moveUp(cur,agents,neoDamage,hostageInfo,pillsInfo,x,y))!=null){
			ans.add(nxt);
		}
		if(!agentDown && (nxt=moveDown(cur,agents,neoDamage,hostageInfo,pillsInfo,x,y))!=null){
			ans.add(nxt);
		}
		if((nxt=kill(cur,agents,neoDamage,hostageInfo,pillsInfo,x,y,agentLeft,agentRight,agentUp,agentDown))!=null){
			ans.add(nxt);
		}
		if((nxt=takePill(cur,agents,neoDamage,hostageInfo,pillsInfo,x,y))!=null){
			ans.add(nxt);
		}
		if((nxt=fly(cur,agents,neoDamage,hostageInfo,pillsInfo,x,y))!=null){
			ans.add(nxt);
		}



		return ans;
	}


	//added
	static int totNodes;
	static HashSet<String> states;
	public static Node generalSearch(SearchProblem problem,QingFunction qfunc,int maxDepth){
		states=new HashSet<>();
		PriorityQueue<Node> nodes=new PriorityQueue<>((x, y)->y.getPriority()-x.getPriority());
		Node initNode=new Node();
		initNode.setDepth(0);
		initNode.setState(problemStatetoNodeState(problem.getInitialState()));
		initNode.setCost(0);
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
	public static Node DepthFirstSearch(SearchProblem problem,int depth){
		return generalSearch(problem,new DepthFirstQingFunction(),depth);
	}
	public static Node BreadthFirstSearch(SearchProblem problem,int depth){
		return generalSearch(problem,new BreadthFirstQingFunction(),depth);
	}
	public static Node IterativeDeepeningSearch(SearchProblem problem){

		totNodes = 0;
//		int prevTotNodes=0;
		for(int d=0;;d++){
			Node cur=depthLimitedSearch(problem,d);
//			System.out.println("d = " + d);
//			System.out.println("Nodes in current depth= "+(totNodes-prevTotNodes));
//			prevTotNodes=totNodes;
			if(cur!=null){
				System.out.println("Nodes = "+(totNodes));
				return cur;
			}
		}
	}
	public static Node DebthFirst(SearchProblem problem){
		totNodes = 0;
		Node cur=DepthFirstSearch(problem,Integer.MAX_VALUE);
		System.out.println("Nodes = "+(totNodes));
		return cur;
	}
	public static Node BreadthFirst(SearchProblem problem){
		totNodes = 0;
		Node cur=BreadthFirstSearch(problem,Integer.MAX_VALUE);
		System.out.println("Nodes = "+(totNodes));
		return cur;
	}
	public static Node UniformCostSearch(SearchProblem problem){
		totNodes = 0;
		Node cur=generalSearch(problem,new UCSQingFunction(),Integer.MAX_VALUE);
		System.out.println("Nodes = "+(totNodes));
		return cur;
	}
	StringBuilder chosenPath(Node cur,boolean visualize){
		if(cur.getParent()==null)return new StringBuilder();
		if(cur.getDepth()==1){
			if(visualize) {
				visualize(cur.getParent());
				visualize(cur);
			}
			StringBuilder ans=new StringBuilder();
			ans.append(cur.getOperator().toString());
			return ans;
		}
		StringBuilder ans=new StringBuilder();
		ans.append(chosenPath(cur.getParent(),visualize));
		ans.append(",");
		ans.append(cur.getOperator().toString());
		if(visualize)
			visualize(cur);
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
	public static String solve(String grid, String strategy, boolean visualize){
		Matrix problem = new Matrix();
		return problem.solve2(grid,strategy,visualize,problem);
	}
	public String solve2(String grid, String strategy, boolean visualize,Matrix problem) {
		Node ans=null;

		problem.setInitialState(grid);
		switch (strategy){
			case "BF":
				ans = BreadthFirst(problem);
				break;
			case "DF":
				ans = DebthFirst(problem);
				break;
			case "ID":
				ans = IterativeDeepeningSearch(problem);
				break;
			case "GR1":
				ans = greedySearch1(problem,problem.getTelephoneBoothX(),problem.getTelephoneBoothY());
				break;
			case "GR2":
				ans = greedySearch2(problem,problem.getTelephoneBoothX(),problem.getTelephoneBoothY());
				break;
			case "UC":
				ans = UniformCostSearch(problem);
				break;
		}
		if(ans==null){
			return "No Solution";
		}
		StringBuilder ret=chosenPath(ans,visualize);
		ret.append(";");
		ret.append(ans.getDeadHostagesNumber());
		ret.append(";");
		ret.append(ans.getKilledHostagesNumber()+ans.agentKilledCnt());
		ret.append(";");
		ret.append(totNodes);
		return ret.toString();
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
	public void visualize(Node cur){
		String grid=getInitialState();
		int i=0;
		int m=0,n=0,c=0;
		while(true){
			if(grid.charAt(i)==','){
				i++;
				break;
			}
			m*=10;
			m+=grid.charAt(i)-'0';
			i++;
		}
		while(true){
			if(grid.charAt(i)==';'){
				i++;
				break;
			}
			n*=10;
			n+=grid.charAt(i)-'0';
			i++;
		}
		while(true){
			if(grid.charAt(i)==';'){
				i++;
				break;
			}
			c*=10;
			c+=grid.charAt(i)-'0';
			i++;
		}
		int neox=cur.getNeoLocationX(),neoy=cur.getNeoLocationY();
		while(true){
			if(grid.charAt(i)==','){
				i++;
				break;
			}
			i++;
		}
		while(true){
			if(grid.charAt(i)==';'){
				i++;
				break;
			}
			i++;
		}
		int tx=0,ty=0;
		while(true){
			if(grid.charAt(i)==','){
				i++;
				break;
			}
			tx*=10;
			tx+=grid.charAt(i)-'0';
			i++;
		}
		while(true){
			if(grid.charAt(i)==';'){
				i++;
				break;
			}
			ty*=10;
			ty+=grid.charAt(i)-'0';
			i++;
		}
		ArrayList<Integer>agents=new ArrayList<>();
		if(grid.charAt(i)!=';') {
			agents.add(0);
			while (true) {
				if (grid.charAt(i) == ';') {
					i++;
					break;
				}
				if(grid.charAt(i)==','){
					agents.add(0);
					i++;
					continue;
				}
				int len= agents.size();
				agents.set(len-1,agents.get(len-1)*10+(grid.charAt(i)-'0'));
				i++;
			}
		}
		ArrayList<Integer>pills=new ArrayList<>();
		if(grid.charAt(i)!=';') {
			pills.add(0);
			while (true) {
				if (grid.charAt(i) == ';') {
					i++;
					break;
				}
				if(grid.charAt(i)==','){
					pills.add(0);
					i++;
					continue;
				}
				int len= pills.size();
				pills.set(len-1,pills.get(len-1)*10+(grid.charAt(i)-'0'));
				i++;
			}
		}
		ArrayList<Integer>pad=new ArrayList<>();
		if(grid.charAt(i)!=';') {
			pad.add(0);
			while (true) {
				if (grid.charAt(i) == ';') {
					i++;
					break;
				}
				if(grid.charAt(i)==','){
					pad.add(0);
					i++;
					continue;
				}
				int len= pad.size();
				pad.set(len-1,pad.get(len-1)*10+(grid.charAt(i)-'0'));
				i++;
			}
		}
		ArrayList<Integer>hos=new ArrayList<>();
		String hostageInfo= cur.getHostageInfo();
		i=0;
		if(hostageInfo.length()>0) {
			hos.add(0);
			while (i<hostageInfo.length()) {
				if (hostageInfo.charAt(i) == ';') {
					i++;
					break;
				}
				if(hostageInfo.charAt(i)==','){
					hos.add(0);
					i++;
					continue;
				}
				int len= hos.size();
				hos.set(len-1,hos.get(len-1)*10+(hostageInfo.charAt(i)-'0'));
				i++;
			}
		}
		String[][]ans=new String[m][n];

		ans[tx][ty]="TB";
		for(int j=0;j<agents.size();j+=2){
			if(!cur.agentKilled(agents.get(j),agents.get(j+1)))
				ans[agents.get(j)][agents.get(j+1)]="A";
		}
		for(int j=0;j<pills.size();j+=2){
			if(cur.pillExist(pills.get(j), pills.get(j+1)))
				ans[pills.get(j)][pills.get(j+1)]="P";
		}
		for(int j=0;j<pad.size();j+=4){
			ans[pad.get(j)][pad.get(j+1)]="PAD"+((j)/4);
			ans[pad.get(j+2)][pad.get(j+3)]="PAD"+((j)/4);
		}
		for(int j=0;j<hos.size();j+=4){
			int state=hos.get(j+3);
			if(state==0 || state==3)
				ans[hos.get(j)][hos.get(j+1)]="H"+Math.min(hos.get(j+2),100);
		}
		System.out.println("__________________________________________________________________________________________");
		System.out.println("Carried hostage = "+cur.getCarriedHostageCnt()+" / "+c);
		System.out.println("Dead Hostages Number = " + cur.getDeadHostagesNumber());
		System.out.println("agent Killed Cnt = " + cur.agentKilledCnt());
		System.out.println("Killed Hostages Number = " + cur.getKilledHostagesNumber());
		System.out.println("Neo Damage = " + cur.getNeoDamage());
		if(cur.getOperator()!=null){
			System.out.println("Last action was : "+cur.getOperator());
		}
		if(ans[neox][neoy]==null)
			ans[neox][neoy]="Neo";
		else{
			ans[neox][neoy]+="+Neo";
		}
		for(int o=0;o<m;o++){
			System.out.print("| ");
			for(int oo=0;oo<n;oo++){
				if(ans[o][oo]==null)ans[o][oo]="";
				print(ans[o][oo]);
				System.out.print(" | ");
			}
			System.out.println();
		}

	}
	static void print(String s){
		while(s.length()<10)s+=" ";
		System.out.print(s);
	}
}


