def solution(s):
    # 메서드
    def compress(text, length):
        #✅ 현재 압축 단위에 따라 문자열을 나눈다.
        words = [text[i:i + length] for i in range(0, len(text), length)]
        compressed = ""
        prev_word = ''
        count = 0

        #✅ 이전 부분 문자열과 현재 부분 문자열을 비교한다.
        for word in words:
            #✅ 만약 같다면, 이전 부분 문자열의 개수를 1 증가시킨다.
            if word == prev_word:
                count += 1
            #✅ 만약 다르다면, 이전 부분 문자열의 개수와 문자열을 압축 문자열에 이어붙인다.
            else:
                if count > 1:
                    compressed += str(count)
                compressed += prev_word
                #✅ 현재 부분 문자열을 이전 부분 문자열로 지정하고, 그 개수를 1로 설정한다.
                prev_word = word
                count = 1

            #✅ 마지막 부분 문자열을 처리한다.
        if count > 1:
            compressed += str(count)
        compressed += prev_word

        return len(compressed)

    # 메인 로직
    if len(s) == 1:
        return 1

    return min(compress(s, length) for length in range(1, len(s) // 2 + 1))