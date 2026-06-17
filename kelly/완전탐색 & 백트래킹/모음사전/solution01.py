def solution(word):
    # 메서드
    def recursive(temp, words):
        if len(temp) == 5:
            return

        for ch in ('A', 'E', 'I', 'O', 'U'):
            temp.append(ch)
            words.append(''.join(temp))
            recursive(temp, words)

            temp.pop()

    # 메인 로직
    words = []
    recursive([], words)

    for i in range(len(words)):
        if words[i] == word:
            return i + 1

    return -1