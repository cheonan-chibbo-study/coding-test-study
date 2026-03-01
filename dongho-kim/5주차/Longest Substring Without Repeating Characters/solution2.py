# 슬라이싱 윈도우 첫 번째 형태
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        answer = 0

        left = 0
        char_map = {}
        for right, char in enumerate(s):
            # 현재 문자가 이미 등장했고, 그 위치가 현재 윈도우 안에 포함되는 경우
            if char in char_map and char_map[char] >= left:
                left = char_map[char] + 1

            # 현재 문자 위치를 최신화
            char_map[char] = right

            answer = max(answer, right - left + 1)

        return answer
