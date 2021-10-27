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
	public boolean goalTest(String state){
		return true;
	}
	public int pathCost(String[]actions){
		return 0;
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


