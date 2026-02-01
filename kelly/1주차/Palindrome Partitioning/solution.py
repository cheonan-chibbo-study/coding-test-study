class Solution:
    def partition(self, s: str) -> List[List[str]]:
        # 메서드
        def backTracking(answer, candi, s):
            if s == "":
                if isPalindrome(candi):
                    answer.append(candi[:])
                return

            for i in range(len(s)):
                candi.append(s[:i + 1])
                backTracking(answer, candi, s[i + 1:])
                candi.pop()
            
        def isPalindrome(candi):
            for c in candi:
                if c != c[::-1]:
                    return False
            
            return True

        # 메인 로직
        answer = []
        backTracking(answer, [], s)
        return answer