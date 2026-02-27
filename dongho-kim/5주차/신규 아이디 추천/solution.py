def solution(new_id):
    chars = list(new_id)

    # 1단계
    for i, char in enumerate(chars):
        if 'A' <= char <= 'Z':
            chars[i] = char.lower()

    # 2단계
    chars = [ch for ch in chars if (ch.islower() or ch.isdigit() or ch == '-' or ch == '_' or ch == '.')]

    # 3단계
    stack = []
    new_chars = []
    for i, char in enumerate(chars):
        if char != '.':
            if stack:
                new_chars.append('.')
            new_chars.append(char)
            stack.clear()
            continue

        if stack:
            continue

        stack.append('.')

    # 4단계
    if new_chars and new_chars[0] == '.':
        new_chars = new_chars[1:]
    if new_chars and new_chars[-1] == '.':
        new_chars.pop()

    # 5단계
    if not new_chars:
        new_chars.append("a")

    # 6단계
    if len(new_chars) >= 16:
        new_chars = list(new_chars)[0:15]
    if new_chars[-1] == '.':
        new_chars.pop()

    # 7단계
    if len(new_chars) <= 2:
        last = new_chars[-1]
        while len(new_chars) < 3:
            new_chars.append(last)

    return ''.join(new_chars)
