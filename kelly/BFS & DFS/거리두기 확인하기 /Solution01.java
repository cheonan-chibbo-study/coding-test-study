import java.util.*;

class Solution {

    int[][] dir = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for (int i = 0; i < places.length; i++) {
            if (check(places[i])) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }

        return answer;
    }

    private boolean check(String[] place) {
        boolean[][] visited = new boolean[place.length][place[0].length()];
        for (int row = 0; row < place.length; row++) {
            for (int col = 0; col < place[row].length(); col++) {
                if (place[row].charAt(col) == 'P' && !visited[row][col]) {
                    if (!bfs(place, visited, row, col)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean bfs(String[] place, boolean[][] visited, int sRow, int sCol) {
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[]{sRow, sCol});
        visited[sRow][sCol] = true;

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            char curObj = place[cur[0]].charAt(cur[1]);
            boolean existPerson = false;

            for (int[] d : dir) {
                int nRow = cur[0] + d[0];
                int nCol = cur[1] + d[1];

                if (!isSafe(place, visited, nRow, nCol)) {
                    continue;
                }

                char nextObj = place[nRow].charAt(nCol);

                if (nextObj == 'P') {
                    if (curObj == 'P' || existPerson) {
                        return false;
                    }

                    existPerson = true;
                }

                if (!visited[nRow][nCol]) {
                    dq.offer(new int[]{nRow, nCol});
                    visited[nRow][nCol] = true;
                }
            }
        }

        return true;
    }

    private boolean isSafe(String[] place, boolean[][] visited, int row, int col) {
        return row >= 0 && row < place.length && col >= 0 && col < place[row].length()
            && place[row].charAt(col) != 'X';
    }
}