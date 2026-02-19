package week4.PathwithMaximumProbability;
import java.util.*;
class Solution {
    class Node {
        int to;
        double prob;
        Node(int to, double prob) { this.to = to; this.prob = prob; }
    }

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        // 무방향 그래프 만들기
        List<Node>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            graph[edges[i][0]].add(new Node(edges[i][1], succProb[i]));
            graph[edges[i][1]].add(new Node(edges[i][0], succProb[i]));
        }

        // 최대 확률을 저장할 배열
        double[] maxProbs = new double[n];
        maxProbs[start] = 1.0;

        //우선순위 큐 (확률이 높은 순서대로)
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Double.compare(b.prob, a.prob));
        pq.offer(new Node(start, 1.0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            // 이미 더 높은 확률로 방문했다면 패스
            if (curr.prob < maxProbs[curr.to]) continue;
            // 목적지 도달 시 조기 종료
            if (curr.to == end) return curr.prob;

            for (Node next : graph[curr.to]) {
                if (maxProbs[curr.to] * next.prob > maxProbs[next.to]) {
                    maxProbs[next.to] = maxProbs[curr.to] * next.prob;
                    pq.offer(new Node(next.to, maxProbs[next.to]));
                }
            }
        }

        return maxProbs[end];
    }
}