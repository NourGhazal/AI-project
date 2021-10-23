import java.util.PriorityQueue;

public abstract class Tree {
private PriorityQueue<Node> nodes;

public boolean goalTest(Node state);

public PriorityQueue<Node> getNodes(){
	return this.nodes;
}
public void setNodes(PriorityQueue<Node> nodes) {
	this.nodes = nodes;
}
public void addNode(Node node) {
	this.nodes.add(node);
}
}
