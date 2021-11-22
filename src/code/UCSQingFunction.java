package code;
import java.util.PriorityQueue;

public class UCSQingFunction extends QingFunction{
    @Override
    public void QingFunc(Node cur, PriorityQueue<Node> pq, int maxDepth) {
        cur.setPriority(-cur.getCost());
        pq.add(cur);
    }

}
