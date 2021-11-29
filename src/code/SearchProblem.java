package code;


import java.util.ArrayList;

public abstract class SearchProblem {
private String initialState;
private NeoOperator operators;
public abstract boolean  goalTest(Node cur);
public abstract  int pathCost(Node curr);
public abstract ArrayList<Node> Expand(Node cur);

public String getInitialState() {
	return this.initialState;
}
public void setInitialState(String state) {
	this.initialState = state;
}



}
