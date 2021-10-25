
public class State {
	
private NeoState neo;
private HostageState[] hostage;
private int hostagesCount;

public State(int count) {
	this.neo = new NeoState();
	this.hostagesCount= count;
	for (int i=0; i<count;i++)
		this.hostage[i] = new HostageState();
}
}
