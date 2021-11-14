package code;
import java.util.PriorityQueue;

public class BreadthFirstQingFunction extends QingFunction{
    private int counter=0;
    @Override
    public void QingFunc(Node cur, PriorityQueue<Node> pq, int maxDepth) {
        //add first
        if(pq.isEmpty()){
            cur.setPriority(0);
            pq.add(cur);
            return;
        }
        int firstPriority=--counter;
        int myPriority=firstPriority;//make the new node has the smallest priority
        cur.setPriority(myPriority);
        pq.add(cur);
    }
}
