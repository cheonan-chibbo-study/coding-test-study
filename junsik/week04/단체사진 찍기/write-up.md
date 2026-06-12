# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/1835

# 문제 접근법

친구는 총 8명이므로 가능한 모든 배치의 경우를 만들어 확인하였다.

8명의 순열을 DFS(백트래킹)로 생성하고, 배치가 완성될 때마다 조건을 검사하였다.

조건 문자열에서 두 사람의 위치를 찾고,

```text
사이 사람 수 = |위치 차이| - 1
```

을 계산하여

* '=' : 정확히 일치
* '<' : 조건보다 작아야 함
* '>' : 조건보다 커야 함

을 확인하였다.

모든 조건을 만족하면 정답을 1 증가시켰다.

# 소스코드

```cpp
#include <string>
#include <vector>
#include <cmath>

using namespace std;

int answer;
bool used[8];
string line;

char people[8] = {
    'A','C','F','J',
    'M','N','R','T'
};

bool check(vector<string>& data)
{
    int pos[256];

    for(int i = 0; i < 8; i++)
    {
        pos[line[i]] = i;
    }

    for(string s : data)
    {
        char a = s[0];
        char b = s[2];
        char op = s[3];
        int dist = s[4] - '0';

        int gap = abs(pos[a] - pos[b]) - 1;

        if(op == '=' && gap != dist)
            return false;

        if(op == '<' && gap >= dist)
            return false;

        if(op == '>' && gap <= dist)
            return false;
    }

    return true;
}

void DFS(vector<string>& data)
{
    if(line.size() == 8)
    {
        if(check(data))
            answer++;

        return;
    }

    for(int i = 0; i < 8; i++)
    {
        if(used[i])
            continue;

        used[i] = true;
        line.push_back(people[i]);

        DFS(data);

        line.pop_back();
        used[i] = false;
    }
}

int solution(int n, vector<string> data)
{
    answer = 0;
    line = "";

    for(int i = 0; i < 8; i++)
        used[i] = false;

    DFS(data);

    return answer;
}
```

# 새로 알게 된 점

* DFS는 그래프 탐색뿐 아니라 순열 생성에도 사용할 수 있다.
* 경우의 수가 크지 않은 경우 완전탐색으로 해결할 수 있다.
* 백트래킹을 사용하면 모든 배치를 효율적으로 생성할 수 있다.

# 느낀점

처음에는 복잡해 보였지만 실제로는 모든 배치를 생성한 뒤 조건을 검사하는 완전탐색 문제였다.

DFS를 이용한 순열 생성 방법을 연습할 수 있었고, 백트래킹 개념을 이해하는 데 도움이 되었다.
