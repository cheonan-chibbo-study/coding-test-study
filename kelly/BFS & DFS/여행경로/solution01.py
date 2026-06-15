def solution(tickets):
    # 메서드
    def dfs(cur):
        if len(answer) == len(tickets) + 1:
            return True

        for i in range(len(tickets)):
            if tickets[i][0] != cur or visited[i]:
                continue

            next = tickets[i][1]
            answer.append(next)
            visited[i] = True

            if dfs(next):
                return True

            answer.pop()
            visited[i] = False

    # 메인 로직
    tickets.sort(key = lambda x: (x[0], x[1]))
    answer = ["ICN"]
    visited = [False] * len(tickets)

    dfs("ICN")

    return answer