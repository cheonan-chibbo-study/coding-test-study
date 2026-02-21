package week4.SmallestRangeCoveringElementsfromKLists;
import java.util.*;
class Solution {
    static class Node {
        int val, listIdx, elemIdx;

        Node(int  val, int listIdx, int elemIdx){
            this.val = val;
            this.listIdx = listIdx;
            this.elemIdx = elemIdx;
        }


    }

    public int[] smallestRange(List<List<Integer>> nums) {
        int k = nums.size();

        PriorityQueue<Node> q = new PriorityQueue<>((a,b) -> Integer.compare(a.val , b.val))       ;

        int curMax = Integer.MIN_VALUE;

        //각 리스트 첫 원소를 큐에넣기
        for(int i = 0 ;i<k ;i++){
            int v = nums.get(i).get(0);
            q.offer(new Node(v,i,0));
            if(v > curMax) curMax =v;
        }

        int bestL = q.peek().val;
        int bestR = curMax;
        while(true){
            Node mn = q.poll(); // 제일 작은값 먼저나옴

            int curMin = mn.val;
            // 베스트 갱신
            if ((curMax - curMin) < (bestR - bestL) ||
                    ((curMax - curMin) == (bestR - bestL) &&
                            curMin < bestL)) {
                bestL = curMin;
                bestR = curMax;
            }

            // min 을 만든 리스트의 다음 값 가져오기
            int nextIdx = mn.elemIdx +1;
            List<Integer> list = nums.get(mn.listIdx);
            if(nextIdx == list.size())break;

            int nextVal = list.get(nextIdx);
            q.offer(new Node(nextVal,mn.listIdx,nextIdx));
            if(nextVal > curMax) curMax = nextVal;

        }
        return new int [] {bestL,bestR};
    }
}