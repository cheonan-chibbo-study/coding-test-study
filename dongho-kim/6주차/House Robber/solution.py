"""
# 요구사항
정수 배열이 주어진다.
정수를 선택해서 최대한의 합을 도출해야 한다.
이때 인접한 숫자는 뽑을 수 없다.

구할 수 있는 최대 합은?

# 접근 방법
인덱스 0번부터 시작해서 뽑는 경우와 뽑지 않는 경우를 모두 따진다. O(2^100)
"""
class Solution:
    def rob(self, nums: List[int]) -> int:
        n = len(nums)
        memo = [-1e9] * n
        # 인덱스 i에서, 숫자를 뽑거나 뽑지 않은 모든 경우를 따졌을 때, 얻을 수 있는 최대 합
        def recurse(i):
            if i >= n:
                return 0
            if memo[i] != -1e9:
                return memo[i]

            result = -1e9
            # 뽑는 경우
            result = max(result, recurse(i + 2)) + nums[i]
            # 뽑지 않는 경우
            result = max(result, recurse(i + 1))
            memo[i] = result
            return result

        return recurse(0)
