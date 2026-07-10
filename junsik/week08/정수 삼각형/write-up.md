# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/43105

# 문제 접근법

각 위치까지 도달했을 때의 최대 합을 저장하는 2차원 DP를 사용하였다.

`dp[i][j]`를 **i행 j열까지 도달했을 때의 최대 합**으로 정의하였다.

* 맨 왼쪽은 위에서만 올 수 있으므로 `dp[i-1][j]`를 이용하였다.
* 맨 오른쪽은 왼쪽 위에서만 올 수 있으므로 `dp[i-1][j-1]`를 이용하였다.
* 가운데는 위와 왼쪽 위 중 더 큰 값을 선택하여 현재 값을 더하였다.

모든 행을 계산한 후 마지막 행에서 가장 큰 값을 찾아 반환하였다.

# 소스코드

```cpp
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<vector<int>> triangle) {
    vector<vector<int>> dp = triangle;
    dp[0][0] = triangle[0][0];

    for(int i = 1; i<triangle.size(); i++)
    {
        for(int j = 0; j<=i; j++)
        {
            // 맨 왼쪽
            if(j == 0)
            {
                dp[i][j] = dp[i-1][j] + triangle[i][j];
            }
            // 맨 오른쪽
            else if(j == i)
            {
                dp[i][j] = dp[i-1][j-1] + triangle[i][j];
            }
            // 가운데
            else
            {
                dp[i][j] = max(dp[i-1][j],dp[i-1][j-1]) + triangle[i][j];
            }
        }
    }
    return *max_element(dp.back().begin(), dp.back().end());
}
```

# 새로 알게 된 점

* 2차원 DP에서는 `dp[i][j]`가 어떤 의미를 가지는지 먼저 정의하는 것이 가장 중요하다.
* 현재 위치에 도달할 수 있는 이전 위치를 찾아 점화식을 세우는 방식으로 문제를 해결할 수 있다.
* 경계 조건(맨 왼쪽, 맨 오른쪽)은 가운데와 점화식이 다르므로 별도로 처리해야 한다.
* `vector<vector<int>> dp = triangle;`를 사용하면 원본과 같은 크기의 2차원 DP 배열을 쉽게 생성할 수 있다.
* `max_element()`를 이용하면 마지막 행의 최댓값을 간단하게 구할 수 있다.

# 느낀점

처음에는 DFS로 모든 경로를 탐색해야 한다고 생각했지만, 각 위치까지의 최대 합만 저장하면 된다는 점을 이해하면서 DP로 해결할 수 있다는 것을 알게 되었다. 특히 2차원 DP에서는 점화식을 세우기 전에 "현재 위치까지의 최대 합"이라는 DP의 의미를 먼저 정의하는 것이 중요하다는 것을 배웠다. 앞으로도 DP 문제에서는 먼저 상태를 정의하고, 이전에 어디에서 올 수 있는지를 생각한 뒤 점화식을 세우는 방식으로 접근해야겠다고 느꼈다.
