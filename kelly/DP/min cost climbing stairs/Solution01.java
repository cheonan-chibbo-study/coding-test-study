import java.util.*;

class Solution {

    int[] cost;
    Map<Integer, Integer> memo;

    public int minCostClimbingStairs(int[] cost) {
        this.cost = cost;
        this.memo = new HashMap<>();
        memo.put(0, 0);
        memo.put(1, 0);

        // 메인 로직
        return dp(cost.length);
    }

    private int dp(int n) {
        if (!memo.containsKey(n)) {
            memo.put(n, Math.min(dp(n - 1) + cost[n - 1], dp(n - 2) + cost[n - 2]));
        }

        return memo.get(n);
    }
}