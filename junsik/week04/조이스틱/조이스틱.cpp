#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(string name) {
    int answer = 0;
    int move = name.size() - 1;

    for (int i = 0; i < name.size(); i++) {

        // 위/아래 조작 횟수
        answer += min(name[i] - 'A', 'Z' - name[i] + 1);

        // 연속된 A 찾기
        int next = i + 1;
        while (next < name.size() && name[next] == 'A') {
            next++;
        }

        // 커서 이동 최소값 갱신
        move = min(move,
                   i * 2 + (int)name.size() - next);

        move = min(move,
                   ((int)name.size() - next) * 2 + i);
    }

    answer += move;

    return answer;
}