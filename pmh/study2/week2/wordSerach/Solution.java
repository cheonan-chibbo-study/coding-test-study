package study2.week2.wordSerach;

class Solution {

    int[] dr = {-1,0,1,0};
    int[] dc = {0,1,0,-1};

    public boolean exist(char[][] board, String word) {

        int m = board.length;
        int n = board[0].length;

        boolean[][] visited = new boolean[m][n];

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){

                if(dfs(board, word, i, j, 0, visited)){
                    return true;
                }

            }
        }

        return false;
    }

    public boolean dfs(char[][] board, String word,
                       int r, int c, int idx,
                       boolean[][] visited){

        if(idx == word.length()){
            return true;
        }

        int m = board.length;
        int n = board[0].length;

        if(r < 0 || r >= m || c < 0 || c >= n
                || visited[r][c]
                || board[r][c] != word.charAt(idx)){
            return false;
        }

        visited[r][c] = true;

        for(int i=0;i<4;i++){

            int nr = r + dr[i];
            int nc = c + dc[i];

            if(dfs(board, word, nr, nc, idx+1, visited)){
                return true;
            }
        }

        visited[r][c] = false;

        return false;
    }
}