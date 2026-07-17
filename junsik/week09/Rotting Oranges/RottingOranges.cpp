class Solution {
public:
    int orangesRotting(vector<vector<int>>& grid) {
        queue<pair<int,int>> oranges;
        int fresh = 0;
        int minute = 0;
        int dr[4] = {-1, 1, 0, 0};
        int dc[4] = {0, 0, -1, 1};

        for(int i = 0; i<grid.size(); i++){
            for(int j = 0; j<grid[0].size(); j++){
                if(grid[i][j] == 2)
                    oranges.push({i,j});
                if(grid[i][j] == 1)
                    fresh++;
            }
        }

        while (!oranges.empty() && fresh > 0){
            int size = oranges.size();
            
            for(int i = 0; i<size; i++){
                pair<int, int> cur = oranges.front();
                oranges.pop();
                int r = cur.first;
                int c = cur.second;

                for(int index = 0; index<4; index++){
                    int nr = r + dr[index];
                    int nc = c + dc[index];

                    if (nr < 0 || nr >= grid.size() || 
                    nc < 0 || nc >= grid[0].size()){
                        continue;
                    }

                    if(grid[nr][nc] == 1){
                        grid[nr][nc] = 2;        // 썩게 만들기
                        oranges.push({nr, nc});  // 다음 분에 처리하도록 Queue에 추가
                        fresh--;                 // 신선한 오렌지 하나 감소
                    }
                }
            }
            minute++;
        }
        if (fresh == 0)
            return minute;

        else
            return -1;
    }
};