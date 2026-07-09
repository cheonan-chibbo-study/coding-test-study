#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<string> answer;
vector<string> path;
vector<bool> visited;
bool finished = false;

void dfs(string now, vector<vector<string>>& tickets)
{
    if (path.size() == tickets.size() + 1)
    {
        answer = path;
        finished = true;
        return;
    }

    for (int i = 0; i < tickets.size(); i++)
    {
        if (finished)
            return;

        if (!visited[i] && tickets[i][0] == now)
        {
            visited[i] = true;
            path.push_back(tickets[i][1]);

            dfs(tickets[i][1], tickets);

            visited[i] = false;
            path.pop_back();
        }
    }
}

vector<string> solution(vector<vector<string>> tickets)
{
    // 출발지가 같으면 도착지를 기준으로,
    // 출발지가 다르면 출발지를 기준으로 정렬
    sort(tickets.begin(), tickets.end());

    visited.resize(tickets.size(), false);

    path.push_back("ICN");

    dfs("ICN", tickets);

    return answer;
}