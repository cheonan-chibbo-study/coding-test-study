package study1.week6.ClimbingStairs;

class Solution {
    int[] memo;
    public int climbStairs(int n) {
        memo = new int[n+1];

        return dfs(n);
    }

    public int dfs(int n){
        if(n<=2) return n;

        if(memo[n] != 0 ) return memo[n];

        memo[n] = dfs(n-1) + dfs(n-2);

        return memo[n];
    }
}