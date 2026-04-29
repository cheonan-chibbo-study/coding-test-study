class Solution {

    int d_size;
    int k;
    int[][] dungeons;

    int answer = 0;

    public int solution(int k, int[][] dungeons) {
        this.k = k;
        this.dungeons = dungeons;
        this.d_size = dungeons.length;

        // 메인 로직
        boolean[] visited = new boolean[d_size];
        search(visited, 0);

        return answer;
    }

    private void search(boolean[] visited, int count) {
        answer = Math.max(answer, count);

        for (int i = 0; i < d_size; i++) {
            if (visited[i] || k < dungeons[i][0]) {
                continue;
            }

            visited[i] = true;
            k -= dungeons[i][1];
            search(visited, count + 1);

            visited[i] = false;
            k += dungeons[i][1];
        }
    }
}