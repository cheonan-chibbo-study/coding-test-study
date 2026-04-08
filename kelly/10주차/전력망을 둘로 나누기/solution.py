from collections import deque

def solution(n, wires):
    # 메서드
    def divide_net(ignore):
        board = get_board(ignore)
        visited = [False] * (n + 1)
        result = []
        for pos in range(1, n + 1):
            if visited[pos]:
                continue

            result.append(bfs(board, visited, pos))

        return result

    def get_board(ignore):
        board = [[] for _ in range(n + 1)]
        for i in range(len(wires)):
            if i == ignore:
                continue

            board[wires[i][0]].append(wires[i][1])
            board[wires[i][1]].append(wires[i][0])

        return board

    def bfs(board, visited, start):
        count = 1
        dq = deque()
        dq.append(start)
        visited[start] = True

        while dq:
            cur = dq.popleft()
            for next in board[cur]:
                if visited[next]:
                    continue

                dq.append(next)
                visited[next] = True
                count += 1

        return count

    # 메인 로직
    answer = float('inf')
    for ignore in range(0, len(wires)):
        result = divide_net(ignore)
        answer = min(answer, abs(result[0] - result[1]))

    return answer