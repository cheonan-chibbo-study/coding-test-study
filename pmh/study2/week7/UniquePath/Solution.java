package study2.week7.UniquePath;

class Solution {
    /*
    m-1. n-1 -> 0,0


    r-1, c
    r  , c-1
     */
    int[][] memo;
    public int uniquePaths(int m, int n) {
        memo = new int[m][n];

        return dfs(m-1,n-1);

    }
    public int dfs(int r,int c){
        if(r<0 || c<0){
            return 0;
        }
        if(r ==0 && m==0){
            return 1;
        }
        if(memo[r][c] != 0){
            return memo[r][c];
        }
        memo[r][c] = dfs(r-1.c) + dfs(r,c-1);
        return memo[r][c];

    }
}