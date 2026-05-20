# 전화번호 목록

## 문제 링크

https://school.programmers.co.kr/learn/courses/30/lessons/42577

## 접근 방법

1. 한 번호가 다른 번호의 접두사인 경우를 찾아야 하므로 각각의 전화번호를 벡터에 저장한다.
2. `sort()`를 이용해 전화번호 목록을 정렬한다.
3. 정렬한 벡터에서 `substr()` 함수를 사용하여 겹치는 요소를 검색한다.

## 다른 풀이

이 문제는 해시 관련 문제이기 때문에 해시를 이용해서도 풀 수 있다.

```cpp
#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

bool solution(vector<string> phone_book)
{
    unordered_map<string, int> map;

    for (int i = 0; i < phone_book.size(); i++)
        map[phone_book[i]] = 1;

    for (int i = 0; i < phone_book.size(); i++)
    {
        for (int j = 0; j < phone_book[i].size() - 1; j++)
        {
            string phone_number = phone_book[i].substr(0, j + 1);

            if (map[phone_number])
                return false;
        }
    }

    return true;
}
```