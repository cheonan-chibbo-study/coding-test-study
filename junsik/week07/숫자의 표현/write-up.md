# 문제

자연수 `n`을 연속된 자연수의 합으로 표현하는 방법의 개수를 구하는 문제이다.

# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/12924

# 문제 접근법

처음에는 시작 숫자를 하나씩 정한 뒤 연속된 자연수를 더해가는 브루트포스 방식을 생각하였다.

이후 투 포인터(Two Pointers)를 이용하여 현재 구간의 합을 관리하면서 해결하였다.

현재 구간의 합이 `n`보다 작으면 오른쪽 포인터를 이동하여 구간을 확장하고, `n`보다 크면 왼쪽 포인터를 이동하여 구간을 축소하였다. 구간의 합이 `n`과 같아지면 정답을 증가시키고 포인터를 이동하며 다음 경우를 탐색하였다.

# 소스코드

```cpp
#include <string>
#include <vector>

using namespace std;

int solution(int n) {
    int answer = 0;
    int left = 1;
    int right = 1;
    int sum = 1;

    while(right <= n){
        if(sum == n){
            answer++;
            sum -= left;
            left++;
        }

        else if(sum < n){
            right++;
            sum += right;
        }
        else{
            sum -= left;
            left++;
        }
    }
    return answer;
}
```

# 새로 알게 된 점

* 투 포인터는 `left`와 `right` 두 개의 포인터를 이용하여 구간을 관리하는 알고리즘이다.
* 현재 구간의 합이 작으면 `right`를 증가시키고, 크면 `left`를 증가시키는 방식으로 탐색할 수 있다.
* 투 포인터에서는 각 포인터가 한 방향으로만 이동하므로 전체 시간복잡도가 `O(n)`이 된다.
* 연속된 구간을 탐색하는 문제에서는 브루트포스보다 투 포인터를 우선 떠올리는 것이 효율적이다.

# 느낀점

처음에는 이중 반복문으로 해결하려고 했지만, 투 포인터를 이용하면 불필요한 계산을 줄이면서 효율적으로 해결할 수 있다는 것을 배웠다. 앞으로 연속된 구간의 합을 구하는 문제가 나오면 투 포인터를 먼저 떠올려 볼 수 있을 것 같다.
