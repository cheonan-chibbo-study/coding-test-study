class Solution:
    def getPermutation(self, n: int, k: int) -> str:
        nums = [str(num) for num in range(1, n + 1)]

        # 메서드
        def recursive(k):
            nonlocal answer

            if len(nums) == 1:
                answer += nums[0]
                return

            case_count = facto(len(nums) - 1)
            target = k // case_count
            answer += nums[target]
            nums.pop(target)

            recursive(k % case_count)

        def facto(num):
            result = 1
            for i in range(2, num + 1):
                result *= i

            return result

        # 메인 로직
        answer = ""
        recursive(k - 1)

        return answer