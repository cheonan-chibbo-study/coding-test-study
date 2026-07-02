import java.util.*;

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 0);
        dp.put(1, 0);

        for (int i = 2; i <= cost.length; i++) {
            dp.put(i, Math.min(dp.get(i - 1) + cost[i - 1], dp.get(i - 2) + cost[i - 2]));
        }

        return dp.get(cost.length);
    }
}