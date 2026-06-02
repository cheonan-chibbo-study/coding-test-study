# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/92334

# 문제 접근법

1. set을 이용하여 중복 신고를 제거한다.
2. 신고 내역을 순회하며 신고당한 유저의 횟수를 map에 저장한다.
3. 신고 횟수가 k 이상인 유저를 정지 대상(set)에 저장한다.
4. 중복 제거된 신고 내역을 다시 순회하며 신고 대상이 정지된 유저인지 확인한다.
5. 정지된 유저를 신고한 사람의 메일 수를 map에 저장한다.
6. id_list 순서대로 메일 수를 answer에 저장하여 반환한다.

# 소스코드

```cpp
#include <string>
#include <vector>
#include <set>
#include <map>

using namespace std;

vector<int> solution(vector<string> id_list, vector<string> report, int k) {
    vector<int> answer;
    set<string> reported;
    set<string> banned;
    map<string, int> count;
    map<string, int> mail;

    for(string s : report){
        reported.insert(s);
    }

    for(string re : reported){
        int pos = re.find(' ');
        string from = re.substr(0, pos);
        string to = re.substr(pos + 1);

        count[to]++;
    }

    for(string id : id_list){
        if(count[id] >= k){
            banned.insert(id);
        }
    }

    for(string re : reported){
        int pos = re.find(' ');
        string from = re.substr(0, pos);
        string to = re.substr(pos + 1);

        if(banned.find(to) != banned.end()){
            mail[from]++;
        }
    }

    for(string id : id_list){
        answer.push_back(mail[id]);
    }

    return answer;
}
```

# 새로 알게 된 점

* set을 이용하면 중복된 신고 내역을 쉽게 제거할 수 있다.
* map을 이용하면 문자열을 key로 하여 횟수를 저장할 수 있다.
* find()와 substr()를 이용하여 문자열을 원하는 위치에서 분리할 수 있다.
* set의 원소 존재 여부는 `find() != end()`로 확인할 수 있다.

# 느낀점

처음에는 신고당한 횟수만 구하면 되는 문제인 줄 알았지만, 실제로는 정지된 유저를 신고한 사람의 메일 수를 계산해야 했다.

set과 map을 함께 사용하는 문제였으며, 중복 제거 → 신고 횟수 계산 → 정지 대상 확인 → 메일 수 계산 순서로 문제를 나누어 생각하니 해결할 수 있었다.

자료구조를 어떻게 선택하느냐가 중요한 문제라는 것을 느꼈다.