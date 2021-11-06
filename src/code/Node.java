package code;

import code.NeoOperator;

import java.util.ArrayList;

public class  Node implements Comparable{
private Node parent;
private int depth;
private int cost;
private String state;
private NeoOperator operator;
//The higher the number the higher the priority
private int priority;

public Node getParent() {
	return this.parent;
}
public void setParent(Node parent) {
	this.parent = parent; 
}
public int getDepth() {
	return this.depth;
}
public void setDepth(int depth) {
	this.depth = depth;
}
public int getCost() {
	return this.cost;
}
public void setCost(int cost) {
	this.cost = cost;
}
public String getState() {	
	return this.state;
}
//Neo location: x,y; Agents killed: x1,y1,x2,y2; damage: x; hostage info: x1,y1,damage1,hostageState1,x2,y2,damage2,hostageState2 ;
//pills not taken: x1,y1,x2,y2
//damage in state represents the damage of the hostage if it was not turned into agent

//hostageState in the state represents the state of the hostage:
//0 -> still hostage and not carried, 1 -> hostage and carried, 2 -> in the booth alive,
//3 -> turned into agent and still alive, 4 -> turned into agent and killed, 5 -> killed while being carried by neo,
//6 -> in the booth dead

public int getKilledHostagesNumber(){//only those who killed by Neo after turning into agents
	int i=0;
	//skip 3 semi colons
	int cntSemiColons=0;
	while(true){
		if(state.charAt(i)==';'){
			cntSemiColons++;
			i++;
			if(cntSemiColons==3)break;
			continue;
		}
		i++;
	}
	int ans=0;
	for(;i<state.length();){
		if(state.charAt(i)==';')break;
		int hState=0;
		while(true){
			if(state.charAt(i)==','){
				i++;
				break;
			}
			i++;
		}
		while(true){
			if(state.charAt(i)==','){
				i++;
				break;
			}
			i++;
		}
		while(true){
			if(state.charAt(i)==','){
				i++;
				break;
			}
			i++;
		}
		while(true){
			if(i==state.length() || state.charAt(i)==',' || state.charAt(i)==';'){
				if(state.charAt(i)==',')i++;
				break;
			}
			hState*=10;hState+=state.charAt(i)-'0';
			i++;
		}
		ans+=(hState==4)?1:0;
	}
	return ans;
}
public int getDeadHostagesNumber(){//whether turned into agents and killed or killed without turning into agents
	int i=0;
	//skip 3 semi colons
	int cntSemiColons=0;
	while(true){
		if(state.charAt(i)==';'){
			cntSemiColons++;
			i++;
			if(cntSemiColons==3)break;
			continue;
		}
		i++;
	}
	int ans=0;
	for(;i<state.length();){
		if(state.charAt(i)==';')break;
		int hState=0;
		while(true){
			if(state.charAt(i)==','){
				i++;
				break;
			}
			i++;
		}
		while(true){
			if(state.charAt(i)==','){
				i++;
				break;
			}
			i++;
		}
		while(true){
			if(state.charAt(i)==','){
				i++;
				break;
			}
			i++;
		}
		while(true){
			if(i==state.length() || state.charAt(i)==',' || state.charAt(i)==';'){
				if(state.charAt(i)==',')i++;
				break;
			}
			hState*=10;hState+=state.charAt(i)-'0';
			i++;
		}
		ans+=(hState==4 || hState==6)?1:0;
	}
	return ans;
}
public int getNeoDamage(){
	int i=0;
	//skip 2 semi colons
	int cntSemiColons=0;
	while(true){
		if(state.charAt(i)==';'){
			cntSemiColons++;
			i++;
			if(cntSemiColons==2)break;
			continue;
		}
		i++;
	}
	int ans=0;
	for(;i<state.length();i++){
		if(state.charAt(i)==';')break;
		ans*=10;
		ans+=(state.charAt(i)-'0');
	}
	return ans;
}
	public int getCarriedHostageCnt(){
		int i=0;
		//skip 3 semi colons
		int cntSemiColons=0;
		while(true){
			if(state.charAt(i)==';'){
				cntSemiColons++;
				i++;
				if(cntSemiColons==3)break;
				continue;
			}
			i++;
		}
		int ans=0;
		for(;i<state.length();){
			if(state.charAt(i)==';')break;
			int hState=0;
			while(true){
				if(state.charAt(i)==','){
					i++;
					break;
				}
				i++;
			}
			while(true){
				if(state.charAt(i)==','){
					i++;
					break;
				}
				i++;
			}
			while(true){
				if(state.charAt(i)==','){
					i++;
					break;
				}
				i++;
			}
			while(true){
				if(i==state.length() || state.charAt(i)==',' || state.charAt(i)==';'){
					if(state.charAt(i)==',')i++;
					break;
				}
				hState*=10;hState+=state.charAt(i)-'0';
				i++;
			}
			ans+=hState==1?1:0;
		}
		return ans;
	}
public int getHostageState(int curx,int cury,boolean afterOneAction){//afterOneAction get the state after performing one action which increase the damage by 2
	int i=0;
	//skip 3 semi colons
	int cntSemiColons=0;
	while(true){
		if(state.charAt(i)==';'){
			cntSemiColons++;
			i++;
			if(cntSemiColons==3)break;
			continue;
		}
		i++;
	}
	for(;i<state.length();){
		if(state.charAt(i)==';')break;
		int x=0,y=0,damage=0,hState=0;
		while(true){
			if(state.charAt(i)==','){
				i++;
				break;
			}
			x*=10;x+=state.charAt(i)-'0';
			i++;
		}
		while(true){
			if(state.charAt(i)==','){
				i++;
				break;
			}
			y*=10;y+=state.charAt(i)-'0';
			i++;
		}
		while(true){
			if(state.charAt(i)==','){
				i++;
				break;
			}
			damage*=10;damage+=state.charAt(i)-'0';
			i++;
		}
		while(true){
			if(i==state.length() || state.charAt(i)==',' || state.charAt(i)==';'){
				if(state.charAt(i)==',')i++;
				break;
			}
			hState*=10;hState+=state.charAt(i)-'0';
			i++;
		}
		if(hState==0 && damage+2>=100 && afterOneAction)hState=3;
		if(x==curx && y==cury)return hState;
	}
	return -1;
}
public String getHostageInfo(){
	int i=0;
	//skip 3 semi colons
	int cntSemiColons=0;
	while(true){
		if(state.charAt(i)==';'){
			cntSemiColons++;
			i++;
			if(cntSemiColons==3)break;
			continue;
		}
		i++;
	}
	StringBuilder ans=new StringBuilder();
	for(;i<state.length();i++){
		if(state.charAt(i)==';')break;
		ans.append(state.charAt(i));
	}
	return ans.toString();
}
public String getAgents(){
		int i=0;
		//skip 1 semi colon
		while(true){
			if(state.charAt(i)==';'){
				i++;
				break;
			}
			i++;
		}
		StringBuilder ans=new StringBuilder();
		for(;i<state.length();i++){
			if(state.charAt(i)==';')break;
			ans.append(state.charAt(i));
		}
		return ans.toString();
}
public boolean agentKilled(int x,int y){
	int i=0;
	//skip 1 semi colon
	while(true){
		if(state.charAt(i)==';'){
			i++;
			break;
		}
		i++;
	}
	int curx=0,cury=0;
	boolean readx=true;
	for(;i<state.length();i++){
		if(state.charAt(i)==';'){
			if(!readx && curx==x && cury==y)return true;
			break;
		}
		if(state.charAt(i)==','){
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
			curx += (state.charAt(i) - '0');
		}
		else{
			cury *= 10;
			cury += (state.charAt(i) - '0');
		}
	}
	return false;
}
	public int agentKilledCnt(){
		int i=0;
		//skip 1 semi colon
		while(true){
			if(state.charAt(i)==';'){
				i++;
				break;
			}
			i++;
		}
		int ans=0;
		boolean readx=true;
		for(;i<state.length();i++){
			if(state.charAt(i)==';')break;
			if(state.charAt(i)==','){
				if(readx){
					ans++;
					readx=false;
					continue;
				}
				readx=true;
				continue;
			}

		}
		return ans;
	}
public String getPills(){
		int i=0;
		//skip 4 semi colons
		int cntSemiColons=0;
		while(true){
			if(state.charAt(i)==';'){
				cntSemiColons++;
				i++;
				if(cntSemiColons==4)break;
				continue;
			}
			i++;
		}
		StringBuilder ans=new StringBuilder();
		for(;i<state.length();i++){
			ans.append(state.charAt(i));
		}
		return ans.toString();
	}
	public String getPillsWithout(int x,int y){
		int i=0;
		//skip 4 semi colons
		int cntSemiColons=0;
		while(true){
			if(state.charAt(i)==';'){
				cntSemiColons++;
				i++;
				if(cntSemiColons==4)break;
				continue;
			}
			i++;
		}
		StringBuilder ans=new StringBuilder();
		int curx=0,cury=0;
		boolean readx=true;
		for(;i<state.length();i++){
			if(state.charAt(i)==','){
				if(readx){
					readx=false;
					continue;
				}
				if(curx==x && cury==y){

				}
				else{
					if(ans.length()>0){
						ans.append(",");
					}
					ans.append(x+","+y);
				}
				curx=0;cury=0;
				readx=true;
				continue;
			}
			if(readx) {
				curx *= 10;
				curx += (state.charAt(i) - '0');
			}
			else{
				cury *= 10;
				cury += (state.charAt(i) - '0');
			}
		}
		return ans.toString();
	}
	public boolean pillExist(int x,int y){
	int i=0;
	//skip 4 semi colons
	int cntSemiColons=0;
	while(true){
		if(state.charAt(i)==';'){
			cntSemiColons++;
			i++;
			if(cntSemiColons==4)break;
			continue;
		}
		i++;
	}
	int curx=0,cury=0;
	boolean readx=true;
	for(;i<state.length();i++){
		if(state.charAt(i)==','){
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
			curx += (state.charAt(i) - '0');
		}
		else{
			cury *= 10;
			cury += (state.charAt(i) - '0');
		}
	}
	return false;
}
	public boolean aliveHostageExist(int x,int y){
		int i=0;
		//skip 4 semi colons
		int cntSemiColons=0;
		while(true){
			if(state.charAt(i)==';'){
				cntSemiColons++;
				i++;
				if(cntSemiColons==4)break;
				continue;
			}
			i++;
		}
		int curx=0,cury=0;
		boolean readx=true;
		for(;i<state.length();i++){
			if(state.charAt(i)==','){
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
				curx += (state.charAt(i) - '0');
			}
			else{
				cury *= 10;
				cury += (state.charAt(i) - '0');
			}
		}
		return false;
	}
public int getNeoLocationX(){
	int ans=0;
	for(int i=0;i<state.length();i++){
		if(state.charAt(i)==',')break;
		ans*=10;
		ans+=(state.charAt(i)-'0');
	}
	return ans;
}
public int getNeoLocationY(){
	int i=0;
	while(true){
		if(state.charAt(i)==','){
			i++;
			break;
		}
		i++;
	}
	int ans=0;
	for(;i<state.length();i++){
		if(state.charAt(i)==';')break;
		ans*=10;
		ans+=(state.charAt(i)-'0');
	}
	return ans;
}

public void setState(String state) {
	this.state = state;
}
public NeoOperator getOperator() {
	return this.operator;
}
public void setOperator(NeoOperator operator) {
	this.operator = operator;
}

public int compareTo(Object o) {
	Node b = (Node)o;
	return b.priority - this.priority;
}
	public int getPriority() {
		return this.priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
}
