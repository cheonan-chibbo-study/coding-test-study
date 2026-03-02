from collections import deque, defaultdict

class Solution:
    def minWindow(self, s: str, t: str) -> str:
        # 메서드
        def check():
            for k, v in target.items():
                if counter[k] < v:
                    return False
            return True

        # 메인 로직
        target_set = set(list(t))
        target = defaultdict(int)
        for c in t:
            target[c] += 1

        l, r = 0, 0
        counter = defaultdict(int)
        counter[s[0]] = 1
        dq = deque()
        answer = ""

        while l <= r:
            # 현재 윈도우에 target 요소가 모두 포함됨
            if check():
                if not answer or len(answer) > r - l + 1:
                    answer = s[l:r + 1]

                next_l = dq.popleft() if dq else l + 1

                if next_l >= len(s):
                    break

                for idx in range(l, next_l):
                    counter[s[idx]] -= 1

                l = next_l

            else:
                r += 1
                if r >= len(s):
                    break
                elif s[r] in target_set:
                    dq.append(r)

                counter[s[r]] += 1

        return answer