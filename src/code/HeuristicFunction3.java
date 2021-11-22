package code;
import java.util.PriorityQueue;

public class HeuristicFunction3 extends QingFunction{
    @Override
    public void QingFunc(Node cur, PriorityQueue<Node> pq, int maxDepth) {
        cur.setPriority(-cur.getCost());
        pq.add(cur);
    }
    private int telephoneBoothx,telephoneBoothy;
    int h(Node cur){//admissable heuristic function -> it never overestimates
        String hostagesInfo=cur.getHostageInfo();
        int deadHostageEstimate=0,killedAgentsEstimate=0;
        int neox= cur.getNeoLocationX(),neoy= cur.getNeoLocationY();
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
                //if I can't go to the hostage and carry him to booth before he dies after taking all pills then he is dead
                //it never overestimate the death of hostage
                if(hState==0) {
                    distNeeded =dist(x,y,telephoneBoothx,telephoneBoothy)+ dist(neox, neoy, x, y) + 1;//+1 for carry
                }
                else{
                    distNeeded=dist(neox,neoy,telephoneBoothx,telephoneBoothy);
                }
            }
            if(i==hostagesInfo.length())break;
        }
        return deadHostageEstimate*226+killedAgentsEstimate;
    }
    int dist(int x1,int y1,int x2,int y2){
        return Math.abs(x1-x2)+Math.abs(y1-y2);
    }
    public void setTelephoneBoothx(int telephoneBoothx) {
        this.telephoneBoothx = telephoneBoothx;
    }
    public void setTelephoneBoothy(int telephoneBoothy) {
        this.telephoneBoothy = telephoneBoothy;
    }
}
