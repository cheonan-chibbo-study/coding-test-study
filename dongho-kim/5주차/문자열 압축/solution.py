def solution(s):
    if len(s) == 1:
        return 1

    answer = 1e9
    n = len(s)

    m = dict()
    result = []
    for size in range(1, (n//2) + 1):
        for i in range(0, n, size):
            l = i
            r = i + size - 1
            if r >= n:
                r = n - 1

                substr = s[l:]
                if substr in m:
                    m[substr] += 1
                else:
                    for k, v in m.items():
                        if v == 1:
                            result.append(k)
                        else:
                            compressed = str(v) + k
                            result.append(compressed)
                    m.clear()
                    m[substr] = 1
                    break

            substr = s[l:r+1]

            if substr not in m:
                for k, v in m.items():
                    if v == 1:
                        result.append(k)
                    else:
                        compressed = str(v) + k
                        result.append(compressed)

                m.clear()
                m[substr] = 1
                continue

            if substr in m:
                m[substr] += 1

        if m:
            for k, v in m.items():
                if v == 1:
                    result.append(k)
                else:
                    compressed = str(v) + k
                    result.append(compressed)

        answer = min(answer, len(''.join(result)))

        m.clear()
        result.clear()

    return answer
