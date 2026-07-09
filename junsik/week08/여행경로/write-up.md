# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/43164

# 문제 접근법

티켓을 사전순으로 정렬한 뒤, DFS(깊이 우선 탐색)와 백트래킹을 이용하여 모든 가능한 경로를 탐색하였다.

현재 공항에서 출발할 수 있는 티켓을 하나 선택하고, 해당 티켓을 사용 처리한 후 다음 공항으로 이동하였다. 모든 티켓을 사용하면 현재까지의 경로를 정답으로 저장하였다.

탐색 도중 막히는 경우에는 사용했던 티켓을 다시 미사용 상태로 복구하고 다른 티켓을 선택하는 백트래킹을 수행하였다. 티켓을 사전순으로 정렬했기 때문에 처음 완성된 경로가 곧 정답이 된다.

# 소스코드

```cpp
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
```

# 새로 알게 된 점

* DFS에서는 **현재 상태, 종료 조건, 다음 상태**를 먼저 정의하는 것이 중요하다.
* 백트래킹은 탐색 후 `visited`를 원래 상태로 복구하여 다른 경우를 탐색하는 기법이다.
* 이 문제에서는 공항이 아니라 **티켓의 사용 여부**를 `visited` 배열로 관리해야 한다.
* `sort()`로 티켓을 사전순 정렬하면 DFS가 알파벳 순으로 가장 앞서는 경로를 먼저 탐색하므로, 처음 완성된 경로를 그대로 정답으로 사용할 수 있다.
* DFS 문제는 대부분 **종료 조건 → 선택 → 재귀 호출 → 복구**의 공통적인 템플릿을 사용한다.

# 느낀점

처음에는 티켓을 단순히 이어 붙이는 방식으로 해결하려고 했지만, 모든 티켓을 반드시 한 번씩 사용해야 한다는 조건 때문에 DFS와 백트래킹이 필요하다는 것을 이해하게 되었다. 또한 여러 DFS 문제가 비슷한 구조를 가진다는 것을 알게 되어, 앞으로는 공통 템플릿을 바탕으로 현재 상태와 종료 조건만 문제에 맞게 바꾸어 적용해 볼 수 있을 것 같다.
