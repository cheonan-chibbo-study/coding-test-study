class Solution:
    def __init__(self):
        self.count = 0
        self.answer = None

    def getPermutation(self, n: int, k: int) -> str:
        nums = list(range(1, n + 1))
        used = [False] * (n + 1)
        self.recurse(0, nums, used, [], k)
        return self.answer

    def recurse(self, depth, nums, used, result, k):
        if depth == len(nums):
            self.count += 1
            if self.count == k:
                self.answer = ''.join(map(str, result))
                return True
            return False

        for i in range(len(nums)):
            if used[i]:
                continue

            used[i] = True
            result.append(nums[i])
            if self.recurse(depth + 1, nums, used, result, k):
                return True
            result.pop()
            used[i] = False
