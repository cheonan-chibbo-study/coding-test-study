def solution(m, k):
    i = 0
    result = []

    for ch in m:
        if i >= len(k): break

        if k[i] == ch:
            i += 1
            continue

        result.append(ch)

    return ''.join(result)
