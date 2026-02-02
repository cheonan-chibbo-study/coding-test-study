class Solution:
    def solveNQueens(self, n: int) -> List[List[str]]:
        answer = []
        result = [['.'] * n for _ in range(n)]
        self.recurse(0, 0, n, answer, result, 0)
        return answer

    def recurse(self, x, y, n, answer, result, cnt):
        if y == n:
            y = 0
            x += 1
        if x == n:
            if cnt == n:
                answer.append([''.join(row) for row in result])
            return

        # 배치하는 경우 (가능하다면)
        if self.can_place(x, y, result, n):
            result[x][y] = 'Q'
            self.recurse(x + 1, 0, n, answer, result, cnt + 1)
            result[x][y] = '.'

        # 배치하지 않고 다음 열로 탐색하는 경우
        self.recurse(x, y + 1, n, answer, result, cnt)


    def can_place(self, start_x, start_y, result, n):
        dx = [-1, -1, 0, 1, 1, 1, 0, -1]
        dy = [0, 1, 1, 1, 0, -1, -1, -1]

        for d in range(8):
            x = start_x
            y = start_y

            while True:
                nx, ny = x + dx[d], y + dy[d]
                if not self.in_array(nx, ny, n):
                    break

                if result[nx][ny] == 'Q':
                    return False
                x, y = nx, ny

        return True

    def in_array(self, x, y, n):
        return 0 <= x < n and 0 <= y < n
