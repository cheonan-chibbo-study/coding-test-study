#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(int rows, int columns, vector<vector<int>> queries) {

    vector<int> answer;

    vector<vector<int>> board(
        rows,
        vector<int>(columns)
    );

    int num = 1;

    for(int i = 0; i < rows; i++)
    {
        for(int j = 0; j < columns; j++)
        {
            board[i][j] = num++;
        }
    }

    for(auto q : queries)
    {
        int x1 = q[0] - 1;
        int y1 = q[1] - 1;
        int x2 = q[2] - 1;
        int y2 = q[3] - 1;

        int temp = board[x1][y1];
        int minValue = temp;

        // 왼쪽
        for(int i = x1; i < x2; i++)
        {
            board[i][y1] = board[i+1][y1];
            minValue = min(minValue, board[i][y1]);
        }

        // 아래
        for(int i = y1; i < y2; i++)
        {
            board[x2][i] = board[x2][i+1];
            minValue = min(minValue, board[x2][i]);
        }

        // 오른쪽
        for(int i = x2; i > x1; i--)
        {
            board[i][y2] = board[i-1][y2];
            minValue = min(minValue, board[i][y2]);
        }

        // 위
        for(int i = y2; i > y1; i--)
        {
            board[x1][i] = board[x1][i-1];
            minValue = min(minValue, board[x1][i]);
        }

        board[x1][y1+1] = temp;

        answer.push_back(minValue);
    }

    return answer;
}