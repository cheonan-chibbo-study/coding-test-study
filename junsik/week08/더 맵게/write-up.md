# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/42626

# 문제 접근법

가장 맵지 않은 두 음식을 반복해서 선택해야 하므로 최소 힙(Priority Queue)을 사용하였다.

먼저 모든 스코빌 지수를 최소 힙에 저장한 뒤, 가장 작은 값이 `K`보다 작을 동안 반복하였다.

반복문에서는 가장 작은 두 값을 꺼내 새로운 스코빌 지수를 계산하여 다시 힙에 넣고, 섞은 횟수를 증가시켰다.

만약 힙에 음식이 1개만 남았는데도 `K` 이상을 만들 수 없다면 `-1`을 반환하였다.

# 소스코드

```cpp
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
```

# 새로 알게 된 점

* `priority_queue<int, vector<int>, greater<int>>`를 이용하면 최소 힙을 만들 수 있다.
* `top()`은 가장 작은 값을 확인하고, `pop()`은 가장 작은 값을 제거하며, `push()`는 새로운 값을 삽입한다.
* 가장 작은 값이나 가장 큰 값을 반복해서 꺼내야 하는 문제에서는 Priority Queue를 사용하는 것이 효율적이다.
* 힙을 이용하면 삽입과 삭제를 효율적으로 수행할 수 있어 반복적으로 최솟값을 선택하는 문제를 빠르게 해결할 수 있다.

# 느낀점

이번 문제를 통해 Priority Queue를 처음 사용해 보았다. 처음에는 생소했지만 `top()`, `pop()`, `push()` 세 가지 연산만으로 반복적으로 최솟값을 관리할 수 있다는 점을 이해할 수 있었다. 앞으로 최솟값이나 최댓값을 계속 선택해야 하는 문제가 나오면 Priority Queue를 먼저 떠올려야겠다고 느꼈다.
