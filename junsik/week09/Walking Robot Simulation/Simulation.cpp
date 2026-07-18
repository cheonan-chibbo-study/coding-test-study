class Solution {
public:
    int robotSim(vector<int>& commands, vector<vector<int>>& obstacles) {
        // 장애물 저장
        unordered_set<string> obstacleSet;
        for (auto& obs : obstacles) {
            obstacleSet.insert(to_string(obs[0]) + "," + to_string(obs[1]));
        }

        // 북, 동, 남, 서
        int dx[4] = {0, 1, 0, -1};
        int dy[4] = {1, 0, -1, 0};

        int dir = 0;
        int x = 0, y = 0;
        int maxDist = 0;

        for (int cmd : commands) {

            // 왼쪽 회전
            if (cmd == -2) {
                dir = (dir + 3) % 4;
            }
            // 오른쪽 회전
            else if (cmd == -1) {
                dir = (dir + 1) % 4;
            }
            // 이동
            else {
                for (int step = 0; step < cmd; step++) {

                    int nx = x + dx[dir];
                    int ny = y + dy[dir];

                    string key = to_string(nx) + "," + to_string(ny);

                    // 장애물이 있으면 더 이상 이동하지 않음
                    if (obstacleSet.count(key))
                        break;

                    // 이동
                    x = nx;
                    y = ny;

                    maxDist = max(maxDist, x * x + y * y);
                }
            }
        }
        return maxDist;
    }
};