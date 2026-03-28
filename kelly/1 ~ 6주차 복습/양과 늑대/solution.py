def solution(info, edges):
    # 메서드
    def search(s, w, cur_v):
        nonlocal answer

        if info[cur_v] == 0:
            s += 1
        else:
            w += 1

        if w >= s:
            return

        answer = max(answer, s)
        for start, end in edges:
            if start in visited and (start, end) not in candi:
                visited.append(end)
                candi.add((start, end))
                search(s, w, end)

                visited.pop()
                candi.remove((start, end))

    # 메인 로직
    visited = [0]
    candi = set()
    answer = 0

    search(0, 0, 0)

    return answer