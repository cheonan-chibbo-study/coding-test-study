class Solution {
public:
    void dfs(vector<vector<int>>& image, int r, int c, int originalColor, int newColor) {
        // 범위를 벗어난 경우
        if (r < 0 || r >= image.size() || c < 0 || c >= image[0].size())
            return;

        // 시작 색과 다르면 종료
        if (image[r][c] != originalColor)
            return;

        // 색 변경
        image[r][c] = newColor;

        // 상하좌우 탐색
        dfs(image, r - 1, c, originalColor, newColor);
        dfs(image, r + 1, c, originalColor, newColor);
        dfs(image, r, c - 1, originalColor, newColor);
        dfs(image, r, c + 1, originalColor, newColor);
    }

    vector<vector<int>> floodFill(vector<vector<int>>& image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];

        // 이미 같은 색이면 탐색할 필요 없음
        if (originalColor == color)
            return image;

        dfs(image, sr, sc, originalColor, color);

        return image;
    }
};