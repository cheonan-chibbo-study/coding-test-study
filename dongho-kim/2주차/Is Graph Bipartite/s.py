class Solution:
    def isBipartite(self, graph: List[List[int]]) -> bool:
        n = len(graph)

        # 0: 미방문, 1: 색상A, -1: 색상B
        color = [0] * n

        for i in range(n):
            if color[i] == 0:
                if not self.dfs(i, 1, graph, color):
                    return False
        return True

    def dfs(self, curr_node, curr_color, graph, color):
        color[curr_node] = curr_color

        for nxt_node in graph[curr_node]:
            if color[nxt_node] == color[curr_node]:
                return False

            if color[nxt_node] == 0:
                if not self.dfs(nxt_node, curr_color * -1, graph, color):
                    return False

        return True
