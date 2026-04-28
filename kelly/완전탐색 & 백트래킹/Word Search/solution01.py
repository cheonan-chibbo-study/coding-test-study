class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        row_size = len(board)
        col_size = len(board[0])

        # 메서드
        def search(s_row, s_col, visited, step):
            if step == len(word):
                return True

            for dr, dc in [[-1, 0], [0, 1], [0, -1], [1, 0]]:
                n_row, n_col = s_row + dr, s_col + dc

                if not is_safe(n_row, n_col, visited) or board[n_row][n_col] != word[step]:
                    continue

                visited[n_row][n_col] = True
                if search(n_row, n_col, visited, step + 1):
                    return True

                visited[n_row][n_col] = False

            return False

        def is_safe(r, c, visited):
            return 0 <= r < row_size and 0 <= c < col_size and not visited[r][c]

        # 메인 로직
        for row in range(row_size):
            for col in range(col_size):
                if board[row][col] != word[0]:
                    continue

                visited = [[False] * col_size for _ in range(row_size)]
                visited[row][col] = True
                if search(row, col, visited, 1):
                    return True

        return False