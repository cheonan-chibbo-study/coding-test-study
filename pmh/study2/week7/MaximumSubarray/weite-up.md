# 문제링크
https://leetcode.com/problems/maximum-subarray/
# 30분내 어디까지 풀었는가
30분내 풀었지만 힌트를 보고풀었다
# 왜 못풀었는가 - 
### 풀이가 안떠올랐는지 / 어떻게 풀지는 알겠는데 구현을 못하겠다.


# 접근방법
1. 재귀방식을 통해 i-1 까지의 부분배열의 최대합을 구한다
2. 현재 배열에서 i-1 까지의 부분배열의 최대합 을 더한 경우, 현재 배열부터 새로 시작할때의 값중 더 큰값을 현재값으로한다
3. 매 계산마다 최대합과, 현재합 중 더큰값을 갱신한다
4. 최대값을 반환한다.

# 시간/공간 복잡도

시간 복잡도: **O(n)**
- 배열을 한 번만 순회합니다.

공간 복잡도: **O(1)**
- `currentSum`과 `maxSum` 두 개의 변수만 사용합니다.

# 최적화
```java
class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum=Integer.MIN_VALUE;
        int currentSum=0;
        for(int i=0;i<nums.length;i++)
        {
            currentSum+=nums[i];
            if(currentSum>maxSum)
            {
                maxSum=currentSum;
            }
            if(currentSum<0)
            {
                currentSum=0;
            }
        }
        return maxSum;
    }
}
```
## Kadane's Algorithm

### 핵심 아이디어

`currentSum`을 **현재 위치에서 끝나는 최대 부분합**으로 유지하면서 배열을 한 번만 순회하는 알고리즘.

각 원소를 볼 때마다 두 가지 선택을 한다.

1. 이전 부분합에 현재 원소를 이어 붙인다.
2. 현재 원소부터 새로운 부분 배열을 시작한다.

이를 점화식으로 표현하면 다음과 같다.

```text
currentSum = max(currentSum + nums[i], nums[i])
maxSum = max(maxSum, currentSum)
```

- `currentSum` : **i에서 끝나는 최대 부분합**
- `maxSum` : 지금까지 등장한 최대 부분합

---

###  왜 음수가 되면 버릴까?

이전까지의 합이 음수라면 이후의 원소를 더해도 손해이다.

예를 들어,

```text
currentSum = -5
다음 숫자 = 3

이어서 계산
-5 + 3 = -2

새로 시작
3
```

새로 시작하는 것이 항상 더 크므로, 음수인 부분합은 버리고 현재 원소부터 다시 시작한다.

그래서 아래 코드와 같은 동작을 한다.

```java
currentSum += nums[i];

if (currentSum < 0) {
    currentSum = 0;
}
```

이는 아래 점화식을 조금 더 간단하게 구현한 것과 같다.

```java
currentSum = Math.max(currentSum + nums[i], nums[i]);
```

---

### 동작 과정

예제

```text
nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
```

| i | nums[i] | currentSum | maxSum |
|---:|--------:|-----------:|-------:|
|0|-2|-2|-2|
| |(음수이므로 0으로 초기화)|||
|1|1|1|1|
|2|-3|-2|1|
| |(음수이므로 0으로 초기화)|||
|3|4|4|4|
|4|-1|3|4|
|5|2|5|5|
|6|1|6|6|
|7|-5|1|6|
|8|4|5|6|

최종 답은 **6**

---

### DFS와의 관계

Kadane's Algorithm은 다음 DP 점화식을 반복문으로 최적화한 것이다.

```text
dp[i] = max(dp[i-1] + nums[i], nums[i])
```

- `dp[i]` : i에서 끝나는 최대 부분합

`dp[i]`를 계산할 때 필요한 값은 `dp[i-1]` 하나뿐이므로 배열을 만들 필요가 없다.

```java
currentSum = Math.max(currentSum + nums[i], nums[i]);
```

처럼 이전 값 하나만 유지하면 되므로 공간 복잡도를 **O(1)** 로 줄일 수 있다.

---