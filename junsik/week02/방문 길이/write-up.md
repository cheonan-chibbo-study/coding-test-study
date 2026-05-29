# 문제 링크
https://school.programmers.co.kr/learn/courses/30/lessons/49994

# 문제 접근법
1. 현재 위치 `(x, y)`와 이동 후 위치 `(nx, ny)`를 관리한다.
2. 이동 후 좌표가 범위를 벗어나면 해당 이동을 무시한다.
3. 현재 위치와 다음 위치를 이용하여 길 정보를 문자열로 생성한다.
4. `set`을 이용하여 이미 방문한 길인지 확인한다.
5. 처음 방문한 길이면 answer를 증가시키고, 양방향 경로를 모두 저장한다.
6. 현재 위치를 다음 위치로 갱신한다.

# 소스코드

```cpp
#include <string>
#include <set>

using namespace std;

int solution(string dirs) {
    int answer = 0;

    int x = 0;
    int y = 0;

    set<string> visited;

    for(char dir : dirs) {
        int nx = x;
        int ny = y;

        switch (dir){
            case 'U':
                ny++;
                break;
            case 'D':
                ny--;
                break;
            case 'L':
                nx--;
                break;
            case 'R':
                nx++;
                break;
        }

        if(ny > 5 || ny < -5 || nx > 5 || nx < -5){
            continue;
        }

        string path1 =
            to_string(x) + "," + to_string(y) + "->" +
            to_string(nx) + "," + to_string(ny);

        string path2 =
            to_string(nx) + "," + to_string(ny) + "->" +
            to_string(x) + "," + to_string(y);

        if (visited.find(path1) == visited.end()) {
            answer++;
            visited.insert(path1);
            visited.insert(path2);
        }

        x = nx;
        y = ny;
    }

    return answer;
}
```

# 새로 알게 된 점
* cpp에는 `set`이라는 자료형이 존재하고 이는 중복된 값을 저장하지 않은 파이썬에서 쓰이는 `set`과 동일하다라는 점을 알게되었다.

# 느낀 점
* 문자열을 이용하여 길 정보를 표현하는 방법을 배울 수 있었다.
* `set`을 활용한 중복 처리 방법을 다시 복습할 수 있었다.