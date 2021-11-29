package code;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class HeuristicFunction3 extends QingFunction{
    @Override
    public void QingFunc(Node cur, PriorityQueue<Node> pq, int maxDepth) {

        cur.setPriority(-(cur.getCost()+h3(cur)));
        pq.add(cur);
    }
    private int telephoneBoothx,telephoneBoothy;

    public void setPads(ArrayList<Pair> pads) {
        this.pads = pads;
    }
    public void setTelephoneBoothx(int telephoneBoothx) {
        this.telephoneBoothx = telephoneBoothx;
    }
    public void setTelephoneBoothy(int telephoneBoothy) {
        this.telephoneBoothy = telephoneBoothy;
    }

    private ArrayList<Pair> pads;
    int h3(Node cur){
        String hostagesInfo=cur.getHostageInfo();
        int ans=0;
        int i=0;
        int neox= cur.getNeoLocationX(),neoy= cur.getNeoLocationY();
        int mindis = HeuristicFunction1.dist(neox,neoy,telephoneBoothx,telephoneBoothy);
        while (!pads.isEmpty()){
            Pair padFrom = pads.remove(0);
            Pair padTo = pads.remove(0);
            int paddis = HeuristicFunction1.dist(neox,neoy,padFrom.x,padFrom.y) + HeuristicFunction1.dist(padTo.x,padTo.y,telephoneBoothx,telephoneBoothy)+1;
            mindis = Math.min(mindis,paddis);
        }


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
            switch (hState){
                case 0:
                case 3:
                    ans+=2+HeuristicFunction1.dist(neox,neoy,x,y);
                    break;
                case 1:
                case 5:
                    ans++;
                    break;

            }
            if(i==hostagesInfo.length())break;
        }
        return ans+mindis+cur.agentKilledCnt();
    }
    int h(Node cur){//admissable heuristic function -> it never overestimates
        String hostagesInfo=cur.getHostageInfo();
        int ans=0;
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
            if(hState==0 || hState==1){
                int distNeeded;
                //alive
                ans+=damage;
            }
            if(i==hostagesInfo.length())break;
        }
        return ans;
    }

}
