# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/12941

# 문제 접근법

합을 최소로 만들기 위해서는 큰 수와 작은 수를 서로 곱해야 한다.

한 배열은 오름차순으로, 다른 배열은 내림차순으로 정렬한 뒤 같은 인덱스의 원소를 곱하여 모두 더하였다.

이 방법을 사용하면 각 원소를 한 번씩만 사용하면서 최솟값을 만들 수 있다.

# 소스코드

```cpp
#include <iostream>
#include<vector>
#include<algorithm>
using namespace std;

int solution(vector<int> A, vector<int> B)
{
    int answer = 0;

    sort(A.begin(), A.end(), greater<>());
    sort(B.begin(), B.end());

    for(int i = 0; i<A.size(); i++){
        answer += A[i] * B[i];
    }

    return answer;
}
```

# 새로 알게 된 점

* 합을 최소로 만들기 위해서는 큰 값과 작은 값을 서로 짝지어 곱하는 것이 가장 효율적이다.
* `sort(v.begin(), v.end())`는 오름차순, `sort(v.begin(), v.end(), greater<int>())`는 내림차순 정렬을 수행한다.
* 정렬을 이용하여 최적의 선택을 반복하는 문제는 그리디(Greedy) 알고리즘으로 해결할 수 있다.
* 그리디는 현재 시점에서 가장 좋은 선택을 반복하여 전체 최적해를 구하는 알고리즘이라는 것을 이해하였다.

# 느낀점

처음에는 각 배열의 최댓값끼리 곱해야 할 것이라고 생각했지만, 오히려 큰 수와 작은 수를 짝지어야 합이 최소가 된다는 점을 알게 되었다. 그리디 알고리즘의 대표적인 예제를 통해 정렬만으로도 효율적으로 문제를 해결할 수 있다는 것을 배울 수 있었다.
