package code;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class HeuristicFunction2 extends QingFunction{
    @Override
    public void QingFunc(Node cur, PriorityQueue<Node> pq,int maxDepth) {
        if(cur.getDepth()>maxDepth)return;
        int myPriority=-h(cur);
        cur.setPriority(myPriority);
        pq.add(cur);
    }


    int h(Node cur){//admissable heuristic function -> it never overestimates

		int i=0;
		String hostagesInfo=cur.getHostageInfo();
		int neox= cur.getNeoLocationX(),neoy= cur.getNeoLocationY();
		int ans=-1;
		boolean flag=true;
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
			if(hState == 3 ){
				if(ans==-1)
					ans=mindist(pads,neox,neoy,x,y);
				else
					ans=Math.min(mindist(pads,neox,neoy,x,y),ans);
				flag=false;
			}
			if(i==hostagesInfo.length())break;
		}
		if(flag)
			ans=0;

		return ans;
//        String hostagesInfo=cur.getHostageInfo();
//        int ans=0;
//        int i=0;
//        int neox= cur.getNeoLocationX(),neoy= cur.getNeoLocationY();
//        int maxDist=0;
//        while(true){
//            int x=0,y=0,damage=0,hState=0;
//            while(true){
//                if(hostagesInfo.charAt(i)==','){
//                    i++;
//                    break;
//                }
//                x*=10;x+=hostagesInfo.charAt(i)-'0';
//                i++;
//            }
//            while(true){
//                if(hostagesInfo.charAt(i)==','){
//                    i++;
//                    break;
//                }
//                y*=10;y+=hostagesInfo.charAt(i)-'0';
//                i++;
//            }
//            while(true){
//                if(hostagesInfo.charAt(i)==','){
//                    i++;
//                    break;
//                }
//                damage*=10;damage+=hostagesInfo.charAt(i)-'0';i++;
//            }
//            while(true){
//                if(i==hostagesInfo.length() || hostagesInfo.charAt(i)==','){
//                    if(i<hostagesInfo.length())i++;
//                    break;
//                }
//                hState*=10;hState+=hostagesInfo.charAt(i)-'0';i++;
//            }
//            switch (hState){
//                case 0:
//                    int distMoved=dist(neox,neoy,x,y)+dist(x,y,telephoneBoothx,telephoneBoothy);//go to hostage then go to booth
//                    maxDist= Math.min(maxDist,distMoved);
//                    ans+=2;//for the carry and drop
//                    break;
//                case 3:
//                    int distMoved2=dist(neox,neoy,x,y)-1+dist(x,y,telephoneBoothx,telephoneBoothy);//go near to hostage then go to booth
//                    maxDist= Math.min(maxDist,distMoved2);
//                    ans+=1;//for the kill
//                    break;
//                case 1:
//                case 5:
//                    ans++;//for the drop
//                    break;
//
//            }
//            if(i==hostagesInfo.length())break;
//        }
//        return ans+maxDist;
    }
	int mindist(ArrayList<Pair> pads, int x1, int y1, int x2, int y2){
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
//
//        for (int i =0;i<padsdis.length;i++){
//            Pair ourpadfrom1 = pads.get(i*4);
//            Pair ourpadTo2 = pads.get(i*4+3);
//
//        }
//        if(pads.size()>4)
//        System.out.println(padsdis);
//        return 0;
//        int mindis = HeuristicFunction1.dist(x1,y1,x2,y2);
//        for (int i =0; i<pads.size()-1;i++){
//            Pair padFrom = pads.get(i);
//            Pair padTo = pads.get(i+1);
//            int paddis = HeuristicFunction1.dist(x1,y1,padFrom.x,padFrom.y) ;
//            int hostagedis = HeuristicFunction1.dist(padTo.x,padTo.y,x2,y2);
//            for(int j = 0; j< pads.size()-1;j++){
//                Pair padFrom2 = pads.get(i);
//                Pair padTo2 = pads.get(i+1);
//                if(padFrom.equals(padFrom2))continue;
//                if(HeuristicFunction1.dist(padTo.x,padTo.y,x2,y2)>(HeuristicFunction1.dist(padTo.x,padTo.y,padFrom2.x,padFrom2.y)  + HeuristicFunction1.dist(padTo2.x,padTo2.y,x2,y2)))
//                    hostagedis  = HeuristicFunction1.dist(padTo.x,padTo.y,padFrom2.x,padFrom2.y)  + HeuristicFunction1.dist(padTo2.x,padTo2.y,x2,y2);
//
//            }
//            paddis+=hostagedis;
//            mindis = Math.min(mindis,paddis);
//        }
////        while (!pads.isEmpty()){
////            Pair padFrom = pads.remove(0);
////            Pair padTo = pads.remove(0);
////            int paddis = HeuristicFunction1.dist(x1,y1,padFrom.x,padFrom.y) + HeuristicFunction1.dist(padTo.x,padTo.y,x2,y2);
////            mindis = Math.min(mindis,paddis);
////        }
//        return mindis;
	}
	int dist(int x1,int y1,int x2,int y2){
		return Math.abs(x1-x2)+Math.abs(y1-y2);
	}
	public void setPads(ArrayList<Pair> pads) {
		this.pads = pads;
	}
	private ArrayList<Pair> pads;
	private int telephoneBoothx,telephoneBoothy;
	public void setTelephoneBoothx(int telephoneBoothx) {
		this.telephoneBoothx = telephoneBoothx;
	}
	public void setTelephoneBoothy(int telephoneBoothy) {
		this.telephoneBoothy = telephoneBoothy;
	}
}
