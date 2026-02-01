# key는 M*M이다.
# lock은 n*n이다.
# 열쇠는 회전과 이동이 가능하다. 열쇠의 돌기 부분(1)이 자물쇠의 홈 부분(0)에 딱 맞게 채우면 자물쇠가 열린다.
# 자물쇠 영역을 벗어난 부분에 있는 열쇠의 홈과 돌기는 아무런 영향이 없다.
# 자물쇠 영역 내에서는 열쇠 돌기 부분과 자물쇠 홈 부분이 정확히 일치해야 한다.
# 열쇠의 돌기와 자물쇠의 돌기가 만나서는 안된다.
# 열쇠로 자물쇠를 열 수 있으면 true, 그렇지 않으면 false를 반환한다.

# 접근 방법
# key의 중심부를 lock의 (-1, -1)부터 (n, n)까지 한 번씩 데본다. -> 열지 못하면 key를 시계 방향으로 한 번 돌린 다음, 다시 한 번씩 데본다. 그렇게 세 번 돌렸음에도 안되면 못여는거다.
from collections import deque

def solution(key, lock):
    n = len(lock)
    m = len(key)

    target_count = 0
    for x in range(n):
        for y in range(n):
            if lock[x][y] == 0:
                target_count += 1

    turn_count = 0
    while True:
        if turn_count == 4:
            break

        finish = simulate(key, lock, target_count)

        if finish:
            return True

        # 시계 방향으로 돌리기
        key = rotate(key)
        turn_count += 1

    return False

def simulate(key, lock, target_count):
    m = len(key)
    n = len(lock)

    for start_x in range(-m+1, n):
        for start_y in range(-m+1, n):
            count = 0

            for x in range(start_x, start_x + m):
                for y in range(start_y, start_y + m):
                    if 0 <= x < n and 0 <= y < n:
                        key_x = x - start_x
                        key_y = y - start_y

                        if key[key_x][key_y] == 1 and lock[x][y] == 1:
                            break

                        if key[key_x][key_y] == 1 and lock[x][y] == 0:
                            count += 1
            if count == target_count:
                return True
    return False

# 시계 방향으로 돌린다.
def rotate(key):
    m = len(key)
    result = [[0] * m for _ in range(m)]

    for x in range(m):
        for y in range(m):
            result[y][m-1-x] = key[x][y]

    return result
