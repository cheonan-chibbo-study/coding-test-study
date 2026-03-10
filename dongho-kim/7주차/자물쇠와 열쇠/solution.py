def solution(key, lock):
    n = len(lock)
    m = len(key)

    target_cnt = 0
    for x in range(n):
        for y in range(n):
            if lock[x][y] == 0:
                target_cnt += 1

    def in_lock(x, y):
        return 0 <= x < n and 0 <= y < n

    direction = 0
    while direction < 4:
        # Brute-Force
        # Key의 시작 위치 설정
        for kx in range(-m + 1, n):
            for ky in range(-m + 1, n):
                cnt = 0
                conflict = False
                for x in range(kx, kx + m):
                    for y in range(ky, ky + m):
                        if not in_lock(x, y): continue

                        key_x = x - kx
                        key_y = y - ky

                        if lock[x][y] == 1 and key[key_x][key_y] == 1:
                            conflict = True
                            break

                        if lock[x][y] == 0 and key[key_x][key_y] == 1:
                            cnt += 1

                    if conflict: break

                if not conflict and cnt == target_cnt:
                    return True

        # Turn Right
        new_key = [[0] * m for _ in range(m)]
        for x in range(m):
            for y in range(m):
                new_key[y][m-1-x] = key[x][y]
        key = new_key

        direction += 1

    return False
