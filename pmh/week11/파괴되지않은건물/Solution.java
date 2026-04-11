package week11.파괴되지않은건물;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int r = board.length;
        int c = board[0].length;

        // 차분 배열
        int[][] diff = new int[r + 1][c + 1];

        // 스킬 적용: 네 꼭짓점만 표시
        for (int[] s : skill) {
            int type = s[0];
            int sr = s[1];
            int sc = s[2];
            int er = s[3];
            int ec = s[4];
            int degree = s[5];

            int value = (type == 1) ? -degree : degree;

            diff[sr][sc] += value;

            if (ec + 1 < c) {
                diff[sr][ec + 1] -= value;
            }
            if (er + 1 < r) {
                diff[er + 1][sc] -= value;
            }
            if (er + 1 < r && ec + 1 < c) {
                diff[er + 1][ec + 1] += value;
            }
        }

        // 가로 누적합
        for (int i = 0; i < r; i++) {
            for (int j = 1; j < c; j++) {
                diff[i][j] += diff[i][j - 1];
            }
        }

        // 세로 누적합
        for (int j = 0; j < c; j++) {
            for (int i = 1; i < r; i++) {
                diff[i][j] += diff[i - 1][j];
            }
        }

        // 최종 board 반영 후 살아남은 건물 개수 세기
        int count = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] + diff[i][j] > 0) {
                    count++;
                }
            }
        }

        return count;
    }
}