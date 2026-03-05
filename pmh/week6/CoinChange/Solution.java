package week6.CoinChange;

import java.util.Arrays;

class Solution {
    int[] memo; // memo[x] = x원을 만드는 최소 동전 개수 (불가능이면 -1)

    public int coinChange(int[] coins, int amount) {
        memo = new int[amount + 1];
        Arrays.fill(memo, -2); // -2: 아직 계산 안 함 (sentinel)
        return dfs(coins, amount);
    }

    private int dfs(int[] coins, int amount) {
        // 1) base case
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        // 2) 이미 계산한 적 있으면 그대로 반환
        if (memo[amount] != -2) return memo[amount];

        int best = Integer.MAX_VALUE;

        for (int coin : coins) {
            // 11 기준 11 - 1 , 11 - 2 , 11- 5 중 최소갯수
            int sub = dfs(coins, amount - coin);
            if (sub == -1) continue;              // 만들 수 없으면 스킵
            best = Math.min(best, sub + 1);       // 동전 1개 추가
        }

        // 3) 계산 결과 저장

        memo[amount] = (best == Integer.MAX_VALUE) ? -1 : best;
        return memo[amount];
    }
}