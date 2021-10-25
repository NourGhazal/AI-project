
public class NeoState {
private int hostages;
private  int  damage;
private boolean isDead;

public NeoState()
{
	this.hostages = 0;
	this.damage=0;
	this.isDead=false;
}
public int getHostages() 
{
	return this.hostages;
}
public void setHostages(int hostages)
{
	this.hostages = hostages;
}
public int geDamage() 
{
	return this.damage;
}
public void setDamage(int damage) 
{
	if(damage<100)
		this.damage =damage;
	else
	{
		this.damage = 100;
		this.isDead= True;
	}
}
public boolean isDead() 
{
	return this.isDead;
}
}
