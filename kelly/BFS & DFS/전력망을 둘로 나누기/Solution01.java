import java.util.*;

class Solution {

    Map<Integer, List<Integer>> graph;

    public int solution(int n, int[][] wires) {
        graph = new HashMap<>();
        for (int[] wire : wires) {
            graph.computeIfAbsent(wire[0], k -> new ArrayList<>()).add(wire[1]);
            graph.computeIfAbsent(wire[1], k -> new ArrayList<>()).add(wire[0]);
        }

        // 메인 로직
        int answer = Integer.MAX_VALUE;

        for (int[] wire : wires) {
            graph.get(wire[0]).remove(Integer.valueOf(wire[1]));
            graph.get(wire[1]).remove(Integer.valueOf(wire[0]));

            List<Integer> nodeCount = new ArrayList<>();
            Set<Integer> visited = new HashSet<>();
            for (int start : graph.keySet()) {
                if (visited.contains(start)) {
                    continue;
                }

                nodeCount.add(search(visited, start));
            }

            answer = Math.min(answer, Math.abs(nodeCount.get(0) - nodeCount.get(1)));

            graph.get(wire[0]).add(wire[1]);
            graph.get(wire[1]).add(wire[0]);
        }

        return answer;
    }

    private int search(Set<Integer> visited, int start) {
        Deque<Integer> dq = new ArrayDeque<>();
        dq.push(start);
        visited.add(start);

        int result = 0;
        while (!dq.isEmpty()) {
            int cur = dq.pop();
            result++;

            if (!graph.containsKey(cur)) {
                continue;
            }

            for (int next : graph.get(cur)) {
                if (visited.contains(next)) {
                    continue;
                }

                dq.push(next);
                visited.add(next);
            }
        }

        return result;
    }
}