from copy import deepcopy

def solution(key, lock):
    # 메서드
    def match(key_row_offset, key_col_offset, rot):
        rotated_key = rotate(rot)
        for lock_row in range(N):
            for lock_col in range(N):
                lock_value = lock[lock_row][lock_col]
                key_row, key_col = lock_row + key_row_offset, lock_col + key_col_offset

                if 0 <= key_row < M and 0 <= key_col < M:
                    lock_value += rotated_key[key_row][key_col]

                if lock_value != 1:
                    return False

        return True

    def rotate(rot):
        rotated = deepcopy(key)
        for i in range(rot):
            rotated = [list(r) for r in zip(*rotated[::-1])]

        return rotated

    # 메인 로직
    M = len(key)
    N = len(lock)

    for key_row_offset in range(M - 1, -N, -1):
        for key_col_offset in range(M - 1, -N, -1):
            for rot in range(4):
                if match(key_row_offset, key_col_offset, rot):
                    return True
    
    return False