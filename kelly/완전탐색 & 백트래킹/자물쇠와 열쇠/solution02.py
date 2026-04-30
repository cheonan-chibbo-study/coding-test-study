from copy import deepcopy

def solution(key, lock):
    k_size = len(key)
    l_size = len(lock)

    # 메서드
    def check(row_offset, col_offset):
        rotated = deepcopy(key)
        is_continue = True

        for d in range(4):
            if d != 0:
                rotated = [list(item) for item in zip(*rotated[::-1])]
            is_continue = True

            for l_row in range(l_size):
                for l_col in range(l_size):
                    k_row, k_col = l_row + row_offset, l_col + col_offset

                    # 자물쇠가 홈 일 때
                    if lock[l_row][l_col] == 0:
                        if not is_safe(k_row, k_col) or rotated[k_row][k_col] != 1:
                            is_continue = False
                            break
                    else:  # 자물쇠가 돌기 일 때
                        if is_safe(k_row, k_col) and rotated[k_row][k_col] == 1:
                            is_continue = False
                            break

                if not is_continue:
                    break

            if is_continue:
                return True

        return False

    def is_safe(r, c):
        return 0 <= r < k_size and 0 <= c < k_size

    # 메인 로직
    for row_offset in range(k_size, -(l_size + 1), -1):
        for col_offset in range(k_size, -(l_size + 1), -1):
            if (check(row_offset, col_offset)):
                return True

    return False