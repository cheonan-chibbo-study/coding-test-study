from collections import deque

class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        n = len(board)
        m = len(board[0])

        dx = [-1, -1, 0, 1, 1, 1, 0, -1]
        dy = [0, 1, 1, 1, 0, -1, -1, -1]

        # 클릭한 위치가 지뢰인 경우
        if board[click[0]][click[1]] == "M":
            # 해당 위치를 X로
            board[click[0]][click[1]] = "X"
        else: # 클릭한 위치가 지뢰가 아닌 경우
            def bfs(sx, sy):
                visited = [[False] * m for _ in range(n)]
                q = deque()
                visited[sx][sy] = True
                q.append((sx, sy))

                while q:
                    x, y = q.popleft()

                    mine_cnt = 0

                    # 8방향 모두 탐색한다.
                    for d in range(8):
                        nx, ny = x + dx[d], y + dy[d]

                        # 다음 탐색 칸이 격자 범위를 벗어나면 탐색 X
                        if not (0 <= nx < n and 0 <= ny < m): continue

                        # 이미 탐색을 한 칸이라면 탐색 X
                        if visited[nx][ny]: continue

                        # 다음 탐색 칸이 지뢰이면 카운트
                        if board[nx][ny] == "M":
                            mine_cnt += 1
                            continue

                    # 8방향에 지뢰가 1개라도 있다면, 해당 칸에 지뢰 개수를 할당한다.
                    if mine_cnt > 0:
                        board[x][y] = str(mine_cnt)

                    # 8방향에 지뢰가 없다면, 해당 칸을 "B"로 바꾸고 주변의 모든 칸을 큐에 넣는다.
                    if mine_cnt == 0:
                        board[x][y] = "B"
                        for d in range(8):
                            nx, ny = x + dx[d], y + dy[d]
                            if not (0 <= nx < n and 0 <= ny < m): continue
                            if visited[nx][ny]: continue
                            visited[nx][ny] = True
                            q.append((nx, ny))


            # 클릭 지점으로부터 BFS 탐색을 시작한다.
            bfs(click[0], click[1])

        return board
