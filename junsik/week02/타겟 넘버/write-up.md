# 문제 링크
https://school.programmers.co.kr/learn/courses/30/lessons/43165

## 문제 접근법
1. 주어진 배열 요소들을 더하거나 빼서 target의 숫자가 될수있는 경우의 수를 구한다.
2. DFS를 사용하면 쉽게 풀수있다.

## 소스코드
```cpp
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
```
---
## 느낌 점
DFS관련해 공부하면서 재귀함수에 관한 개념을 다시 상기 시켜주는 고마운 문제였다.