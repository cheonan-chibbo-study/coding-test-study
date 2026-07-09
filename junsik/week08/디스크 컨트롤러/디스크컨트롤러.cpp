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