from collections import defaultdict

class Solution:
    def minWindow(self, s: str, t: str) -> str:
        # T에 있는 각 문자의 등장 횟수를 저장하는 해시 맵
        target = defaultdict(int)
        for c in t:
            target[c] += 1

        # 슬라이딩 윈도우 설정을 위한 포인터 및 결과 변수
        l, r = 0, 0
        min_len = float('inf')
        answer = ""
        required_chars = len(t)

        # right 포인터가 문자열 s의 끝에 도달할 때까지 아래의 과정을 반복합니다.
        while r < len(s):
            # 현재 문자를 윈도우에 추가
            # 현재 right가 가리키는 문자가 t에 속한다면, 해당 문자의 등장 횟수를 감소시키고,
            # 필요한 문자의 수도 갱신합니다.
            if s[r] in target:
                target[s[r]] -= 1
                if target[s[r]] >= 0:
                    required_chars -= 1

            # 모든 문자가 포함된 경우
            while required_chars == 0:
                # 최소 윈도우 갱신
                if r - l + 1 < min_len:
                    min_len = r - l + 1
                    answer = s[l:r+1]
                # left를 이동하여 최소 윈도우 축소:
                if s[l] in target:
                    target[s[l]] += 1
                    if target[s[l]] > 0:
                        required_chars += 1
                l += 1
            # right를 이동하여 윈도우 확장
            r += 1

        return answer