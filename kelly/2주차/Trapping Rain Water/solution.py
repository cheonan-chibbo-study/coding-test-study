from collections import deque

class Solution:
    def trap(self, height: List[int]) -> int:
        stack = []
        total = 0

        for i in range(len(height)):
            while stack and height[stack[-1]] < height[i]:
                bottom = stack.pop()

                if not stack:
                    break

                left = stack[-1]
                width = i - left - 1
                water_height = min(height[left], height[i]) - height[bottom]
                total += width * water_height

            stack.append(i)

        return total