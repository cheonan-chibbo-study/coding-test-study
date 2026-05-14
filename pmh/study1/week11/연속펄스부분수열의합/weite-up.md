# 문제링크

# 30분내 어디까지 풀었는가
30분내에 풀었지만 시간초과가 남
# 왜 못풀었는가 - 
### 풀이가 안떠올랐는지 / 어떻게 풀지는 알겠는데 구현을 못하겠다.
```java

class Solution {
    public long solution(int[] sequence) {
        int n = sequence.length;
        int[] arrminus = new int[n];
        int[] arrplus = new int[n];

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                arrminus[i] = -sequence[i];
                arrplus[i] = sequence[i];
            } else {
                arrminus[i] = sequence[i];
                arrplus[i] = -sequence[i];
            }
        }

        long totalSum = Long.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            long sum1 = 0;
            long sum2 = 0;

            for (int len = 0; i + len < n; len++) {
                sum1 += arrminus[i + len];
                sum2 += arrplus[i + len];
                totalSum = Math.max(totalSum, Math.max(sum1, sum2));
            }
        }

        return totalSum;
    }
}
```

# 접근방법
1. sequence 배열에 각 펄스를 곱해서 2개의 배열을 구한다
2. 각배열을 len 마다 더해가면서 연속된부분수열 구간합을 구한다 
3. 합 을 비교해서 max 값을 찾고 반환한다 
4. 2중 배열을쓰니 시간 초과가났다 다른방식을써 개선한다
5. 카데인 알고리즘 를 사용해 O(n) 에 구한다
   - [2, -3, 6, -1, 4] 에서 최대 연속 부분합을 구한다고 할때
   - 현재 원소 x 를볼때 
   - 이전까지의 합에 이어 붙이는 게 이득인지 
   - 아니면 여기서 새로 시작하는 게 이득인지 확인한다
   - ```java
     현재까지 최대합 = max(현재 원소, 이전합 + 현재 원소)
     ```
     
6. 카데인 알고리즘을 통해 각 펄스의 구간합 최대값을 구한후 반환한다
# 배운점 
카데인 알고리즘에대해 알게되었다
