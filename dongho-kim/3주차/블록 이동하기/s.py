from collections import deque

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def solution(board):
    n = len(board)
    # 지도의 외곽을 벽(1)으로 둘러싸서 범위 체크를 간소화함
    new_board = [[1] * (n + 2) for _ in range(n + 2)]
    for i in range(n):
        for j in range(n):
            new_board[i + 1][j + 1] = board[i][j]

    # BFS 준비
    start_pos = {(1, 1), (1, 2)}
    q = deque([(start_pos, 0)])
    visited = [start_pos]

    while q:
        curr_pos, cost = q.popleft()

        # 목표 도달 확인 (로봇의 두 칸 중 하나라도 (n, n)에 위치하면 종료)
        if (n, n) in curr_pos:
            return cost

        # 다음 이동 가능한 위치 탐색
        for next_p in get_next_pos(curr_pos, new_board):
            if next_p not in visited:
                q.append((next_p, cost + 1))
                visited.append(next_p)

    return 0


def get_next_pos(curr_pos, board):
    result = []
    curr_pos = list(curr_pos)
    x1, y1 = curr_pos[0]
    x2, y2 = curr_pos[1]

    # 1. 상하좌우 이동
    for d in range(4):
        nr1, nc1, nr2, nc2 = x1 + dx[d], y1 + dy[d], x2 + dx[d], y2 + dy[d]
        if board[nr1][nc1] == 0 and board[nr2][nc2] == 0:
            result.append({(nr1, nc1), (nr2, nc2)})

    # 2. 로봇이 가로 방향일 때 회전
    if x1 == x2:
        for d in [-1, 1]: # 위쪽(-1) 또는 아래쪽(1)으로 회전
            if board[x1 + d][y1] == 0 and board[x2 + d][y2] == 0: # 회전 경로에 장애물이 없다면
                result.append({(x1, y1), (x1 + d, y1)}) # (x1, y1)를 축으로 회전
                result.append({(x2, y2), (x2 + d, y2)}) # (x2, y2)를 축으로 회전

    # 3. 로봇이 세로 방향일 때 회전
    elif y1 == y2:
        for d in [-1, 1]: # 왼쪽(-1) 또는 오른쪽(1)으로 회전
            if board[x1][y1 + d] == 0 and board[x2][y2 + d] == 0: # 회전 경로에 장애물이 없다면
                result.append({(x1, y1), (x1, y1 + d)}) # (x1, y1)를 축으로 회전
                result.append({(x2, y2), (x2, y2 + d)}) # (x2, y2)를 축으로 회전

    return result
