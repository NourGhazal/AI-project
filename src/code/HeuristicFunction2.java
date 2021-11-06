package code;

import java.util.PriorityQueue;

public class HeuristicFunction2 extends QingFunction{
    @Override
    public void QingFunc(Node cur, PriorityQueue<Node> pq,int maxDepth) {
        if(cur.getDepth()>maxDepth)return;
        int myPriority=-h(cur);
        cur.setPriority(myPriority);
        pq.add(cur);
    }
    private int telephoneBoothx,telephoneBoothy;


    int h(Node cur){//admissable heuristic function -> it never overestimates
        String hostagesInfo=cur.getHostageInfo();
        int ans=0;
        int i=0;
        int neox= cur.getNeoLocationX(),neoy= cur.getNeoLocationY();
        int maxDist=0;
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
                    int distMoved=dist(neox,neoy,x,y)+dist(x,y,telephoneBoothx,telephoneBoothy);//go to hostage then go to booth
                    maxDist= Math.min(maxDist,distMoved);
                    ans+=2;//for the carry and drop
                    break;
                case 3:
                    int distMoved2=dist(neox,neoy,x,y)-1+dist(x,y,telephoneBoothx,telephoneBoothy);//go near to hostage then go to booth
                    maxDist= Math.min(maxDist,distMoved2);
                    ans+=1;//for the kill
                    break;
                case 1:
                case 5:
                    ans++;//for the drop
                    break;

            }
            if(i==hostagesInfo.length())break;
        }
        return ans+maxDist;
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
