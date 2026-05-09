import java.util.*;

class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        visited[0] = true;

        while (!stack.isEmpty()) {
            int cur = stack.pop();

            for (int next : rooms.get(cur)) {
                if (visited[next]) {
                    continue;
                }

                stack.push(next);
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