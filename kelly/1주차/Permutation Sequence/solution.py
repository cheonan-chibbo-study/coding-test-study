class Solution:
    def getPermutation(self, n: int, k: int) -> str:
        # 전역 데이터

        # 메서드
        def recursive(nums, k, answer):
            if (len(nums) == 1):
                return answer + str(nums[0])
            
            case_count = calculate_factorial(len(nums) - 1)
            target = k // case_count
            answer += str(nums[target])
            nums.pop(target)

            return recursive(nums, k % case_count, answer)
        
        def calculate_factorial(n):
            result = 1
            for i in range(1, n + 1):
                result *= i
            
            return result

        # 메인 로직
        nums = [i for i in range(1, n + 1)]
        return recursive(nums, k - 1, "")
