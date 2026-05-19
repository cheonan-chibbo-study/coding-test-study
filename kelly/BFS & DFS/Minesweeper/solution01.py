from collections import deque

class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        dir = [[-1, 0], [0, -1], [0, 1], [1, 0], [-1, -1], [-1, 1], [1, -1], [1, 1]]

        # 메서드
        def is_safe(row, col):
            return 0 <= row < len(board) and 0 <= col < len(board[row])

        # 메인 로직
        dq = deque([click])
        visited = [[False] * len(board[0]) for _ in range(len(board))]

        while dq:
            c_row, c_col = dq.popleft()

            if board[c_row][c_col] == "M":
                board[c_row][c_col] = "X"
                break

            mine_count = 0
            for dr, dc in dir:
                n_row, n_col = c_row + dr, c_col + dc

                if not is_safe(n_row, n_col) or visited[n_row][n_col]:
                    continue

                if board[n_row][n_col] == "M":
                    mine_count += 1

            if mine_count == 0:
                board[c_row][c_col] = "B"
            else:
                board[c_row][c_col] = str(mine_count)
                continue

            for dr, dc in dir:
                n_row, n_col = c_row + dr, c_col + dc

                if not is_safe(n_row, n_col) or visited[n_row][n_col] or board[n_row][n_col] == "M":
                    continue

                dq.append([n_row, n_col])
                visited[n_row][n_col] = True

        return board