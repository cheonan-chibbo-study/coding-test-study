from collections import deque

def solution(board):
    # 전역 데이터
    N = len(board)
    visited = set()
    new_board = [[0] * (N + 2) for _ in range(N + 2)]
    for row in range(0, len(new_board)):
        for col in range(0, len(new_board[row])):
            if row == 0 or row == len(new_board) - 1 or col == 0 or col == len(new_board[row]) - 1:
                new_board[row][col] = 1
            else:
                new_board[row][col] = board[row - 1][col - 1]

    # 메서드
    def get_next(cur):
        result = []
        c1, c2, c_step = cur

        # 상/하/좌/우 이동
        for dr, dc in ((-1, 0), (0, 1), (0, -1), (1, 0)):
            n1 = (c1[0] + dr, c1[1] + dc)
            n2 = (c2[0] + dr, c2[1] + dc)

            if is_safe(n1, n2):
                result.append((n1, n2, c_step + 1))

        # 회전
        rotted = []
        # 현재 가로 모드면 세로 모드로 회전
        if c1[0] == c2[0]:
            if new_board[c1[0] - 1][c1[1]] == 0 and new_board[c2[0] - 1][c2[1]] == 0:
                rotted.append((c1, (c1[0] - 1, c1[1]), c_step + 1))
                rotted.append(((c2[0] - 1, c2[1]), c2, c_step + 1))

            if new_board[c1[0] + 1][c1[1]] == 0 and new_board[c2[0] + 1][c2[1]] == 0:
                rotted.append((c1, (c1[0] + 1, c1[1]), c_step + 1))
                rotted.append(((c2[0] + 1, c2[1]), c2, c_step + 1))
        # 현재 세로 모드면 가로 모드로 회전
        else:
            if new_board[c1[0]][c1[1] - 1] == 0 and new_board[c2[0]][c2[1] - 1] == 0:
                rotted.append((c1, (c1[0], c1[1] - 1), c_step + 1))
                rotted.append(((c2[0], c2[1] - 1), c2, c_step + 1))

            if new_board[c1[0]][c1[1] + 1] == 0 and new_board[c2[0]][c2[1] + 1] == 0:
                rotted.append((c1, (c1[0], c1[1] + 1), c_step + 1))
                rotted.append(((c2[0], c2[1] + 1), c2, c_step + 1))

        for rot in rotted:
            if is_safe(rot[0], rot[1]):
                result.append(rot)

        return result

    def is_safe(n1, n2):
        if new_board[n1[0]][n1[1]] != 0 or new_board[n2[0]][n2[1]] != 0:
            return False

        if (n1[0], n1[1], n2[0], n2[1]) in visited or (n2[0], n2[1], n1[0], n1[1]) in visited:
            return False

        return True

    # 메인 로직
    dq = deque()
    start = ((1, 1), (1, 2), 0)

    dq.append(start)
    visited.add((1, 1, 1, 2))
    visited.add((1, 2, 1, 1))

    while dq:
        cur = dq.popleft()

        if (N, N) in cur:
            return cur[2]

        for next in get_next(cur):
            dq.append(next)
            visited.add((next[0][0], next[0][1], next[1][0], next[1][1]))
            visited.add((next[1][0], next[1][1], next[0][0], next[0][1]))

    return -4444