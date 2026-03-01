# 슬라이싱 윈도우 2번째 형태
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        n = len(s)
        l, r = 0, 0
        used_chars = set()
        answer = 0

        while r < n:
            # 중복이 없는 경우
            if s[r] not in used_chars:
                # 윈도우 확장
                used_chars.add(s[r])
                answer = max(answer, r - l + 1) # 현재 윈도우 길이 체크
                r += 1 # 윈도우 확장

            # 중복이 있는 경우
            else:
                # 윈도우 축소
                used_chars.remove(s[l])
                l += 1

        return answer
