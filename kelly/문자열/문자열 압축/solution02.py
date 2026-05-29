def solution(s):
    # 메인 로직
    if len(s) == 1:
        return 1

    answer = len(s)

    for length in range(1, (len(s) // 2) + 1):
        words = [s[i:i + length] for i in range(0, len(s), length)]
        stack = [[words[0], 1]]

        for word in words[1:]:
            if word == stack[-1][0]:
                stack[-1][1] += 1
            else:
                stack.append([word, 1])

        result = ''.join([str(cnt) + w if cnt > 1 else w for w, cnt in stack])
        answer = min(answer, len(result))

    return answer