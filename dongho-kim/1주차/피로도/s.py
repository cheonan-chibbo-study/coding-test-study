def solution(k, dungeons):
    n = len(dungeons)
    visited = [False] * n
    answer = 0

    def recurse(depth, count, remain):
        nonlocal answer
        if answer == n:
            return
        if depth == n:
            answer = max(answer, count)
            return

        for i in range(n):
            if visited[i]:
                continue

            minimum, using = dungeons[i]
            if remain < minimum:
                recurse(depth + 1, count, remain)
            else:
                visited[i] = True
                recurse(depth + 1, count + 1, remain - using)
                visited[i] = False

    recurse(0, 0, k)
    return answer
