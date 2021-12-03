package code;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class HeuristicFunction1 extends QingFunction{
    @Override
    public void QingFunc(Node cur, PriorityQueue<Node> pq,int maxDepth) {
        if(cur.getDepth()>maxDepth)return;
        int myPriority=-h(cur);
        cur.setPriority(myPriority);
        pq.add(cur);
    }
    private int telephoneBoothx,telephoneBoothy;

    public void setPads(ArrayList<Pair> pads) {
        this.pads = pads;
    }

    private ArrayList<Pair> pads;

    int h(Node cur){
        String hostagesInfo=cur.getHostageInfo();
        int ans=0;
        int i=0;
        int neox= cur.getNeoLocationX(),neoy= cur.getNeoLocationY();
//        ArrayList<Integer[]> hostages= new ArrayList<>();
//        Integer [] info = {0,0,0};
        boolean flag = false;
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
                    ans+=2;
//                    break;

//                    break;
//                    mindist((ArrayList<Pair>)(pads.clone()),neox,neoy,x,y);
//                    break;
                case 1:
                case 5:
                    ans+=1;
//                    info[0]=x;info[1]=y;info[2]=hState;
//                    hostages.add(info);
                    break;

            }
            if(i==hostagesInfo.length())break;
        }
        return  ans + mindist(pads,neox,neoy,telephoneBoothx,telephoneBoothy);
    }
    int mindist(ArrayList<Pair>pads,int x1,int y1,int x2,int y2){
        int disbooth = HeuristicFunction1.dist(x1,y1,x2,y2);
        int [][] padsdis = new int[pads.size()/4 ][pads.size()/4];
        for (int i =0; i<pads.size()-1;i+=4){
            Pair padFrom1 = pads.get(i);
            Pair padTo1 = pads.get(i+1);
            for(int j=0;j<pads.size()-6;j+=4){
//                if(j==i)
//                    continue;
                Pair padFrom2 = pads.get(j);
                Pair padTo2 = pads.get(j+1);
                int d1 = HeuristicFunction1.dist(padTo1.x,padTo1.y,padFrom2.x,padFrom2.y);
                int d2 = HeuristicFunction1.dist(padTo2.x,padTo2.y,padFrom1.x,padFrom1.y);
                padsdis[i/4][j/2] = Math.min(d1,d2);
            }

        }
        int padboothdis []= new int[pads.size()/2];
        for (int i =0;i<pads.size()-1;i+=2){
            Pair padto =  pads.get(i+1);
            padboothdis[i/2] = HeuristicFunction1.dist(padto.x,padto.y,x1,y1);
        }
        int padneodis []= new int[pads.size()/2];
        for (int i =0;i<pads.size()-1;i+=2){
            Pair padfrom =  pads.get(i);
            padneodis[i/2] = HeuristicFunction1.dist(padfrom.x,padfrom.y,x1,y1);
        }
        int distance = disbooth;
        for(int i =0;i<padneodis.length;i++){
            int neocost = padboothdis[i];
            if(i<padsdis.length){
                for(int k=0 ; k < padsdis[i].length;k++){
                    int cost = neocost + padsdis[i][k] + padboothdis[k];
                    distance= Math.min(cost,distance);
                }
            }
        }
        return distance;

    }
    public  static int  dist(int x1,int y1,int x2,int y2){
        return Math.abs(x1-x2)+Math.abs(y1-y2);
    }
    public void setTelephoneBoothx(int telephoneBoothx) {
        this.telephoneBoothx = telephoneBoothx;
    }
    public void setTelephoneBoothy(int telephoneBoothy) {
        this.telephoneBoothy = telephoneBoothy;
    }
}
