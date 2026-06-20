import java.util.*;

class Solution {

    char[][] board;
    List<Set<Character>> rows;
    List<Set<Character>> cols;
    List<Set<Character>> boxes;
    List<Character> candi;
    List<int[]> emptyPos;

    public void solveSudoku(char[][] board) {
        this.board = board;
        this.rows = new ArrayList<>();
        this.cols = new ArrayList<>();
        this.boxes = new ArrayList<>();
        this.candi = new ArrayList<>();
        this.emptyPos = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            rows.add(new HashSet<>());
            cols.add(new HashSet<>());
            boxes.add(new HashSet<>());
            candi.add((char) (i + '0'));
        }

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                char v = board[row][col];
                if (v != '.') {
                    rows.get(row).add(v);
                    cols.get(col).add(v);
                    boxes.get((row / 3) * 3 + (col / 3)).add(v);
                } else {
                    emptyPos.add(new int[]{row, col});
                }
            }
        }

        // 메인 로직
        recursive(0);
    }

    private boolean recursive(int cur) {
        if (cur == emptyPos.size()) {
            return true;
        }

        int tRow = emptyPos.get(cur)[0];
        int tCol = emptyPos.get(cur)[1];
        int tBox = (tRow / 3) * 3 + (tCol / 3);

        for (char n : candi) {
            if ((!rows.get(tRow).contains(n)) && (!cols.get(tCol).contains(n)) && (!boxes.get(tBox).contains(n))) {
                rows.get(tRow).add(n);
                cols.get(tCol).add(n);
                boxes.get(tBox).add(n);
                board[tRow][tCol] = n;

                if (recursive(cur + 1)) {
                    return true;
                }

                rows.get(tRow).remove(n);
                cols.get(tCol).remove(n);
                boxes.get(tBox).remove(n);
                board[tRow][tCol] = '.';
            }
        }

        return false;
    }
}