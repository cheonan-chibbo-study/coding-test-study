"""
# 요구 사항
정수 배열 nums가 주어진다.
부분 수열의 합이 가장 큰 값을 찾는다.

# 접근 방법
1. 2중 for문으로 부분 수열 범위를 잡고, for문으로 해당 범위 탐색하면서 합을 구한다. O(n^3)
2. 누적합?
arr = [-2,1,-3,4,-1,2,1,-5,4]
prefix_sum = [-2, -1, -4, 0, -1, 1, 2, -3, 1]

누적합구하고, 2중 for문으로 범위의 합을 구한다. O(n^2)

3. nums의 범위 중에서 숫자 2개 뽑아서, 해당 범위만큼의 sum을 구한다.
nC2 = n! / 2!(n-2)! = (10^4)! / 2!(10^4 - 2)!

4. 각 숫자를 뽑거나 뽑지 않는 모든 경우를 따졌을 때, 부분 수열의 합을 구한다. O(2^N)
5. 4번에서 메모이제이션
"""
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        n = len(nums)

        memo = [[-float('inf')] * n for _ in range(n)]

        # 마지막으로 선택한 정수의 인덱스가 last_i일 때, (last_i가 -1일 때는 마지막으로 선택한 정수가 없다는 의미다.)
        # 인덱스 i에 있는 num을 선택하거나, 선택하지 않았을 때, 부분 수열의 합의 최대값을 구하는 메서드
        def recurse(i, last_i):
            if i == n:
                return 0
            if memo[i][last_i] != -float('inf'):
                return memo[i][last_i]

            result = -1e9

            # 선택하는 경우
            # 마지막으로 선택한 정수와 이어지는 경우
            if last_i == i - 1:
                result = max(result, recurse(i + 1, i) + nums[i])
            # 처음 선택하거나 이어지지 않는 경우
            else:
                result = max(result, recurse(i + 1, i), nums[i])

            # 선택하지 않는 경우
            result = max(result, recurse(i + 1, last_i))
            memo[i][last_i] = result
            return result

        return recurse(0, -1)
