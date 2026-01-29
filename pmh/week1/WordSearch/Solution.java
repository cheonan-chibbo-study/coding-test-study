package week1.WordSearch;

import java.lang.foreign.StructLayout;
import java.util.ArrayList;
import java.util.List;
class Solution {
    public boolean exist(char[][] board, String word) {
        int n = board.length;
        int m = board[0].length;

        //시작 지점 찾기
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (board[r][c] == word.charAt(0)) {
                    if (Search(r, c, 0, board, word)) {
                        return true;
                    }
                }
            }
        }
        return false;

    }

    public boolean Search(int r, int c, int index, char[][] board, String word) {

        if (index == word.length()) return true;

        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != word.charAt(index)) {

            return false;
        }

        //방문처리
        char temp = board[r][c];
        board[r][c] = '#';

        //탐색하기 상하좌우
        boolean found = Search(r + 1, c, index + 1, board, word) ||
                Search(r - 1, c, index + 1, board, word) ||
                Search(r, c + 1, index + 1, board, word) ||
                Search(r, c - 1, index + 1, board, word);

        //복구
        board[r][c] = temp;

        return found;


    }


        public static void main(String[] args) {
            Solution sol = new Solution();

            // 테스트 데이터
            char[][] board = {
                    {'A', 'B', 'C', 'E'},
                    {'S', 'F', 'C', 'S'},
                    {'A', 'D', 'E', 'E'}
            };

            String word1 = "ABCCED";
            String word2 = "SEE";
            String word3 = "ABCB";

            // 결과 출력
            System.out.println("Word 'ABCCED' exists: " + sol.exist(board, word1)); // true
        }
    }
