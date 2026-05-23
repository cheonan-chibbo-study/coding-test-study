// 소요시간 -> 요청 시각 -> 작업 번호

import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        List<Node> sortList = new ArrayList<>();
        for (int id = 0; id < jobs.length; id++) {
            sortList.add(new Node(id, jobs[id][0], jobs[id][1]));
        }

        sortList.sort((v1, v2) -> {
            if (v1.reqTime != v2.reqTime) return Integer.compare(v1.reqTime, v2.reqTime);
            if (v1.needTime != v2.needTime) return Integer.compare(v1.needTime, v2.needTime);
            return Integer.compare(v1.id, v2.id);
        });

        Deque<Node> dq = new ArrayDeque<>(sortList);
        PriorityQueue<Node> pq = new PriorityQueue<>((v1, v2) -> {
            if (v1.needTime != v2.needTime) return Integer.compare(v1.needTime, v2.needTime);
            if (v1.reqTime != v2.reqTime) return Integer.compare(v1.reqTime, v2.reqTime);
            return Integer.compare(v1.id, v2.id);
        });

        int[] totalTimes = new int[jobs.length];
        int curTime = 0;

        while (!dq.isEmpty() || !pq.isEmpty()) {
            while (!dq.isEmpty() && dq.peek().reqTime <= curTime) {
                pq.offer(dq.poll());
            }

            if (pq.isEmpty()) {
                Node popped = dq.poll();
                pq.offer(popped);
                curTime = popped.reqTime;
            }

            Node cur = pq.poll();
            curTime += cur.needTime;
            totalTimes[cur.id] = curTime - cur.reqTime;
        }

        int answer = 0;
        for (int time : totalTimes) {
            answer += time;
        }

        return answer / jobs.length;
    }

    class Node {
        int id;
        int reqTime;
        int needTime;

        public Node(int id, int reqTime, int needTime) {
            this.id = id;
            this.reqTime = reqTime;
            this.needTime = needTime;
        }
    }
}