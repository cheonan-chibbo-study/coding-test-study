# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/131701

# 문제 접근법

원형 수열을 처리하기 위해 기존 배열을 한 번 더 이어 붙여 원형 구조를 일반 배열처럼 만들었다.

길이 1부터 수열의 길이까지 모든 연속 부분 수열을 탐색하며 각 구간의 합을 계산하였다.

계산한 합은 `set`에 저장하여 중복을 자동으로 제거하였고, 마지막에 `set`의 크기를 반환하였다.

# 소스코드

```cpp
#include <string>
#include <vector>
#include <set>

using namespace std;

int solution(vector<int> elements) {
    set<int> sums;

    int n = elements.size();

    // 원형 처리를 위해 뒤에 한 번 더 붙이기
    for(int i = 0; i < n; i++)
    {
        elements.push_back(elements[i]);
    }

    // 길이 1 ~ n
    for(int len = 1; len <= n; len++)
    {
        // 시작 위치
        for(int start = 0; start < n; start++)
        {
            int sum = 0;

            for(int i = start; i < start + len; i++)
            {
                sum += elements[i];
            }

            sums.insert(sum);
        }
    }

    return sums.size();
}
```

# 새로 알게 된 점

* `set`은 중복된 값을 자동으로 제거하며, `insert()`를 사용해 값을 저장할 수 있다.
* 원형 배열은 배열을 한 번 더 이어 붙이면 일반 배열처럼 연속 구간을 처리할 수 있다.
* `set.size()`를 이용하면 중복을 제거한 원소의 개수를 바로 구할 수 있다.
* 구간합을 여러 번 계산하는 문제에서는 누적합(Prefix Sum)을 사용하면 시간복잡도를 줄일 수 있다는 것을 알게 되었다.

# 느낀점

처음에는 어떤 자료구조를 사용해야 할지 고민했지만, `set`을 이용하면 중복 제거를 따로 구현하지 않아도 되어 풀이가 훨씬 간단해졌다. 또한 원형 배열을 두 번 이어 붙이는 아이디어와 누적합이라는 새로운 기법도 배울 수 있었던 문제였다.
