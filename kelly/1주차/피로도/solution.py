def solution(k, dungeons):
    # 전역 값
    answer = 0
    
    # 메서드
    def search(k, count, visited):
        nonlocal answer
        answer = max(answer, count)

        for i in range(len(dungeons)):
            needHp, useHp = dungeons[i]
            if visited[i] or k < needHp:
                continue

            visited[i] = True
            search(k - useHp, count + 1, visited)
            visited[i] = False
    
    # 메인 로직
    visited = [False for _ in dungeons]
    search(k, 0, visited)

    return answer