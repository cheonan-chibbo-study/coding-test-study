def solution(s):
    # 메서드
    def compress(length):
        words = [s[i:i + length] for i in range(0, len(s), length)]

        result = ""
        prev_word = ""
        count = 1

        for word in words:
            if word == prev_word:
                count += 1
                continue

            if count > 1:
                result += str(count)
            result += prev_word

            prev_word = word
            count = 1

        if count > 1:
            result += str(count)
        result += prev_word

        return len(result)

    # 메인 로직
    if len(s) == 1:
        return 1

    return min(compress(i) for i in range(1, (len(s) // 2) + 1))