from collections import deque

def solution(rectangle, characterX, characterY, itemX, itemY):
    # 메서드
    def get_board():
        # 1. 넉넉한 고정 크기 배열 선언 (최대 50 * 2 = 100이므로 102면 충분)
        board = [[-1] * 102 for _ in range(102)]

        # 2. 오프셋 없이 바로 2배율 적용하며 그리기 (하나의 루프로 압축!)
        for x1, y1, x2, y2 in rectangle:
            x1, y1, x2, y2 = x1 * 2, y1 * 2, x2 * 2, y2 * 2

            for row in range(y1, y2 + 1):
                for col in range(x1, x2 + 1):
                    # x1, x2, y1, y2 경계선 안쪽이면 무조건 내부(0)
                    if y1 < row < y2 and x1 < col < x2:
                        board[row][col] = 0
                    # 내부에 해당하지 않는 모서리(테두리)이면서, 다른 사각형의 내부가 아닐 때만 1
                    elif board[row][col] != 0:
                        board[row][col] = 1

        return board

    # 3. BFS 탐색
    def bfs():
        dq = deque()
        visited = [[False] * 102 for _ in range(102)]

        start_r, start_c = characterY * 2, characterX * 2
        target_r, target_c = itemY * 2, itemX * 2

        dq.append((start_r, start_c, 0))
        visited[start_r][start_c] = True

        while dq:
            cur_r, cur_c, cur_s = dq.popleft()

            if cur_r == target_r and cur_c == target_c:
                return cur_s // 2

            for dr, dc in [[-1, 0], [0, 1], [0, -1], [1, 0]]:
                next_r, next_c = cur_r + dr, cur_c + dc

                # 고정 배열이므로 범위 체크가 매우 단순해짐
                if 0 <= next_r < 102 and 0 <= next_c < 102:
                    if board[next_r][next_c] == 1 and not visited[next_r][next_c]:
                        dq.append((next_r, next_c, cur_s + 1))
                        visited[next_r][next_c] = True
        return -1

    # 메인 로직
    board = get_board()
    return bfs()