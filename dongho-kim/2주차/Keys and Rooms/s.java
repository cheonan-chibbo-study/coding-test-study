import java.util.*;

class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        List<Integer> firstRoom = rooms.get(0);
        for (int key : firstRoom) {
            dfs(key, rooms, visited);
        }

        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    private void dfs(int num, List<List<Integer>> rooms, boolean[] visited) {
        visited[num] = true;

        List<Integer> nextKeys = rooms.get(num);
        for (int next : nextKeys) {
            if (!visited[next]) {
                dfs(next, rooms, visited);
            }
        }
    }
}
