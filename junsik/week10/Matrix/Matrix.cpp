class Solution {
public:
    vector<vector<int>> updateMatrix(vector<vector<int>>& mat) {
        int n = mat.size();
        int m = mat[0].size();

        queue<pair<int, int>> q;
        vector<vector<int>> distance(n, vector<int>(m, -1));

        int dr[4] = {-1, 1, 0, 0};
        int dc[4] = {0, 0, -1, 1};

        // 모든 0을 시작점으로 설정
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    q.push({i, j});
                    distance[i][j] = 0;
                }
            }
        }

        // Multi-Source BFS
        while (!q.empty()) {

            pair<int, int> cur = q.front();
            q.pop();

            int r = cur.first;
            int c = cur.second;

            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                // 범위 확인
                if (nr < 0 || nr >= n || nc < 0 || nc >= m)
                    continue;

                // 아직 방문하지 않은 칸
                if (distance[nr][nc] == -1) {

                    distance[nr][nc] = distance[r][c] + 1;
                    q.push({nr, nc});
                }
            }
        }

        return distance;
    }
};
