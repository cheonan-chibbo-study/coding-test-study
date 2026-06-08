from collections import defaultdict

def solution(n, wires):
    graph = defaultdict(list)
    for v1, v2 in wires:
        graph[v1].append(v2)
        graph[v2].append(v1)

    # 메서드
    def search(visited, start):
        result = 0
        stack = [start]
        visited.add(start)

        while stack:
            cur = stack.pop()
            result += 1

            for next in graph[cur]:
                if next in visited:
                    continue

                stack.append(next)
                visited.add(next)

        return result

    # 메인 로직
    answer = float('inf')

    for v1, v2 in wires:
        graph[v1].remove(v2)
        graph[v2].remove(v1)

        tree_count = []
        visited = set()
        for node in graph.keys():
            if node not in visited:
                tree_count.append(search(visited, node))

        answer = min(answer, abs(tree_count[0] - tree_count[1]))

        graph[v1].append(v2)
        graph[v2].append(v1)

    return answer