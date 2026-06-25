# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/70129

# 문제 접근법

문자열이 `"1"`이 될 때까지 반복문을 수행하였다.

매 반복마다 문자열을 순회하면서 0의 개수를 누적하고, 1의 개수를 계산하였다.

남은 1의 개수를 2진수 문자열로 변환하여 다시 문자열에 저장하고, 변환 횟수를 증가시켰다.

반복이 종료되면 변환 횟수와 제거한 0의 개수를 반환하였다.

# 소스코드

```cpp
#include <string>
#include <vector>

using namespace std;

string toBinary(int num)
{
    string binary = "";

    while(num > 0)
    {
        binary = char(num % 2 + '0') + binary;
        num /= 2;
    }

    return binary;
}

vector<int> solution(string s) {
    vector<int> answer;
    int count = 0;
    int zcount = 0;
    
    while (s != "1"){
        int current = 0;
        
        for(char c : s){
            if(c == '0')
                zcount++;
            else
                current++;
        }
        s = toBinary(current);
        count++;
    }
    
    answer.push_back(count);
    answer.push_back(zcount);
    
    return answer;
}
```

# 새로 알게 된 점

* 문자열을 순회하면서 필요한 문자만 선택하여 새로운 문자열을 만들 수 있다.
* 10진수를 2진수 문자열로 변환할 때는 `num % 2`와 `num /= 2`를 반복하여 구현할 수 있다.
* 나머지를 앞쪽에 붙여야 올바른 순서의 2진수 문자열을 만들 수 있다.
* 반복문을 이용해 문자열이 특정 조건을 만족할 때까지 변환하는 과정을 구현할 수 있다.

# 느낀점

문제 자체는 복잡하지 않았지만, 10진수를 직접 2진수 문자열로 변환하는 구현이 가장 중요한 부분이었다. 평소에는 내장 함수를 사용하는 경우가 많았는데, 직접 변환 과정을 구현해 보면서 2진수 변환 원리를 이해할 수 있었다.
