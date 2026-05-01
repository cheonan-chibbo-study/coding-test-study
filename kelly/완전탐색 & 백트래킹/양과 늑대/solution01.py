def solution(info, edges):
    visited = [False] * len(info)
    answer = 0

    # 메서드
    def back_tracking(yang, wolf):
        nonlocal answer
        answer = max(answer, yang)

        for edge in edges:
            if not visited[edge[0]] or visited[edge[1]]:
                continue

            nextY = yang
            nextW = wolf

            if info[edge[1]] == 0:
                nextY += 1
            else:
                nextW += 1

            if (nextY <= nextW):
                continue

            visited[edge[1]] = True
            back_tracking(nextY, nextW)

            visited[edge[1]] = False

    # 메인 로직
    visited[0] = True
    back_tracking(1, 0)

    return answer