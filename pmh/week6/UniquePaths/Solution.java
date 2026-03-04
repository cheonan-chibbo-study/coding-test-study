package week6.UniquePaths;

class Solution {
    /*

    끝이[3][7] 이할때 끝에서 올수있는 경우는 [2][7] [3][6] ->[m-1][n] [m][m-1]

    끝이 다다를수있는 모든경우의 수 구하기
     */
    int[][] memo;
    public int uniquePaths(int m, int n) {

        memo = new int[m][n];


        return dfs(m - 1, n - 1);
    }

    public int dfs(int r, int c){
        // 격자 밖이면 0가지
        if (r < 0 || c < 0) return 0;

        // 시작점(0,0)은 1가지
        if (r == 0 && c == 0) return 1;

        // 이미 계산했으면 재사용
        if (memo[r][c] != 0) return memo[r][c];

        // (r,c)로 오는 방법 = 위에서 오는 방법 + 왼쪽에서 오는 방법
        memo[r][c] = dfs(r - 1, c) + dfs(r, c - 1);
        return memo[r][c];


    }
}