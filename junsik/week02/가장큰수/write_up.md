# 문제링크
https://school.programmers.co.kr/learn/courses/30/lessons/42746

## 문제 접근법
1. 정수 배열로 주어진 것들을 string타입으로 변환하여 다시 저장한다.
2. 이를 정렬하면서 compare이라는 bool함수를 선언하여 만들어지는 경우의 수의 크기 비교를 한다.
3. 크기 비교를 하여 제일 큰 숫자가 나올 수 있는 경우의 수를 answer에 저장한다.

### 소스코드
```cpp
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool compare(string a, string b){
    return a + b > b + a;
}

string solution(vector<int> numbers) {
    string answer = "";
    vector<string> n;
    
    for(int i = 0; i<numbers.size(); i++){
        n.push_back(to_string(numbers[i]));
    }
    sort(n.begin(), n.end(), compare);
    
    for(int i = 0; i<n.size(); i++){
        answer += n[i];
    }
    
    return answer;
}
```

### 새로 알게 된점
sort()에서 bool함수를 사용하여 정렬하면서 크기 비교할 수 있다는 점을 처음 알게 되었다.

#### 문제점
1. 문제를 제출할려고 다른 테스트를 돌리던중 프로그래머스에서 진행하는 테스트 11단계에서 오류가 떴다.
2. 아무리 고민해도 몰라서 GPT로 물어봤더니, 0이 3개가 나오는 경우의 수도 존재하기 때문에 이도 처리해야한다고 한다.
3. 그래서 이를 처리하기 위해 추가 코드를 달았다.
```cpp
if(answer[0] == '0')
        return "0";
```
4. 이 조건문을 이용해서 0이 나올 경우 "0"으로 반환하게끔하니 해결됐다.