def solution(tickets):
    # 메서드
    def dfs(cur):
        if len(path) == len(tickets) + 1:
            return True

        for i in range(len(tickets)):
            if tickets[i][0] == cur and not visited[i]:
                next = tickets[i][1]
                visited[i] = True
                path.append(next)

                if dfs(next):
                    return True

                path.pop()
                visited[i] = False

        return False

    # 메인 로직
    tickets.sort(key=lambda x: (x[0], x[1]))
    visited = [False] * len(tickets)
    path = ["ICN"]
    dfs("ICN")

    return path
