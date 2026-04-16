from collections import deque
from copy import deepcopy

def solution(game_board, table):
    # 메서드
    def parse_items(board, mark):
        item_pos_list = []
        for row in range(len(board)):
            for col in range(len(board[row])):
                if board[row][col] == mark:
                    item_pos_list.append(bfs(board, row, col, mark))

        return [make_item(pos) for pos in item_pos_list]

    def bfs(board, start_row, start_col, mark):
        result = []
        dq = deque()
        dq.append((start_row, start_col))
        result.append((start_row, start_col))
        board[start_row][start_col] = -1

        while dq:
            cur_r, cur_c = dq.popleft()

            for dr, dc in [(-1, 0), (0, 1), (0, -1), (1, 0)]:
                next_r, next_c = cur_r + dr, cur_c + dc

                if not 0<=next_r<len(board) or not 0<=next_c<len(board[next_r]) or board[next_r][next_c] != mark:
                    continue

                dq.append((next_r, next_c))
                result.append((next_r, next_c))
                board[next_r][next_c] = -1

        return result

    def make_item(pos_list):
        row_list = [pos[0] for pos in pos_list]
        col_list = [pos[1] for pos in pos_list]
        min_row = min(row_list)
        max_row = max(row_list)
        min_col = min(col_list)
        max_col = max(col_list)

        item_row_size = max_row - min_row + 1
        item_col_size = max_col - min_col + 1
        item = [[0] * item_col_size for _ in range(item_row_size)]
        for r, c in pos_list:
            item[r - min_row][c - min_col] = 1

        return (item, len(pos_list))

    # 메인 로직
    empty_list = parse_items(deepcopy(game_board), 0)
    block_list = parse_items(deepcopy(table), 1)

    answer = 0
    used = [False] * len(block_list)
    for empty, e_size in empty_list:
        founded = False
        for j in range(len(block_list)):
            if used[j]:
                continue

            block, b_size = block_list[j]
            if e_size != b_size:
                continue

            cur_block = block
            for _ in range(4):
                if empty == cur_block:
                    answer += e_size
                    used[j] = True
                    founded = True
                    break

                cur_block = [list(rotatted) for rotatted in zip(*cur_block[::-1])]

            if founded:
                break

    return answer