# 문제링크
https://school.programmers.co.kr/learn/courses/30/lessons/42587

## 문제 접근법
1. priorities 내부에 있는 요소들을 중복된 값을 제거후, 큰 숫자대로 정렬한 후, result 벡터에 저장
2. location 값을 이용해서 result 벡터 내부에 있는 값과 동일한 값을 찾은 후, 그 순번을 answer에 저장후 반환

## 오답노트

```cpp
#include <string>
#include <vector>

using namespace std;

#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> priorities, int location) {
    int answer = 0;
    vector<int> result;

    for (int i = 0; i < priorities.size(); i++) {
        result.push_back(priorities[i]);
    }
    sort(result.begin(), result.end());
    result.erase(unique(result.begin(), result.end()), result.end());

    sort(result.begin(), result.end(), greater<int>());

    int target = priorities[location];

    for (int i = 0; i < result.size(); i++) {
        if (result[i] == target) {
            answer = i + 1;
            break;
        }
    }

    return answer;
}
```
### 틀린이유
처음에는 중복된 값을 제거한 후, 큰 숫자대로 정렬하면 되는줄 알았으나, 제거를 진행할 경우
첫번째 예제는 통과가 되지만 두번째 예제를 통과할 수 없는 오류가 생김
이는 중복된 우선순위도 똑같이 우선순위가 부여되기 때문에 이를 무시하는 경우가 생긴다.

### 다른 접근법
그렇기에 단순히 우선순위만 저장하는 것이 아니라 인덱스값 또한 저장을 하여 중복 우선순위를 고려한다.

### 해답 코드
```cpp
#include <string>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int solution(vector<int> priorities, int location) {
    int answer = 0;
    queue<pair<int, int>> q;

    for (int i = 0; i < priorities.size(); i++) {
        q.push({i, priorities[i]});
    }

    while (!q.empty()) {
        int index = q.front().first;
        int priority = q.front().second;
        q.pop();

        bool hasHigher = false;

        queue<pair<int, int>> temp = q;

        while (!temp.empty()) {
            if (temp.front().second > priority) {
                hasHigher = true;
                break;
            }
            temp.pop();
        }
        
        if (hasHigher) {
            q.push({index, priority});
        }
        else {
            answer++;

            if (index == location) {
                return answer;
            }
        }
    }

    return answer;
}
```