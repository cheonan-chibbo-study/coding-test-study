class Solution:
    def trap(self, height: List[int]) -> int:
        answer = [0] * len(height)
        stack = []

        for i in range(len(height)):
            if not stack or height[stack[-1]] >= height[i]:
                stack.append(i)
                continue

            last_popped = None
            while stack and height[stack[-1]] < height[i]:
                last_popped = stack.pop()

            low_wall = last_popped if not stack else i
            start = last_popped if not stack else stack[-1]
            for j in range(start + 1, i):
                answer[j] = height[low_wall] - height[j]

            stack.append(i)

        return sum(answer)