package week1.NQueens;
import java.util.ArrayList;
import java.util.List;
class Solution {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        boolean[][] chessBoard = new boolean[n][n];

        dfs(0,n,new ArrayList<>(),result,chessBoard);
        return  result;
    }

    public void dfs(int row,int n, List<String> current, List<List<String>> result, boolean[][] chessBoard) {
        if (row == n) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int col = 0; col < n; col++) {
            if (qennCheck(row, col, chessBoard, n)) {
                chessBoard[row][col] = true;

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    if(i==col) sb.append("Q");
                    else sb.append(".");
                }
                String rowString = sb.toString();

                current.add(rowString);

                dfs(row + 1, n, current, result, chessBoard);

                current.remove(current.size() - 1);

                chessBoard[row][col] = false;
            }
        }


    }

    public boolean qennCheck(int row, int col,boolean[][] chessBoard, int n) {

        //우쪽 세로 방향 체크
        for (int i = 0; i < row; i++) {
            if(chessBoard[i][col])return false;
        }

        //왼쪽 위 대각선 방향 체크
        for (int i = 1; i <= row && col - i >= 0; i++) {
            if(chessBoard[row-i][col-i]) return false;
        }
        //오른쪽 위 대각선 방향 체크
        for (int i = 1; i <= row && col + i < n; i++) {
            if (chessBoard[row-i][col+i])return false;
        }

        //세 방향 모두 퀸이 없으면 안전
        return true;
    }

}