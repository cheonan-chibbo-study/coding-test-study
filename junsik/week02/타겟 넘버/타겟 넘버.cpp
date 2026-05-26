#include <string>
#include <vector>

using namespace std;

int answer = 0;

// DFS 함수
void dfs(vector<int>& numbers, int target, int index, int sum) {
    // 1. 모든 숫자를 다 사용했는지 확인
    if(index == numbers.size()) {
        // 2. 현재 합이 target과 같은지 확인
        if(sum == target){
            // 같으면 answer 증가
            answer++;
        }
        return;
    }
    // 3. 현재 숫자를 더하는 경우
    dfs(numbers, target,index+1,sum + numbers[index]);

    // 4. 현재 숫자를 빼는 경우
    dfs(numbers, target,index+1, sum - numbers[index]);
}

int solution(vector<int> numbers, int target) {
    dfs(numbers, target,0,0);

    return answer;
}