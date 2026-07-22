class Solution {
public:
    int nearestExit(vector<vector<char>>& maze, vector<int>& entrance) {
        int n = maze.size();
        int m = maze[0].size();

        queue<tuple<int, int, int>> q;
        vector<vector<bool>> visited(n, vector<bool>(m, false));

        int dr[4] = {-1, 1, 0, 0};
        int dc[4] = {0, 0, -1, 1};

        // 시작점
        q.push({entrance[0], entrance[1], 0});
        visited[entrance[0]][entrance[1]] = true;

        while (!q.empty()) {

            auto cur = q.front();
            q.pop();

            int r = get<0>(cur);
            int c = get<1>(cur);
            int dist = get<2>(cur);

            // 현재 위치가 출구인지 확인
            if ((r == 0 || r == n - 1 || c == 0 || c == m - 1) &&
                !(r == entrance[0] && c == entrance[1])) {
                return dist;
            }

            // 상하좌우 탐색
            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                // 범위 확인
                if (nr < 0 || nr >= n || nc < 0 || nc >= m)
                    continue;

                // 벽이면 이동 불가
                if (maze[nr][nc] == '+')
                    continue;

                // 이미 방문한 칸이면 건너뜀
                if (visited[nr][nc])
                    continue;

                visited[nr][nc] = true;
                q.push({nr, nc, dist + 1});
            }
        }

        return -1;
    }
};