# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/42888

# 문제 접근법

`stringstream`을 이용하여 각 기록을 명령어, uid, 닉네임으로 분리하였다.

`map`을 사용하여 uid와 최신 닉네임을 저장하였다.

`Enter`와 `Change`가 발생하면 최신 닉네임으로 갱신하고, `Enter`와 `Leave`만 별도의 로그에 저장하였다.

모든 기록을 처리한 뒤, 저장된 로그를 순회하면서 최종 닉네임을 적용하여 정답 문자열을 생성하였다.

# 소스코드

```cpp
#include <string>
#include <vector>
#include <sstream>
#include <map>

using namespace std;

vector<string> solution(vector<string> record) {
    vector<string> answer;
    
    map<string,string> nickname;
    vector<pair<string,string>> logs;
    
    for(string r : record)
    {
        stringstream ss(r);

        string cmd;
        string uid;
        string name;

        ss >> cmd >> uid;

        if(cmd != "Leave")
            ss >> name;
        
        if(cmd == "Enter" || cmd == "Change")
            nickname[uid] = name;
        
        if(cmd == "Enter" || cmd == "Leave")
            logs.push_back({cmd, uid});
    }
    
    for(auto log : logs)
    {
        string cmd = log.first;
        string uid = log.second;

        if(cmd == "Enter")
            answer.push_back(nickname[uid] + "님이 들어왔습니다.");

        else
            answer.push_back(nickname[uid] + "님이 나갔습니다.");
    }
    
    return answer;
}
```

# 새로 알게 된 점

* `stringstream`을 사용하면 공백 기준으로 문자열을 쉽게 분리할 수 있다.
* `map`을 이용해 uid와 닉네임을 관리할 수 있다.
* 로그를 바로 출력하지 않고 필요한 정보만 저장한 뒤 마지막에 처리하는 방식도 사용할 수 있다.

# 느낀점

처음에는 채팅 기록을 바로 저장하려고 했지만, 닉네임 변경 시 과거 기록도 함께 변경되어야 한다는 점이 핵심이었다. uid와 로그를 분리해서 관리하는 방법을 익힐 수 있었던 문제였다.