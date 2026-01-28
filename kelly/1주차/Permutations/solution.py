class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        # 메서드
        def backTracking(temp, result):
            if len(temp) == len(nums):
                result.append(temp[:])
                return
            
            for i in (nums):
                if i in temp:
                    continue

                temp.append(i)
                backTracking(temp, result)
                temp.pop()
        
        # 메인 로직
        result = []
        backTracking([], result)

        return result