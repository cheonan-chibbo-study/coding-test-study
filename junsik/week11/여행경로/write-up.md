# 문제

주어진 항공권을 모두 정확히 한 번씩 사용하여 여행 경로를 완성하는 문제이다. 가능한 경로가 여러 개라면 알파벳 순으로 가장 앞서는 경로를 반환해야 한다.

# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/43164

# 문제 접근법

항공권을 사전순으로 정렬한 후 DFS와 백트래킹을 이용하여 모든 가능한 경로를 탐색하였다.

현재 공항에서 출발할 수 있는 티켓을 하나 선택하고, 해당 티켓을 방문 처리한 뒤 다음 공항으로 이동하였다. 모든 티켓을 사용하면 현재까지의 경로를 정답으로 저장하고 탐색을 종료하였다.

탐색이 끝난 후에는 방문 처리를 해제하고 경로에서 마지막 공항을 제거하여 다른 경우의 수를 탐색하였다.

또한 사전순으로 정렬된 상태에서 첫 번째로 완성된 경로가 정답이므로 `finished` 변수를 이용하여 정답을 찾은 이후에는 불필요한 탐색을 하지 않도록 구현하였다.

# 소스코드

```cpp
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<bool> visited;
vector<string> path;
vector<string> answer;
bool finished;

void DFS(string c, vector<vector<string>> &tickets){
    if(path.size() == tickets.size() + 1){
        answer = path;
        finished = true;
        return;
    }
    
    for(int i = 0; i<tickets.size(); i++){
        if(finished){
            return;
        }
        if(visited[i] == false && c == tickets[i][0]){
            visited[i] = true;
            path.push_back(tickets[i][1]);
            DFS(tickets[i][1], tickets);
            visited[i] = false;
            path.pop_back();
        }
    }
}

vector<string> solution(vector<vector<string>> tickets) {
    visited = vector<bool>(tickets.size(), false);
    sort(tickets.begin(), tickets.end());
    path.push_back("ICN");
    
    DFS("ICN", tickets);
    
    return answer;
}
```

# 전에 풀었던 것과 달라진 점

* 이전에는 DFS의 전체 구조를 이해하는 데 집중했다면, 이번에는 직접 DFS와 백트래킹을 구현하였다.
* `visited`를 DFS 함수 내부에서 생성하면 재귀 호출마다 초기화되어 방문 정보가 유지되지 않는다는 점을 직접 확인하고, 전역으로 선언한 뒤 `solution()`에서 한 번만 초기화하도록 수정하였다.
* 사전순으로 첫 번째 경로가 정답이라는 점을 이용하여 `finished` 플래그를 추가하고, 정답을 찾은 이후에는 즉시 재귀를 종료하도록 구현하였다.
* 공항이 아니라 **티켓 단위**로 방문 여부를 관리해야 한다는 이유를 직접 코드로 적용하며 이해하였다.

# 느낀점

처음 풀었을 때는 DFS의 흐름을 이해하는 수준이었다면, 이번에는 직접 구현하면서 백트래킹이 왜 필요한지 더 명확하게 이해할 수 있었다. 특히 방문 배열의 생성 위치와 재귀 종료 시점을 직접 디버깅하면서 확인한 것이 큰 도움이 되었고, 앞으로 DFS 문제를 풀 때는 종료 조건, 방문 처리, 재귀 호출, 복구의 순서를 자연스럽게 떠올릴 수 있을 것 같다.
