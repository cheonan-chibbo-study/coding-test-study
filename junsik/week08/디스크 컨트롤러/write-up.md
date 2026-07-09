# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/42627

# 문제 접근법

먼저 작업들을 요청 시간 기준으로 정렬하였다.

현재 시간까지 요청된 작업들을 모두 최소 힙(Priority Queue)에 저장하고, 힙에는 작업 시간이 가장 짧은 작업이 먼저 나오도록 `(작업시간, 요청시간)` 형태로 저장하였다.

힙이 비어 있지 않다면 작업 시간이 가장 짧은 작업을 꺼내 실행하고, 현재 시간을 작업 시간만큼 증가시킨 뒤 `(현재 시간 - 요청 시간)`을 총 소요 시간에 더하였다.

만약 현재 시간까지 요청된 작업이 하나도 없다면 실행할 작업이 없으므로 현재 시간을 1 증가시켜 다음 작업이 도착할 때까지 기다렸다.

모든 작업을 처리한 후 총 소요 시간을 작업 개수로 나누어 평균 반환 시간을 구하였다.

# 소스코드

```cpp
#include <string>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int solution(vector<vector<int>> jobs) {
    sort(jobs.begin(), jobs.end());

    priority_queue<
    pair<int,int>,
    vector<pair<int,int>>,
    greater<pair<int,int>>> pq;

    int time = 0;
    int idx = 0;
    int sum = 0;

    while (idx < jobs.size() || !pq.empty())
    {
        // 현재 시간까지 들어온 작업을 모두 힙에 넣기
        while (idx < jobs.size() && jobs[idx][0] <= time)
        {
            pq.push({jobs[idx][1], jobs[idx][0]});
            idx++;
        }

        // 실행 가능한 작업이 있다면
        if (!pq.empty())
        {
            int work = pq.top().first;     // 작업시간
            int request = pq.top().second; // 요청시간
            pq.pop();

            time += work;
            sum += (time - request);
        }
        else
        {
            // 아직 도착한 작업이 없으면 시간만 증가
            time++;
        }
    }

    return sum / jobs.size();
}
```

# 새로 알게 된 점

* `sort()`를 이용하여 요청 시간 기준으로 작업을 먼저 정렬할 수 있다.
* `priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>>`를 이용하면 작업 시간이 가장 짧은 작업을 먼저 꺼낼 수 있는 최소 힙을 만들 수 있다.
* 힙에는 `(요청시간, 작업시간)`이 아니라 **`(작업시간, 요청시간)`** 순으로 저장해야 작업 시간을 기준으로 자동 정렬된다.
* 현재 시간까지 도착한 작업만 힙에 넣고, 그중 작업 시간이 가장 짧은 작업을 선택하는 것이 핵심 아이디어이다.
* 실행 가능한 작업이 없을 때는 현재 시간을 증가시켜 다음 작업이 도착할 때까지 기다려야 한다.

# 느낀점

처음에는 구현 방법이 잘 떠오르지 않았지만, 가사코드로 전체 흐름을 정리한 뒤 구현하니 문제를 이해하기 쉬웠다. 특히 Priority Queue를 이용해 현재 실행 가능한 작업 중 가장 짧은 작업을 선택하는 방법을 배우면서, 힙이 어떤 상황에서 사용되는지 확실히 이해할 수 있었다. 앞으로도 우선순위가 가장 높은 작업을 반복해서 선택하는 문제가 나오면 Priority Queue를 먼저 떠올려야겠다고 느꼈다.
