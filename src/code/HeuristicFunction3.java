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
                case 1:
                case 5:
                 ans+=1;
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
