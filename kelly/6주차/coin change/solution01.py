class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        # 메서드
        def dp(n):
            if n == 0:
                return 0

            candi_list = []
            for coin in coins:
                if n - coin >= 0:
                    if n - coin not in memo:
                        memo[n - coin] = dp(n - coin)
                    if memo[n - coin] != -1:
                        candi_list.append(memo[n - coin])

            return min(candi_list) + 1 if candi_list else -1

        # 메인 로직
        memo = {}
        return dp(amount)