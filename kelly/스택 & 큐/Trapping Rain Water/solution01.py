class Solution:
    def trap(self, height: List[int]) -> int:
        stack = []
        answer = 0

        for i in range(len(height)):
            while stack and height[stack[-1]] < height[i]:
                bottom = stack.pop()

                if not stack:
                    break

                left = stack[-1]
                water = min(height[left], height[i]) - height[bottom]
                answer += water * (i - left - 1)

            stack.append(i)

        return answer