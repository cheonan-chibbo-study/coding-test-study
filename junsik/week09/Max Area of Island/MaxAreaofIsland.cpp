class Solution {
public:
    int dfs(vector<vector<int>>& grid, int r, int c) {
        if (r < 0 || r >= grid.size() || c < 0 || c >= grid[0].size())
            return 0;

        if (grid[r][c] == 0)
            return 0;

        //방문처리
        grid[r][c] = 0;

        // 상하좌우 탐색
        return 1 + dfs(grid, r - 1, c)
        +dfs(grid, r + 1, c)
        +dfs(grid, r, c - 1)
        +dfs(grid, r, c + 1);
    }

    int maxAreaOfIsland(vector<vector<int>>& grid) {
        int maxArea = 0;

        for (int i = 0; i<grid.size(); i++){
            for (int j = 0; j<grid[0].size(); j++){
                if (grid[i][j] == 1){
                    int area = dfs(grid,i,j);
                    maxArea = max(maxArea, area);
                }
            }
        }
        return maxArea;
    }
};