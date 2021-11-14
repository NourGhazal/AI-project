package code;

import java.util.PriorityQueue;

public class DepthFirstQingFunction extends QingFunction{
    @Override
    public void QingFunc(Node cur, PriorityQueue<Node> pq,int maxDepth) {
        //add first
        if(pq.isEmpty()){
            cur.setPriority(0);
            pq.add(cur);
            return;
        }
        int firstPriority=pq.peek().getPriority();
        int myPriority=firstPriority+1;//make the new node has the biggest priority
        cur.setPriority(myPriority);
        pq.add(cur);
    }
}