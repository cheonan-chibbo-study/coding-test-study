#include <string>
#include <vector>

using namespace std;

int answer = 0;
bool visited[8];

void DFS(int k, vector<vector<int>>& dungeons, int count)
{
    answer = max(answer, count);

    for(int i = 0; i < dungeons.size(); i++)
    {
        if(visited[i])
            continue;

        if(k < dungeons[i][0])
            continue;

        visited[i] = true;

        DFS(
            k - dungeons[i][1],
            dungeons,
            count + 1
        );

        visited[i] = false;
    }
}

int solution(int k, vector<vector<int>> dungeons)
{
    DFS(k, dungeons, 0);

    return answer;
}