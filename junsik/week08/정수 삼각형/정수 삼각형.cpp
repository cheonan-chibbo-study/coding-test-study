#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<vector<int>> triangle) {
    vector<vector<int>> dp = triangle;
    dp[0][0] = triangle[0][0];

    for(int i = 1; i<triangle.size(); i++)
    {
        for(int j = 0; j<=i; j++)
        {
            // 맨 왼쪽
            if(j == 0)
            {
                dp[i][j] = dp[i-1][j] + triangle[i][j];
            }
            // 맨 오른쪽
            else if(j == i)
            {
                dp[i][j] = dp[i-1][j-1] + triangle[i][j];
            }
            // 가운데
            else
            {
                dp[i][j] = max(dp[i-1][j],dp[i-1][j-1]) + triangle[i][j];
            }
        }
    }
    return *max_element(dp.back().begin(), dp.back().end());
}