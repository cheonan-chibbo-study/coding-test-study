#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(vector<int> scoville, int K) {
    int answer = 0;

    priority_queue<int, vector<int>, greater<int>> pq;

    // 모든 음식 삽입
    for (int x : scoville)
        pq.push(x);

    while (!pq.empty() && pq.top() < K) {
        // 섞을 음식이 부족한 경우
        if (pq.size() < 2)
            return -1;

        int first = pq.top();
        pq.pop();

        int second = pq.top();
        pq.pop();

        pq.push(first + second * 2);
        answer++;
    }

    return answer;
}