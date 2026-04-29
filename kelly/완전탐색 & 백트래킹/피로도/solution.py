def solution(k, dungeons):
    d_size = len(dungeons)
    answer = 0

    # 메서드
    def search(visited, count):
        nonlocal answer, k
        answer = max(answer, count)

        for i in range(d_size):
            if visited[i] or k < dungeons[i][0]:
                continue

            visited[i] = True
            k -= dungeons[i][1]
            search(visited, count + 1)

            visited[i] = False
            k += dungeons[i][1]

    # 메인 로직
    visited = [False] * d_size
    search(visited, 0)

    return answer