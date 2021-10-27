package code;

import org.w3c.dom.Node;

public abstract class SearchProblem {
private String initialState;
private NeoOperator operators;
private String [] stateSpace;
public abstract boolean  goalTest(String state);
public abstract int pathCost(String[]actions);

public String getInitialState() {
	return this.initialState;
}
public void setInitialState(String state) {
	this.initialState = state;
}
public String[] getStateSpace() {
	return this.stateSpace;
}
public void setStateSpace(String[] stateSpace) {
	this.stateSpace = stateSpace;
}
}
