class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;

        int[] colors = new int[n];
        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) {
                if (!dfs(i, 1, colors, graph)) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean dfs(int curr, int color, int[] colors, int[][] graph) {
        colors[curr] = color;

        for (int next : graph[curr]) {
            if (colors[next] == 0) {
                if (!dfs(next, color * -1, colors, graph)) {
                    return false;
                }
                continue;
            }

            if (colors[next] == colors[curr]) {
                return false;
            }
        }

        return true;
    }
}
