import java.util.*;

class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        Map<Integer, List<Node>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            graph.computeIfAbsent(edge[0], key -> new ArrayList<>()).add(new Node(edge[1], succProb[i]));
            graph.computeIfAbsent(edge[1], key -> new ArrayList<>()).add(new Node(edge[0], succProb[i]));
        }

        double[] cost = new double[n];
        for (int i = 0; i < n; i++) {
            cost[i] = -Double.MAX_VALUE;
        }

        // 메인 로직
        PriorityQueue<Node> pq = new PriorityQueue<>((v1, v2) -> {
            return Double.compare(v2.cost, v1.cost);
        });

        pq.offer(new Node(start_node, 1.0));
        cost[start_node] = 1.0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.n == end_node) {
                return cur.cost;
            }

            if (!graph.containsKey(cur.n)) {
                continue;
            }

            for (Node next : graph.get(cur.n)) {
                Node nextStatus = new Node(next.n, cur.cost * next.cost);
                if (nextStatus.cost <= cost[nextStatus.n]) {
                    continue;
                }

                pq.offer(nextStatus);
                cost[nextStatus.n] = nextStatus.cost;
            }
        }

        return 0.0;
    }

    class Node {
        int n;
        double cost;

        public Node(int n, double cost) {
            this.n = n;
            this.cost = cost;
        }
    }
}