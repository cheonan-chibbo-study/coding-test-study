#include <string>
#include <set>

using namespace std;

int solution(string dirs) {
    int answer = 0;

    int x = 0;
    int y = 0;
    
    //이미 지나간 길인지 확인
    set<string> visited;

    for(char dir : dirs) {
        int nx = x;
        int ny = y;

        // 1. 다음 좌표 계산
        switch (dir){
            case 'U' : 
                ny++;
                break;
            case 'D' : 
                ny--;
                break;
            case 'L' : 
                nx--;
                break;
            case 'R' : 
                nx++;
                break;
        }

        // 2. 범위 체크
        if(ny > 5 || ny < -5 || nx > 5 || nx < -5){
            continue;
        }

        // 현재 위치 -> 다음 위치
        string path1 = to_string(x) + "," + to_string(y) + "->" + 
            to_string(nx) + "," + to_string(ny);

        // 다음 위치 -> 현재 위치
        string path2 = to_string(nx) + "," + to_string(ny) + "->" + 
            to_string(x) + "," +to_string(y);

        // 4. 처음 지나가는 길이면
        if (visited.find(path1) == visited.end()) {
            answer++;
            visited.insert(path1);
            visited.insert(path2);
        }s

        // 5. 현재 위치 갱신
        x = nx;
        y = ny;
    }

    return answer;
}