from copy import deepcopy

def solution(game_board, table):
    # 메서드
    def get_parts(board, obj):
        result = []
        visited = [[False] * len(board[0]) for _ in range(len(board))]
        for row in range(len(board)):
            for col in range(len(board[row])):
                if board[row][col] == obj and not visited[row][col]:
                    result.append(dfs(board, obj, visited, row, col))

        return result

    def dfs(board, obj, visited, start_r, start_c):
        pos_list = [(start_r, start_c)]
        stack = [(start_r, start_c)]
        visited[start_r][start_c] = True

        while stack:
            cur_r, cur_c = stack.pop()

            for dr, dc in ((-1, 0), (0, -1), (0, 1), (1, 0)):
                next_r, next_c = cur_r + dr, cur_c + dc

                if not is_safe(board, visited, obj, next_r, next_c):
                    continue

                stack.append((next_r, next_c))
                visited[next_r][next_c] = True
                pos_list.append((next_r, next_c))

        return make_part(pos_list)

    def is_safe(board, visited, obj, row, col):
        return 0 <= row < len(board) and 0 <= col < len(board[row]) and board[row][col] == obj and not visited[row][col]

    def make_part(pos_list):
        row_list = [row for row, col in pos_list]
        col_list = [col for row, col in pos_list]

        min_row, min_col = min(row_list), min(col_list)
        max_row, max_col = max(row_list), max(col_list)

        part_row_size = max_row - min_row + 1
        part_col_size = max_col - min_col + 1
        node_count = 0
        part = [[0] * part_col_size for _ in range(part_row_size)]

        for row, col in pos_list:
            part[row - min_row][col - min_col] = 1
            node_count += 1

        return (part, node_count)

    def rotate(ori):
        rotatted = [[0] * len(ori) for _ in range(len(ori[0]))]
        for row in range(len(ori)):
            for col in range(len(ori[row])):
                rotatted[col][len(ori) - row - 1] = ori[row][col]

        return rotatted

    # 메인 로직
    gb_parts = get_parts(game_board, 0)
    table_parts = get_parts(table, 1)
    gbp_used = [False] * len(gb_parts)

    answer = 0

    for tp_idx in range(len(table_parts)):
        tp, tp_size = table_parts[tp_idx]
        rotatted = deepcopy(tp)
        stop = False

        for rot in range(4):
            if rot > 0:
                rotatted = rotate(rotatted)

            for gbp_idx in range(len(gb_parts)):
                if gbp_used[gbp_idx]:
                    continue

                gbp, gbp_size = gb_parts[gbp_idx]
                if rotatted == gbp:
                    answer += tp_size
                    gbp_used[gbp_idx] = True
                    stop = True
                    break

            if stop:
                break

    return answer