"""
정상까지 n 스텝 밟아야 함.
한 번에 1 or 2 스텝밖에 못감
정상까지 오를 수 있는 방법의 수는?

# 접근 방법
1. BFS
2. 0에서 시작해서 1로 가는 경우, 2로 가는 경우를 모두 따진다. O(2^45)
"""
class Solution:
    def climbStairs(self, n: int) -> int:
        memo = [-1e9] * (n + 1)

        # curr 위치에서 1칸 또는 2칸으로 이동하는 모든 경우를 따졌을 때, 정확히 n에 도달하는 모든 경우의 수를 구하는 메서드
        def recurse(curr):
            if curr > n:
                return 0
            if curr == n:
                return 1
            if memo[curr] != -1e9:
                return memo[curr]

            result = 0
            result += recurse(curr + 1)
            result += recurse(curr + 2)
            memo[curr] = result
            return result

        return recurse(0)
