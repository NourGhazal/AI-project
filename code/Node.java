
public class  Node implements Comparable{

private Node parent;
private int depth;
private int cost;
private String state;
private String operator;
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
public void setState(String state) {
	this.state = state;
}
public String getOperator() {
	return this.operator;
}
public void setOperator(String operator) {
	this.operator = operator;
}

public int compareTo(Node a, Node b) {
	return b.priority - a.priority;
}

}
