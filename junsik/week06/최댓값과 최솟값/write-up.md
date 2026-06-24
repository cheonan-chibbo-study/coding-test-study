# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/12939

# 문제 접근법

`stringstream`을 이용하여 공백을 기준으로 문자열을 정수로 변환하였다.

첫 번째 숫자를 기준으로 최댓값과 최솟값을 초기화한 뒤, 나머지 숫자들을 순회하면서 `min()`과 `max()` 함수를 이용해 값을 갱신하였다.

마지막으로 `to_string()`을 사용하여 문자열 형태로 변환한 후 반환하였다.

# 소스코드

```cpp
#include <string>
#include <sstream>
#include <algorithm>

using namespace std;

string solution(string s) {
    string answer = "";
    stringstream ss(s);
    int num;
    
    //첫번째 숫자 읽어오기
    ss>>num;
    
    int maxValue = num;
    int minValue = num;
    
    while(ss >> num){
        maxValue = max(maxValue,num);
        minValue = min(minValue,num);
    }
    
    answer = to_string(minValue) + " " + to_string(maxValue);
    
    return answer;
}
```

# 새로 알게 된 점

* `stringstream`을 이용하면 공백 기준으로 문자열을 쉽게 분리할 수 있다.
* `min()`, `max()` 함수를 사용하여 최솟값과 최댓값을 간단하게 구할 수 있다.
* `to_string()`을 이용하여 정수를 문자열로 변환할 수 있다.

# 느낀점

복잡한 알고리즘보다는 문자열 처리와 C++ 문법 활용이 중요한 문제였다. 특히 첫 번째 값을 이용하여 최댓값과 최솟값을 초기화하는 방법을 익힐 수 있었다.
