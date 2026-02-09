# Gemini가 짠 코드
def solution(info, edges):
    n = len(info)
    graph = [[] for _ in range(n)]
    for p, c in edges:
        graph[p].append(c)

    max_sheep = 0

    def dfs(sheep, wolf, current_candidates):
        nonlocal max_sheep
        max_sheep = max(max_sheep, sheep)

        # 현재 갈 수 있는 후보 노드들을 하나씩 탐색
        for i, nxt in enumerate(current_candidates):
            # 다음 노드가 양인지 늑대인지 확인
            is_wolf = info[nxt]

            # 늑대라면 현재 양의 수와 비교하여 이동 가능한지 체크
            if is_wolf and sheep <= wolf + 1:
                continue

            # 다음 경로를 위한 새로운 후보 리스트 생성
            # 1. 현재 후보군에서 방문할 노드(nxt) 제외
            # 2. 방문할 노드의 자식들을 새롭게 추가
            new_candidates = current_candidates[:i] + current_candidates[i+1:]
            new_candidates.extend(graph[nxt])

            if is_wolf:
                dfs(sheep, wolf + 1, new_candidates)
            else:
                dfs(sheep + 1, wolf, new_candidates)

    # 초기 상태: 0번 노드 방문 (이미 양 1마리 확보), 후보는 0번의 자식들
    dfs(1, 0, graph[0])

    return max_sheep
