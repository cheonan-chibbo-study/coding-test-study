# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/131127

# 문제 접근법

원하는 상품과 수량을 `map`에 저장하였다.

이후 할인 목록을 10일 단위로 한 칸씩 이동하면서 현재 10일 동안 할인하는 상품의 개수를 `map`에 저장하였다.

원하는 상품의 개수와 현재 할인 상품의 개수가 모두 일치하는지 확인하고, 조건을 만족하면 정답을 증가시켰다.

# 소스코드

```cpp
#include <string>
#include <vector>
#include <map>

using namespace std;

int solution(vector<string> want, vector<int> number, vector<string> discount) {
    int answer = 0;

    map<string, int> wantMap;

    // 원하는 상품 저장
    for(int i = 0; i < want.size(); i++)
    {
        wantMap[want[i]] = number[i];
    }

    // 시작 날짜 이동
    for(int start = 0;
        start <= discount.size() - 10;
        start++)
    {
        map<string, int> discountMap;

        // 10일 동안 할인 상품 개수 세기
        for(int i = start;
            i < start + 10;
            i++)
        {
            discountMap[discount[i]]++;
        }

        bool ok = true;

        // 원하는 상품 비교
        for(int i = 0; i < want.size(); i++)
        {
            if(discountMap[want[i]]
               != wantMap[want[i]])
            {
                ok = false;
                break;
            }
        }

        if(ok)
            answer++;
    }
    return answer;
}
```

# 새로 알게 된 점

* `map`을 이용하여 상품별 개수를 쉽게 관리할 수 있다.
* 연속된 구간을 탐색하는 문제는 슬라이딩 윈도우 방식으로 접근할 수 있다.
* 특정 구간을 반복해서 검사하는 문제는 구간의 시작점을 이동시키면서 해결할 수 있다.

# 느낀점

복잡한 알고리즘보다는 `map`과 반복문을 활용하는 구현 문제였다. 연속된 10일 구간을 하나씩 이동시키며 확인하는 슬라이딩 윈도우 개념을 연습할 수 있었다.