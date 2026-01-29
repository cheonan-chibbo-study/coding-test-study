class Solution:
    dx = [-1, 0, 1, 0]
    dy = [0, 1, 0, -1]

    def exist(self, board: list[list[str]], word: str) -> bool:
        success = False
        used = [[False] * len(board[0]) for _ in range(len(board))]

        def dfs(depth: int, x: int, y: int):
            nonlocal success
            if success:
                return
            if depth == len(word):
                success = True
                return

            for d in range(4):
                next_x, next_y = x + self.dx[d], y + self.dy[d]

                if not (0 <= next_x < len(board) and 0 <= next_y < len(board[0])):
                    continue
                if used[next_x][next_y]:
                    continue
                if board[next_x][next_y] == word[depth]:
                    used[next_x][next_y] = True
                    dfs(depth + 1, next_x, next_y)
                    used[next_x][next_y] = False

        for x in range(len(board)):
            for y in range(len(board[x])):
                if board[x][y] == word[0]:
                    used[x][y] = True
                    dfs(1, x, y)
                    used[x][y] = False

        return success
