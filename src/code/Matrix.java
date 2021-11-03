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
				x*=10;x+=10;
			}
			while(true){
				if(hostagesInfo.charAt(i)==','){
					i++;
					break;
				}
				y*=10;y+=10;
			}
			while(true){
				if(hostagesInfo.charAt(i)==','){
					i++;
					break;
				}
				damage*=10;damage+=10;
			}
			while(true){
				if(i==hostagesInfo.length() || hostagesInfo.charAt(i)==','){
					if(hostagesInfo.charAt(i)==',')i++;
					break;
				}
				hState*=10;hState+=10;
			}
			if(hState!=2 && hState!=4)return false;
			if(i==hostagesInfo.length())break;
		}
		return isTelephoneBooth(cur.getNeoLocationX(),cur.getNeoLocationY());
	}
	public int pathCost(String[]actions){
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
		for(;i<initState.length();i++){
			if(initState.charAt(i)==';'){
				break;
			}
			int x=0,y=0,tox=0,toy=0;
			while(true){
				if(initState.charAt(i)==','){
					i++;
					break;
				}
				x*=10;x+=10;
			}
			while(true){
				if(initState.charAt(i)==','){
					i++;
					break;
				}
				y*=10;y+=10;
			}
			while(true){
				if(initState.charAt(i)==','){
					i++;
					break;
				}
				tox*=10;tox+=10;
			}
			while(true){
				if(i==initState.length() || initState.charAt(i)==',' || initState.charAt(i)==';'){
					if(initState.charAt(i)==',')i++;
					break;
				}
				toy*=10;toy+=10;
			}
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
	public String updateHostages(String hostagesInfo,boolean carry,int curx,int cury,boolean drop){
		StringBuilder ans=new StringBuilder();

		int i=0;
		while(true){
			int x=0,y=0,damage=0,hState=0;
			while(true){
				if(hostagesInfo.charAt(i)==','){
					i++;
					break;
				}
				x*=10;x+=10;
			}
			while(true){
				if(hostagesInfo.charAt(i)==','){
					i++;
					break;
				}
				y*=10;y+=10;
			}
			while(true){
				if(hostagesInfo.charAt(i)==','){
					i++;
					break;
				}
				damage*=10;damage+=10;
			}
			while(true){
				if(i==hostagesInfo.length() || hostagesInfo.charAt(i)==','){
					if(hostagesInfo.charAt(i)==',')i++;
					break;
				}
				hState*=10;hState+=10;
			}
			if(carry && curx==x && cury==y){
				hState=1;
			}
			if(drop && (hState==1 || hState==5)){
				hState=2;
				drop=false;//neo can drop one hostage at a time as the description said
			}
			if(hState<2){
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
	public boolean cellHasAgent(Node cur,int x,int y){
		return (agentExist(x,y) && !cur.agentKilled(x,y)) || cur.getHostageState(x,y)==3;
	}
	public Node moveUp(Node cur){
		int x=cur.getNeoLocationX(),y=cur.getNeoLocationY();
		if(x==0){//can not move up
			return null;
		}
		if(cellHasAgent(cur,x-1,y)) {
			return null;
		}
		StringBuilder sb=new StringBuilder();
		sb.append((x-1)+","+y+";");
		sb.append(cur.getAgents()+";");
		sb.append(cur.getNeoDamage()+";");
		sb.append(updateHostages(cur.getHostageInfo(),false,x,y,false)+";");
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
		if(cellHasAgent(cur,x+1,y)) {
			return null;
		}
		StringBuilder sb=new StringBuilder();
		sb.append((x+1)+","+y+";");
		sb.append(cur.getAgents()+";");
		sb.append(cur.getNeoDamage()+";");
		sb.append(updateHostages(cur.getHostageInfo(),false,x,y,false)+";");
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
		if(cellHasAgent(cur,x,y-1)) {
			return null;
		}
		StringBuilder sb=new StringBuilder();
		sb.append((x)+","+(y-1)+";");
		sb.append(cur.getAgents()+";");
		sb.append(cur.getNeoDamage()+";");
		sb.append(updateHostages(cur.getHostageInfo(),false,x,y,false)+";");
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
		if(cellHasAgent(cur,x,y+1)) {
			return null;
		}
		StringBuilder sb=new StringBuilder();
		sb.append((x)+","+(y+1)+";");
		sb.append(cur.getAgents()+";");
		sb.append(cur.getNeoDamage()+";");
		sb.append(updateHostages(cur.getHostageInfo(),false,x,y,false)+";");
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
		if(cur.getHostageState(x,y)!=0){//we can only carry hostages that are still hostage and not carried
			return null;
		}
		if(cur.getCarriedHostageCnt()==getC()){//we can only carry if there is capacity
			return null;
		}
		StringBuilder sb=new StringBuilder();
		sb.append((x)+","+(y)+";");
		sb.append(cur.getAgents()+";");
		sb.append(cur.getNeoDamage()+";");
		sb.append(updateHostages(cur.getHostageInfo(),true,x,y,false)+";");
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
		if(!isTelephoneBooth(x,y)){//we can only drop hostages if neo at booth
			return null;
		}
		StringBuilder sb=new StringBuilder();
		sb.append((x)+","+(y)+";");
		sb.append(cur.getAgents()+";");
		sb.append(cur.getNeoDamage()+";");
		sb.append(updateHostages(cur.getHostageInfo(),false,x,y,true)+";");
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
		sb.append(updateHostages(cur.getHostageInfo(),false,x,y,false)+";");
		sb.append(cur.getPills());
		Node ans=new Node();
		ans.setDepth(cur.getDepth()+1);
		ans.setState(sb.toString());
		ans.setCost(0);//TODO <--------------
		ans.setParent(cur);
		ans.setOperator(NeoOperator.FLY);
		return ans;
	}

	public ArrayList<Node> nextNodes(Node cur){
		ArrayList<Node>ans=new ArrayList<>();




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
			if(i==agentCount-1)
				grid+=";";
			else
				grid+=",";
		}
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
			if(i==pillCount-1)
				grid+=";";
			else
				grid+=",";
		}
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
			if(i==padCount-1)
				grid+=";";
			else
				grid+=",";
		}

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
			if(i==hostageCount-1)
				grid+=";";
			else
				grid+=",";
		}
		System.out.println(agentCount);
		System.out.println(pillCount);
		System.out.println(padCount);
		System.out.println(hostageCount);
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
		return "";
	}
	public static void main(String[]args) {
		System.out.println(genGrid());
	}
}


