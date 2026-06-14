import java.util.*;

class Solution {

    int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    public int solution(int[][] game_board, int[][] table) {
        // 메인 로직
        List<Part> gbParts = getParts(game_board, 0);
        List<Part> tParts = getParts(table, 1);
        boolean[] gbpUsed = new boolean[gbParts.size()];

        int answer = 0;

        for (Part tPart : tParts) {
            int[][] rotatted = tPart.getValue();

            for (int rot = 0; rot < 4; rot++) {
                if (rot > 0) {
                    rotatted = rotate(rotatted);
                }

                boolean founded = false;

                for (int gbIdx = 0; gbIdx < gbParts.size(); gbIdx++) {
                    if (gbpUsed[gbIdx]) {
                        continue;
                    }

                    if (gbParts.get(gbIdx).match(new Part(rotatted, tPart.size))) {
                        answer += tPart.size;
                        gbpUsed[gbIdx] = true;
                        founded = true;
                        break;
                    }
                }

                if (founded) {
                    break;
                }
            }
        }

        return answer;
    }

    private List<Part> getParts(int[][] board, int obj) {
        List<Part> result = new ArrayList<>();
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == obj && !visited[row][col]) {
                    result.add(bfs(board, obj, visited, row, col));
                }
            }
        }

        return result;
    }

    private Part bfs(int[][] board, int obj, boolean[][] visited, int startR, int startC) {
        List<int[]> posList = new ArrayList<>();
        Deque<int[]> dq = new ArrayDeque<>();

        dq.offer(new int[]{startR, startC});
        visited[startR][startC] = true;
        posList.add(new int[]{startR, startC});

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();

            for (int[] d : dir) {
                int nextR = cur[0] + d[0];
                int nextC = cur[1] + d[1];

                if (!isSafe(board, obj, visited, nextR, nextC)) {
                    continue;
                }

                dq.offer(new int[]{nextR, nextC});
                visited[nextR][nextC] = true;
                posList.add(new int[]{nextR, nextC});
            }
        }

        return makePart(posList);
    }

    private boolean isSafe(int[][] board, int obj, boolean[][] visited, int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[row].length
            && board[row][col] == obj && !visited[row][col];
    }

    private Part makePart(List<int[]> posList) {
        int minRow = Integer.MAX_VALUE;
        int minCol = Integer.MAX_VALUE;
        int maxRow = Integer.MIN_VALUE;
        int maxCol = Integer.MIN_VALUE;

        for (int[] pos : posList) {
            minRow = Math.min(minRow, pos[0]);
            minCol = Math.min(minCol, pos[1]);
            maxRow = Math.max(maxRow, pos[0]);
            maxCol = Math.max(maxCol, pos[1]);
        }

        int[][] part = new int[maxRow - minRow + 1][maxCol - minCol + 1];
        int partSize = 0;
        for (int[] pos : posList) {
            part[pos[0] - minRow][pos[1] - minCol] = 1;
            partSize += 1;
        }

        return new Part(part, partSize);
    }

    private int[][] rotate(int[][] ori) {
        int[][] rotatted = new int[ori[0].length][ori.length];

        for (int row = 0; row < ori.length; row++) {
            for (int col = 0; col < ori[row].length; col++) {
                rotatted[col][ori.length - row - 1] = ori[row][col];
            }
        }

        return rotatted;
    }

    class Part {
        int[][] value;
        int size;

        public Part(int[][] value, int size) {
            this.value = value;
            this.size = size;
        }

        public int[][] getValue() {
            int[][] newValue = new int[value.length][value[0].length];
            for (int row = 0; row < value.length; row++) {
                for (int col = 0; col < value[0].length; col++) {
                    newValue[row][col] = value[row][col];
                }
            }

            return newValue;
        }

        public boolean match(Part o) {
            if (o.size != size || o.value.length != value.length || o.value[0].length != value[0].length) {
                return false;
            }

            for (int row = 0; row < value.length; row++) {
                for (int col = 0; col < value[row].length; col++) {
                    if (value[row][col] != o.value[row][col]) {
                        return false;
                    }
                }
            }

            return true;
        }
    }
}