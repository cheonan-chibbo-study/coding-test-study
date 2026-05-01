class Solution:
    def solveNQueens(self, n: int) -> List[List[str]]:
        answer = []

        # 메서드
        def back_tracking(board, count, s_row):
            if count == n:
                answer.append(["".join(s) for s in board])
                return

            for row in range(s_row, n):
                for col in range(n):
                    if not is_safe(board, row, col):
                        continue

                    board[row][col] = "Q"
                    back_tracking(board, count + 1, row + 1)
                    board[row][col] = "."

        def is_safe(board, r, c):
            dir = [[-1, 0], [1, 0], [-1, -1], [-1, 1], [1, -1], [1, 1]]

            for dr, dc in dir:
                n_row, n_col = r + dr, c + dc

                while (0 <= n_row < n and 0 <= n_col < n):
                    if board[n_row][n_col] == "Q":
                        return False

                    n_row, n_col = n_row + dr, n_col + dc

            return True

        # 메인 로직
        board = [["."] * n for _ in range(n)]
        back_tracking(board, 0, 0)

        return answer