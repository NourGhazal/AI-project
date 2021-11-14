package code;

import code.SearchProblem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;;
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
		return isTelephoneBooth(cur.getNeoLocationX(),cur.getNeoLocationY());
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
			if(x==killx && y==killy && hState==3){
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
				drop=false;//neo can drop one hostage at a time as the description said
			}
			if(hState<2){
				if(pillTaken){
					damage=Math.max(0,damage-20);
				}
				damage+=2;
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
	public Node moveUp(Node cur){
		int x=cur.getNeoLocationX(),y=cur.getNeoLocationY();
		if(x==0){//can not move up
			return null;
		}
		if(cellHasAgent(cur,x-1,y,true)) {
			return null;
		}
		StringBuilder sb=new StringBuilder();
		sb.append((x-1)+","+y+";");
		sb.append(cur.getAgents()+";");
		sb.append(cur.getNeoDamage()+";");
		sb.append(updateHostages(cur.getHostageInfo(),false,x,y,false,false,-1,-1)+";");
		sb.append(cur.getPills());
		Node ans=new Node();
		ans.setDepth(cur.getDepth()+1);
		ans.setState(sb.toString());
		ans.setCost(0);//TODO <--------------
		ans.setParent(cur);
		ans.setOperator(NeoOperator.UP);
		return ans;
	}
	public Node moveDown(Node cur){
		int x=cur.getNeoLocationX(),y=cur.getNeoLocationY();
		if(x+1==getM()){//can not move down
			return null;
		}
		if(cellHasAgent(cur,x+1,y,true)) {
			return null;
		}
		StringBuilder sb=new StringBuilder();
		sb.append((x+1)+","+y+";");
		sb.append(cur.getAgents()+";");
		sb.append(cur.getNeoDamage()+";");
		sb.append(updateHostages(cur.getHostageInfo(),false,x,y,false,false,-1,-1)+";");
		sb.append(cur.getPills());
		Node ans=new Node();
		ans.setDepth(cur.getDepth()+1);
		ans.setState(sb.toString());
		ans.setCost(0);//TODO <--------------
		ans.setParent(cur);
		ans.setOperator(NeoOperator.DOWN);
		return ans;
	}
	public Node moveLeft(Node cur){
		int x=cur.getNeoLocationX(),y=cur.getNeoLocationY();
		if(y==0){//can not move Left
			return null;
		}
		if(cellHasAgent(cur,x,y-1,true)) {
			return null;
		}
		StringBuilder sb=new StringBuilder();
		sb.append((x)+","+(y-1)+";");
		sb.append(cur.getAgents()+";");
		sb.append(cur.getNeoDamage()+";");
		sb.append(updateHostages(cur.getHostageInfo(),false,x,y,false,false,-1,-1)+";");
		sb.append(cur.getPills());
		Node ans=new Node();
		ans.setDepth(cur.getDepth()+1);
		ans.setState(sb.toString());
		ans.setCost(0);//TODO <--------------
		ans.setParent(cur);
		ans.setOperator(NeoOperator.LEFT);
		return ans;
	}
	public Node moveRight(Node cur){
		int x=cur.getNeoLocationX(),y=cur.getNeoLocationY();
		if(y+1==getN()){//can not move right
			return null;
		}
		if(cellHasAgent(cur,x,y+1,true)) {
			return null;
		}
		StringBuilder sb=new StringBuilder();
		sb.append((x)+","+(y+1)+";");
		sb.append(cur.getAgents()+";");
		sb.append(cur.getNeoDamage()+";");
		sb.append(updateHostages(cur.getHostageInfo(),false,x,y,false,false,-1,-1)+";");
		sb.append(cur.getPills());
		Node ans=new Node();
		ans.setDepth(cur.getDepth()+1);
		ans.setState(sb.toString());
		ans.setCost(0);//TODO <--------------
		ans.setParent(cur);
		ans.setOperator(NeoOperator.RIGHT);
		return ans;
	}
	public Node carry(Node cur){
		int x=cur.getNeoLocationX(),y=cur.getNeoLocationY();
		if(cur.getHostageState(x,y,false)!=0){//we can only carry hostages that are still hostage and not carried
			return null;
		}
		if(cur.getCarriedHostageCnt()>=getC()){//we can only carry if there is capacity
			return null;
		}
		StringBuilder sb=new StringBuilder();
		sb.append((x)+","+(y)+";");
		sb.append(cur.getAgents()+";");
		sb.append(cur.getNeoDamage()+";");
		sb.append(updateHostages(cur.getHostageInfo(),true,x,y,false,false,-1,-1)+";");
		sb.append(cur.getPills());
		Node ans=new Node();
		ans.setDepth(cur.getDepth()+1);
		ans.setState(sb.toString());
		ans.setCost(0);//TODO <--------------
		ans.setParent(cur);
		ans.setOperator(NeoOperator.CARRY);
		return ans;
	}
	public Node drop(Node cur){
		int x=cur.getNeoLocationX(),y=cur.getNeoLocationY();
		if(!isTelephoneBooth(x,y) || cur.getCarriedHostageCnt()==0){//we can only drop hostages if neo at booth and neo is carrying hostage
			return null;
		}
		StringBuilder sb=new StringBuilder();
		sb.append((x)+","+(y)+";");
		sb.append(cur.getAgents()+";");
		sb.append(cur.getNeoDamage()+";");
		sb.append(updateHostages(cur.getHostageInfo(),false,x,y,true,false,-1,-1)+";");
		sb.append(cur.getPills());
		Node ans=new Node();
		ans.setDepth(cur.getDepth()+1);
		ans.setState(sb.toString());
		ans.setCost(0);//TODO <--------------
		ans.setParent(cur);
		ans.setOperator(NeoOperator.DROP);
		return ans;
	}
	public Node fly(Node cur){
		int x=cur.getNeoLocationX(),y=cur.getNeoLocationY();
		Pair to=flyFrom(x,y);
		if(to==null){//we can only fly if iy exists in the grid in x,y
			return null;
		}
		StringBuilder sb=new StringBuilder();
		sb.append((to.x)+","+(to.y)+";");
		sb.append(cur.getAgents()+";");
		sb.append(cur.getNeoDamage()+";");
		sb.append(updateHostages(cur.getHostageInfo(),false,x,y,false,false,-1,-1)+";");
		sb.append(cur.getPills());
		Node ans=new Node();
		ans.setDepth(cur.getDepth()+1);
		ans.setState(sb.toString());
		ans.setCost(0);//TODO <--------------
		ans.setParent(cur);
		ans.setOperator(NeoOperator.FLY);
		return ans;
	}
	public Node takePill(Node cur){
		int x=cur.getNeoLocationX(),y=cur.getNeoLocationY();

		if(!cur.pillExist(x,y)){//we can only take pill if it exists in the grid in x,y
			return null;
		}
		StringBuilder sb=new StringBuilder();
		sb.append((x)+","+(y)+";");
		sb.append(cur.getAgents()+";");
		sb.append(Math.max(0,cur.getNeoDamage()-20)+";");
		sb.append(updateHostages(cur.getHostageInfo(),false,x,y,false,true,-1,-1)+";");
		sb.append(cur.getPillsWithout(x,y));
		Node ans=new Node();
		ans.setDepth(cur.getDepth()+1);
		ans.setState(sb.toString());
		ans.setCost(0);//TODO <--------------
		ans.setParent(cur);
		ans.setOperator(NeoOperator.TAKEPILL);
		return ans;
	}
	public Node killUp(Node cur){
		int x=cur.getNeoLocationX(),y=cur.getNeoLocationY();

		if(x==0 || !cellHasAgent(cur,x-1,y,false)){
			return null;
		}
		StringBuilder sb=new StringBuilder();
		sb.append((x)+","+(y)+";");
		String prevAgents=cur.getAgents();
		sb.append(prevAgents);
		if(agentExist(x-1,y)){
			if(prevAgents.length()>0)sb.append(",");
			sb.append((x-1)+","+(y));
		}
		sb.append(";");
		sb.append(Math.min(100,cur.getNeoDamage()+20)+";");
		sb.append(updateHostages(cur.getHostageInfo(),false,x,y,false,false,x-1,y)+";");
		sb.append(cur.getPills());
		Node ans=new Node();
		ans.setDepth(cur.getDepth()+1);
		ans.setState(sb.toString());
		ans.setCost(0);//TODO <--------------
		ans.setParent(cur);
		ans.setOperator(NeoOperator.KILLUp);
		return ans;
	}
	public Node killDown(Node cur){
		int x=cur.getNeoLocationX(),y=cur.getNeoLocationY();

		if(x+1==getM() || !cellHasAgent(cur,x+1,y,false)){
			return null;
		}
		StringBuilder sb=new StringBuilder();
		sb.append((x)+","+(y)+";");
		String prevAgents=cur.getAgents();
		sb.append(prevAgents);
		if(agentExist(x+1,y)){
			if(prevAgents.length()>0)sb.append(",");
			sb.append((x+1)+","+(y));
		}
		sb.append(";");
		sb.append(Math.min(100,cur.getNeoDamage()+20)+";");
		sb.append(updateHostages(cur.getHostageInfo(),false,x,y,false,false,x+1,y)+";");
		sb.append(cur.getPills());
		Node ans=new Node();
		ans.setDepth(cur.getDepth()+1);
		ans.setState(sb.toString());
		ans.setCost(0);//TODO <--------------
		ans.setParent(cur);
		ans.setOperator(NeoOperator.KILLDown);
		return ans;
	}
	public Node killLeft(Node cur){
		int x=cur.getNeoLocationX(),y=cur.getNeoLocationY();

		if(y==0 || !cellHasAgent(cur,x,y-1,false)){
			return null;
		}
		StringBuilder sb=new StringBuilder();
		sb.append((x)+","+(y)+";");
		String prevAgents=cur.getAgents();
		sb.append(prevAgents);
		if(agentExist(x,y-1)){
			if(prevAgents.length()>0)sb.append(",");
			sb.append((x)+","+(y-1));
		}
		sb.append(";");
		sb.append(Math.min(100,cur.getNeoDamage()+20)+";");
		sb.append(updateHostages(cur.getHostageInfo(),false,x,y,false,false,x,y-1)+";");
		sb.append(cur.getPills());
		Node ans=new Node();
		ans.setDepth(cur.getDepth()+1);
		ans.setState(sb.toString());
		ans.setCost(0);//TODO <--------------
		ans.setParent(cur);
		ans.setOperator(NeoOperator.KILLLeft);
		return ans;
	}
	public Node killRight(Node cur){
		int x=cur.getNeoLocationX(),y=cur.getNeoLocationY();

		if(y+1==getN() || !cellHasAgent(cur,x,y+1,false)){
			return null;
		}
		StringBuilder sb=new StringBuilder();
		sb.append((x)+","+(y)+";");
		String prevAgents=cur.getAgents();
		sb.append(prevAgents);
		if(agentExist(x,y+1)){
			if(prevAgents.length()>0)sb.append(",");
			sb.append((x)+","+(y+1));
		}
		sb.append(";");
		sb.append(Math.min(100,cur.getNeoDamage()+20)+";");
		sb.append(updateHostages(cur.getHostageInfo(),false,x,y,false,false,x,y+1)+";");
		sb.append(cur.getPills());
		Node ans=new Node();
		ans.setDepth(cur.getDepth()+1);
		ans.setState(sb.toString());
		ans.setCost(0);//TODO <--------------
		ans.setParent(cur);
		ans.setOperator(NeoOperator.KILLRight);
		return ans;
	}

	public ArrayList<Node> Expand(Node cur){
		ArrayList<Node>ans=new ArrayList<>();
		if(cur.getNeoDamage()>=100)return ans;
		Node nxt;
		if((nxt=carry(cur))!=null){
			ans.add(nxt);
		}
		if((nxt=drop(cur))!=null){
			ans.add(nxt);
		}
		if((nxt=moveLeft(cur))!=null){
			ans.add(nxt);
		}
		if((nxt=moveRight(cur))!=null){
			ans.add(nxt);
		}
		if((nxt=moveUp(cur))!=null){
			ans.add(nxt);
		}
		if((nxt=moveDown(cur))!=null){
			ans.add(nxt);
		}
		if((nxt=killLeft(cur))!=null){
			ans.add(nxt);
		}
		if((nxt=killUp(cur))!=null){
			ans.add(nxt);
		}
		if((nxt=killRight(cur))!=null){
			ans.add(nxt);
		}
		if((nxt=killDown(cur))!=null){
			ans.add(nxt);
		}
		if((nxt=takePill(cur))!=null){
			ans.add(nxt);
		}
		if((nxt=fly(cur))!=null){
			ans.add(nxt);
		}



		return ans;
	}

}


