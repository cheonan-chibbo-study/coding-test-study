package week3.minesweeper;
import java.util.*;

class Solution {
    private final int[] dr = {-1,-1,-1, 0,0, 1,1,1};
    private final int[] dc = {-1, 0, 1,-1,1,-1,0,1};

    public char[][] updateBoard(char[][] board, int[] click) {
        int r = click[0], c = click[1];

        // 1️⃣ 클릭이 지뢰면 게임 종료
        if (board[r][c] == 'M') {
            board[r][c] = 'X';
            return board;
        }

        // 2️⃣ BFS 시작
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int row = cur[0], col = cur[1];

            // 이미 처리된 경우 skip
            if (board[row][col] != 'E') continue;

            int mines = countAdjacentMines(board, row, col);

            // 인접 지뢰가 있으면 숫자로 변경
            if (mines > 0) {
                board[row][col] = (char) ('0' + mines);
            } else {
                // 지뢰가 없으면 B로 바꾸고 주변 확장
                board[row][col] = 'B';

                for (int k = 0; k < 8; k++) {
                    int nr = row + dr[k];
                    int nc = col + dc[k];

                    if (inBounds(board, nr, nc) && board[nr][nc] == 'E') {
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
        }

        return board;
    }

    private int countAdjacentMines(char[][] board, int r, int c) {
        int count = 0;
        for (int k = 0; k < 8; k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];

            if (inBounds(board, nr, nc) && board[nr][nc] == 'M') {
                count++;
            }
        }
        return count;
    }

    private boolean inBounds(char[][] board, int r, int c) {
        return r >= 0 && r < board.length &&
                c >= 0 && c < board[0].length;
    }
}
