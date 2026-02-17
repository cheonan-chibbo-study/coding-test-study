from collections import deque
from copy import deepcopy

class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        # 전역 데이터
        M = len(board)
        N = len(board[0])
        DIR = [[-1, 0],[0, 1], [0, -1], [1, 0], [-1, 1], [-1, -1], [1, 1], [1, -1]]
        click = tuple(click)

        # 메서드
        def get_mines():
            mines = []
            for row in range(M):
                for col in range(N):
                    if board[row][col] == "M":
                        mines.append((row, col))

            return mines

        def get_near_mine_count(row, col):
            count = 0
            for dr, dc in DIR:
                next_r, next_c = row + dr, col + dc
                if is_safe(next_r, next_c) and board[next_r][next_c] == "M":
                    count += 1

            return count

        def is_safe(row, col):
            return row >= 0 and row < M and col >= 0 and col < N

        # 메인 로직
        new_board = deepcopy(board)
        mines = get_mines()
        if click in mines:
            new_board[click[0]][click[1]] = "X"
            return new_board

        dq = deque([click])
        visited = [[False] * N for _ in range(M)]
        visited[click[0]][click[1]] = True

        while dq:
            cur_r, cur_c = dq.popleft()
            mine_count = get_near_mine_count(cur_r, cur_c)

            if mine_count > 0:
                new_board[cur_r][cur_c] = str(mine_count)
                continue
            else:
                new_board[cur_r][cur_c] = "B"

            for dr, dc in DIR:
                next_r, next_c = cur_r + dr, cur_c + dc
                if not is_safe(next_r, next_c) or visited[next_r][next_c]:
                    continue

                dq.append((next_r, next_c))
                visited[next_r][next_c] = True

        return new_board