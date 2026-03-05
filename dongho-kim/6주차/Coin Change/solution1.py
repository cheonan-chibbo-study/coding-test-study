"""
# 요구사항
정수 배열 coins가 주어지고 모든 정수는 서로 다른 양수이다. 그리고 정수 amount가 주어진다.
주어진 정수들로 amount를 만들 때, 사용되는 정수의 최소 개수는?

접근 방법
1. 0원부터 시작해서 BFS 방식으로 모든 경우의 금액을 탐색한다.
2. amount가 될 때까지 계속해서 정수를 하나 골라본다. O(N ^ (10^4))
"""
class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        memo = [1e9] * (amount + 1)

        def recurse(total):
            if total > amount:
                return 1e9
            if total == amount:
                return 0
            if memo[total] != 1e9:
                return memo[total]

            result = 1e9
            for coin in coins:
                result = min(result, recurse(total + coin) + 1)
            memo[total] = result
            return result

        answer = recurse(0)
        if answer == 1e9:
            return -1
        return answer
