package study2.week6.CoinChange;
class Solution {
    int[] memo;
    int[] coins;
    int amount;

    public int coinChange(int[] coins, int amount) {
        this.coins = coins;
        this.amount = amount;

        memo = new int[amount + 1];
        Arrays.fill(memo, -1);

        int ans = dfs(amount);

        return ans == amount + 1 ? -1 : ans;
    }

    public int dfs(int remain) {

        if (remain == 0) {
            return 0;
        }

        if (remain < 0) {
            return amount + 1;
        }

        if (memo[remain] != -1) {
            return memo[remain];
        }

        memo[remain] = amount + 1;

        for (int coin : coins) {
            memo[remain] = Math.min(
                    memo[remain],
                    dfs(remain - coin) + 1
            );
        }

        return memo[remain];
    }
}