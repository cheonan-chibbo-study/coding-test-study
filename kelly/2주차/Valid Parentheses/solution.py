class Solution:
    def isValid(self, s: str) -> bool:
        pair = {
            "(": ")",
            "{": "}",
            "[": "]"
        }

        stack = []
        for v in s:
            if v in pair:
                stack.append(pair[v])
            else:
                if not stack or stack[-1] != v:
                    return False
                else:
                    stack.pop()
        
        return not stack