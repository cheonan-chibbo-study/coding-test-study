from collections import deque, defaultdict

def solution(begin, target, words):
    graph = defaultdict(set)
    for word in words:
        for i in range(len(word)):
            graph[i].add(word[i])

    # 메인 로직
    if target not in words:
        return 0

    dq = deque([(begin, 0)])
    visited = set([begin])

    while dq:
        cur_w, step = dq.popleft()

        if cur_w == target:
            return step

        for i in range(len(cur_w)):
            for next_ch in graph[i]:
                cur_w_list = list(cur_w)
                cur_w_list[i] = next_ch
                next_w = ''.join(cur_w_list)

                if next_w not in words or next_w in visited:
                    continue

                dq.append((next_w, step + 1))
                visited.add(next_w)

    return 0