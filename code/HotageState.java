
public class HotageState {
	static private int c=0;
	private int id;
	private  int  damage;
	private boolean isAgent;
	
	public HotageState()
	{
		this.id=c;
		this.c++;
		this.damage=0;
		this.isAgent=false;
	}
	
	public int getID() {
		return this.id;
	}
	public int getDamage() {
		return this.damage;
	}
	public void setDamage(int damage) {
		if(damage<100)
			this.damage=damage;
		else {
			this.damage = 100;
			this.isAgent = true;
		}
	}
	public boolean isAgent() {
		return this.isAgent;
	}

}
