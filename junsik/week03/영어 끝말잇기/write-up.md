# 문제 링크

https://school.programmers.co.kr/learn/courses/30/lessons/12981

# 문제 접근법

1. 이전 단어의 마지막 문자와 현재 단어의 첫 문자를 비교하여 끝말잇기 규칙을 검사한다.
2. `set`을 사용하여 이미 사용한 단어인지 확인한다.
3. 규칙 위반 또는 중복 단어가 발견되면 실패한 단어 번호를 계산한다.
4. 실패한 단어 번호를 이용하여 사람 번호와 차례를 계산한다.
5. 끝까지 실패 조건이 발생하지 않으면 `[0, 0]`을 반환한다.

# 소스코드

```cpp
#include <string>
#include <vector>
#include <set>

using namespace std;

vector<int> solution(int n, vector<string> words) {
    vector<int> answer;
    set<string> used;

    used.insert(words[0]);

    for(int i = 0; i < words.size() - 1; i++) {

        if(words[i][words[i].size() - 1] != words[i + 1][0] ||
           used.find(words[i + 1]) != used.end()) {

            int fail = i + 2;

            int person = (fail - 1) % n + 1;
            int turn = (fail - 1) / n + 1;

            answer.push_back(person);
            answer.push_back(turn);

            return answer;
        }

        used.insert(words[i + 1]);
    }

    return {0, 0};
}
```

# 새로 알게 된 점

* `set`을 사용하면 중복된 값을 저장하지 않으며, 특정 값의 존재 여부를 쉽게 확인할 수 있다.
* 사람 번호와 차례를 계산할 때는 나머지 연산과 몫 연산을 활용할 수 있다.
* 단어의 첫 글자와 마지막 글자는 인덱스를 이용하여 쉽게 접근할 수 있다.

# 느낀 점

* 처음에는 단순히 앞 단어와 뒤 단어를 비교하는 문제라고 생각했지만, 중복 단어 검사도 함께 고려해야 했다.
* `set`을 이용한 중복 체크 방법을 익힐 수 있었다.
* 사람 번호와 차례를 계산하는 식을 직접 유도하면서 나머지 연산과 몫 연산의 활용 방법을 이해할 수 있었다.
* 처음으로 30분안에 풀었다 (매우 뿌듯)
