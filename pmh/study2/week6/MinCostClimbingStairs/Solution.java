package study2.week6.MinCostClimbingStairs;

import java.util.Arrays;

class Solution {
    int [] memo;
    public int minCostClimbingStairs(int[] cost) {
        int n =cost.length;
        memo = new int[n];
        Arrays.fill(memo,-1);

        return Math.min(dfs(cost,0),dfs(cost,1));
    }
    public int dfs(int[] cost, int idx){
        if(idx>= cost.length){
            return 0 ;
        }
        if(memo[idx] != -1){
            return memo[idx];
        }
        memo[idx] = cost[idx] + Math.min(dfs(cost,idx+1),dfs(cost,idx+2));

        return memo[idx];
    }
}