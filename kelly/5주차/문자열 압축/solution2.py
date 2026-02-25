def solution(s):
    # 전역 데이터
    str_len = len(s)
    answer = str_len

    for size in range(1, str_len // 2 + 1):
        #✅ 현재 압축 단위에 따라 문자열을 나눈다.
        words = [s[i:i+size] for i in range(0, str_len, size)]
        stack = [(words[0], 1)]

        #✅ stack의 top과 현재 부분 문자열을 비교한다.
        for word in words[1:]:
            #✅ 만약 같다면, top의 중복 개수를 1 증가시킨다.
            if stack[-1][0] == word:
                tmp = stack.pop()
                stack.append([tmp[0], tmp[1] + 1])
            #✅ 만약 다르다면, 현재 부분 문자열과 1을 push한다.
            else:
                stack.append([word, 1])

        #✅ 압축 문자열을 만든다.
		#✅ 중복 개수가 1보다 크면, 중복 개수와 부분 문자열 w를 압축 문자열에 이어붙인다.
        compressed = ('').join([str(cnt) + w if cnt > 1 else w for w, cnt in stack])
        answer = min(answer, len(compressed))

    return answer