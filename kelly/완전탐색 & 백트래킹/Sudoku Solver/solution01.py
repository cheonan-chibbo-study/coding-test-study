class Solution:
    def solveSudoku(self, board: List[List[str]]) -> None:
        rows = [set() for _ in range(9)]
        cols = [set() for _ in range(9)]
        boxes = [set() for _ in range(9)]
        empty_pos = []

        for row in range(9):
            for col in range(9):
                v = board[row][col]

                if v != '.':
                    rows[row].add(v)
                    cols[col].add(v)
                    boxes[(row // 3) * 3 + (col // 3)].add(v)
                else:
                    empty_pos.append((row, col))

        candi = [str(n) for n in range(1, 10)]

        # 메서드
        def recursive(cur):
            if cur == len(empty_pos):
                return True

            t_row, t_col = empty_pos[cur]
            t_box = (t_row // 3) * 3 + (t_col // 3)

            for n in candi:
                if (n not in rows[t_row]) and (n not in cols[t_col]) and (n not in boxes[t_box]):
                    rows[t_row].add(n)
                    cols[t_col].add(n)
                    boxes[t_box].add(n)
                    board[t_row][t_col] = n

                    if recursive(cur + 1):
                        return True

                    rows[t_row].remove(n)
                    cols[t_col].remove(n)
                    boxes[t_box].remove(n)
                    board[t_row][t_col] = '.'

            return False

        # 메인 로직
        recursive(0)