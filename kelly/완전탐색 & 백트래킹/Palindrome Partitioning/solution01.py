class Solution:
    def partition(self, s: str) -> List[List[str]]:
        # 메서드
        def back_tracking(temp, start):
            if start == len(s):
                answer.append(temp[::])
                return

            sub_str = ""
            for i in range(start, len(s)):
                sub_str += s[i]

                if not is_palindrome(sub_str):
                    continue

                temp.append(sub_str)
                back_tracking(temp, i + 1)

                temp.pop()

        def is_palindrome(target):
            return target == target[::-1]

        # 메인 로직
        answer = []
        back_tracking([], 0)

        return answer