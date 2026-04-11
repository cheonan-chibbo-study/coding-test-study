from collections import deque

def solution(begin, target, words):
    # 전역 데이터
    word_size = len(begin)
    board = [set() for _ in range(word_size)]
    for word in words:
        for i in range(word_size):
            board[i].add(word[i])

    # 메서드
    def bfs():
        dq = deque()
        visited = set()
        dq.append((begin, 0))
        visited.add(begin)

        while dq:
            cur_w, cur_s = dq.popleft()

            if cur_w == target:
                return cur_s

            for i in range(word_size):
                for next_c in board[i]:
                    next_w = cur_w[:i] + next_c + cur_w[i + 1:]

                    if next_w in words and next_w not in visited:
                        dq.append((next_w, cur_s + 1))
                        visited.add(next_w)

        return 0

    # 메인 로직
    if target not in words:
        return 0

    return bfs()