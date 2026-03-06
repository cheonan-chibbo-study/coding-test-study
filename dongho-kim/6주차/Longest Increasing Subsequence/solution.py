class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        nums = [0] + nums
        n = len(nums)

        memo = [[-float('inf')] * (n + 1) for _ in range(n + 1)]

        # i번 숫자를 뽑거나 뽑지 않았을 때, 만들 수 있는 증가 부분 수열의 최대 길이를 구한다.
        def recurse(i, last_index):
            if i == n:
                return 0
            if memo[i][last_index] != -float('inf'):
                return memo[i][last_index]

            result = -1e9

            # 뽑는 경우
            # 단, 처음 뽑거나 직전에 뽑은 숫자보다 커야 뽑을 수 있다.
            if (last_index == 0) or (nums[i] > nums[last_index]):
                result = max(result, recurse(i + 1, i) + 1)

            # 뽑지 않는 경우
            result = max(result, recurse(i + 1, last_index))
            memo[i][last_index] = result
            return result

        return recurse(1, 0)
