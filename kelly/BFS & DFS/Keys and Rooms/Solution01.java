import java.util.*;

class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offer(0);
        visited[0] = true;

        while (!dq.isEmpty()) {
            int cur = dq.poll();

            for (int next : rooms.get(cur)) {
                if (visited[next]) {
                    continue;
                }

                dq.offer(next);
                visited[next] = true;
            }
        }

        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }

        return true;
    }
}