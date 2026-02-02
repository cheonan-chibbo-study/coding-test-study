class Solution:
    def solveSudoku(self, board: list[list[str]]) -> None:
        self.rows = [set() for _ in range(9)]
        self.cols = [set() for _ in range(9)]
        self.squares = [[set() for _ in range(3)] for _ in range(3)]

        for x in range(9):
            for y in range(9):
                value = board[x][y]

                if value != '.':
                    self.rows[x].add(value)
                    self.cols[y].add(value)
                    self.squares[x // 3][y // 3].add(value)

        self.recurse(0, 0, board)

    def recurse(self, x, y, board):
        if y == 9:
            x += 1
            y = 0
        if x == 9:
            return True

        # 이미 숫자가 채워진 칸은 바로 다음 열로 탐색한다.
        if board[x][y] != '.':
            return self.recurse(x, y + 1, board)

        # 숫자를 모두 채워넣어본다.
        for num in map(str, range(1, 10)):
            if not self.can_put(x, y, num):
                continue

            board[x][y] = num
            self.rows[x].add(num)
            self.cols[y].add(num)
            self.squares[x // 3][y // 3].add(num)

            # 다음 열을 탐색한다.
            if self.recurse(x, y + 1, board):
                return True

            board[x][y] = '.'
            self.rows[x].remove(num)
            self.cols[y].remove(num)
            self.squares[x // 3][y // 3].remove(num)

    def can_put(self, x, y, num):
        if num in self.rows[x]:
            return False
        if num in self.cols[y]:
            return False
        if num in self.squares[x // 3][y // 3]:
            return False
        return True
