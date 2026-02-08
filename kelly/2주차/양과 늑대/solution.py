def solution(info, edges):
    # 전역 데이터
    answer = 0

    # 메서드
    def back_tracking(s, w, visited):
        nonlocal answer

        if s <= w:
            return

        answer = max(answer, s)

        for p, c in edges:
            if visited[p] and not visited[c]:
                visited[c] = True
                if info[c] == 0:
                    back_tracking(s + 1, w, visited)
                else:
                    back_tracking(s, w + 1, visited)

                visited[c] = False

    # 메인 로직
    visited = [False] * len(info)

    visited[0] = True
    back_tracking(1, 0, visited)

    return answer