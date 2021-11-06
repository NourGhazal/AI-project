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


    int h(Node cur){
        String hostagesInfo=cur.getHostageInfo();
        int ans=0;
        int i=0;
        int neox= cur.getNeoLocationX(),neoy= cur.getNeoLocationY();
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
                    ans+=dist(neox,neoy,x,y)+dist(x,y,telephoneBoothx,telephoneBoothy)+1;
                    break;
                case 3:
                    ans+=dist(neox,neoy,x,y)+dist(x,y,telephoneBoothx,telephoneBoothy);
                    break;
                case 1:
                case 5:
                    ans++;
                    break;

            }
            if(i==hostagesInfo.length())break;
        }
        return ans+dist(neox, neoy, telephoneBoothx,telephoneBoothy);
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
