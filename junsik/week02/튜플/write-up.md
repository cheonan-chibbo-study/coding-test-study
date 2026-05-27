# 문제 링크
https://school.programmers.co.kr/learn/courses/30/lessons/64065

---

# 문제 접근법
1. 문자열 안의 각 집합을 분리하여 vector에 저장한다.
---
2. 튜플은 원소가 하나씩 추가되는 구조이므로, 집합 길이가 작은 순서대로 정렬한다.
---
3. 길이가 작은 집합부터 탐색하면서, 처음 등장한 숫자를 answer에 추가한다.
---
4. 중복 체크를 위해 `set`을 사용한다.
---

# 핵심 아이디어 (GPT)

```text
길이가 작은 집합부터 탐색하면서
새롭게 등장한 숫자를 answer에 저장한다.
```

---

# 최종 코드

```cpp
#include <string>
#include <vector>
#include <algorithm>
#include <set>

using namespace std;

bool compare(string a, string b) {
    return a.size() < b.size();
}

vector<int> solution(string s) {

    vector<int> answer;
    vector<string> arr;

    string temp = "";

    for (int i = 0; i < s.size(); i++) {

        if (s[i] == '{') {
            temp = "";
        }

        else if (s[i] == '}') {

            if (!temp.empty()) {
                arr.push_back(temp);
            }
        }

        else {
            temp += s[i];
        }
    }

    sort(arr.begin(), arr.end(), compare);

    set<int> st;

    for (string str : arr) {

        string num = "";

        for (int i = 0; i <= str.size(); i++) {

            if (i == str.size() || str[i] == ',') {

                int n = stoi(num);

                if (st.find(n) == st.end()) {
                    st.insert(n);
                    answer.push_back(n);
                }

                num = "";
            }
            else {
                num += str[i];
            }
        }
    }

    return answer;
}
```

---

# 느낀 점

핵심은 집합 크기 순서와 새롭게 등장하는 숫자를 이용해 튜플을 복원하는 아이디어였다.
또한 `set`을 이용한 중복 제거와 문자열 처리 방법을 연습할 수 있었다.
