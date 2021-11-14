package code;
import java.util.PriorityQueue;

public class BreadthFirstQingFunction extends QingFunction{
    @Override
    public void QingFunc(Node cur, PriorityQueue<Node> pq, int maxDepth) {
        //add first
        if(pq.isEmpty()){
            cur.setPriority(0);
            pq.add(cur);
            return;
        }
        Object[] arr= pq.toArray();
        Node x = (Node)(arr)[arr.length-1];
        int firstPriority=x.getPriority();
        int myPriority=firstPriority-1;//make the new node has the biggest priority
        cur.setPriority(myPriority);
        pq.add(cur);
    }
}
