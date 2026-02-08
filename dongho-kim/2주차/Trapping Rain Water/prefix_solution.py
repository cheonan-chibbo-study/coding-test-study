# 접근 방법
# prefix_max
class Solution:
    def trap(self, height: List[int]) -> int:
        n = len(height)
        prefix_max = [0] * n
        suffix_max = [0] * n

        for i in range(n):
            if i == 0:
                prefix_max[i] = height[i]
                continue
            prefix_max[i] = max(prefix_max[i - 1], height[i])

        for i in range(n-1, -1, -1):
            if i == n-1:
                suffix_max[i] = height[i]
                continue
            suffix_max[i] = max(suffix_max[i + 1], height[i])

        max_val = max(height)
        max_val_idx = 0



        for i in range(n):
            if height[i] == max_val:
                max_val_idx = i
                break

        answer = 0
        for i in range(0, max_val_idx):
            if height[i] < prefix_max[i]:
                answer += (prefix_max[i] - height[i])
        for i in range(n - 1, max_val_idx, -1):
            if height[i] < suffix_max[i]:
                answer += (suffix_max[i] - height[i])
        return answer
