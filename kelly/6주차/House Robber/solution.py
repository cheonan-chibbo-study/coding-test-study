class Solution(object):
    def rob(self, nums):
				#✅ n만큼 크기를 가진 memo를 만든다.
        memo = {}
				#✅ 점화식을 이용해 재귀함수를 구현한다.
        def dp(i):
						#✅ 0번째라면 0번째 집의 금액을 반환한다.
            if i == 0: return nums[0]
						#✅ 1번째라면 0번째, 1번째 중 더 큰 금액을 반환한다.
            if i == 1: return max(nums[0], nums[1])

						#✅ memo[i]에 저장된 값이 없다면 점화식에 따라 값을 계산해 저장한다.
            if i not in memo:
                memo[i] = max(dp(i-1), dp(i-2)+nums[i])
						#✅ memo[i]를 반환한다.
            return memo[i]
        #✅ memo[n-1]을 반환한다.
        return dp(len(nums) - 1)