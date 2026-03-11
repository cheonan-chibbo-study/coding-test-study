def solution(info, edges):
    n = len(info)
    graph = [[] for _ in range(n)]
    for parent, child in edges:
        graph[parent].append(child)

    # 메모이제이션 배열: 2^n 크기 (방문한 노드 집합을 인덱스로 사용)
    # -1: 미방문, 0: 방문 불가(늑대 >= 양), 그 외: 해당 집합에서 모은 최대 양의 수
    memo = [-1] * (1 << n)

    # mask: 각각의 노드 번호를 i라고 할 때, 지금까지 방문한 2^i 값을 모두 더한 정수 값
    def recurse(mask):
        # 이미 계산된 상태라면 결과 반환
        if memo[mask] != -1:
            return memo[mask]

        # 현재 마스크 상태에서 양과 늑대의 수 계산
        sheep = 0
        wolf = 0
        for curr in range(n):
            if (mask >> curr) & 1: # 방문한 경우
                if info[curr] == 0:
                    sheep += 1
                else:
                    wolf += 1

        # 늑대가 양보다 같거나 많으면 실패 (양 0마리 반환)
        if wolf >= sheep:
            memo[mask] = 0
            return 0

        # 현재 상태의 양의 수를 기본값으로 설정
        result = sheep

        # 다음에 갈 수 있는 노드 찾기
        # 이미 방문한 노드(mask의 bit i가 1)들의 자식들 중, 아직 방문하지 않은 노드 탐색
        for curr in range(n):
            if (mask >> curr) & 1: # curr 노드를 방문한 경우
                for nxt in graph[curr]:
                    if not (mask & (1 << nxt)): # 방문하지 않았다면
                        # 다음 노드를 추가한 상태로 재귀 호출
                        result = max(result, recurse(mask | (1 << nxt)))

        memo[mask] = result
        return result

    # 0번 노드(루트)부터 시작 (비트마스크 1)
    return recurse(1)
