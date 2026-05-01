class Solution {

    int[] info;
    int[][] edges;
    boolean[] visited;

    int answer = 0;

    public int solution(int[] info, int[][] edges) {
        this.info = info;
        this.edges = edges;
        this.visited = new boolean[info.length];

        // 메인 로직
        visited[0] = true;
        backTracking(1, 0);

        return answer;
    }

    private void backTracking(int yang, int wolf) {
        answer = Math.max(answer, yang);

        for (int[] edge : edges) {
            if (!visited[edge[0]] || visited[edge[1]]) {
                continue;
            }

            int nextY = yang;
            int nextW = wolf;

            if (info[edge[1]] == 0) {
                nextY += 1;
            } else {
                nextW += 1;
            }

            if (nextY <= nextW) {
                continue;
            }

            visited[edge[1]] = true;
            backTracking(nextY, nextW);

            visited[edge[1]] = false;
        }
    }
}