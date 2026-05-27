# 문제링크
https://school.programmers.co.kr/learn/courses/30/lessons/12924?utm_source=chatgpt.com
# 30분내 어디까지 풀었는가
15분에 풀기성공
# 왜 못풀었는가 - 
### 풀이가 안떠올랐는지 / 어떻게 풀지는 알겠는데 구현을 못하겠다.


# 접근방법
1. 연속된 자연수의 합 이 n 이도록해야한다
2. 이중 반복문을 사용해 1~n 까지 더하기 2~n, .... n~n 까지 더하기를 하도록한다
3. 자연수의 합이 n 보다 크면 break 을 해 다음 반복문을 돌게한다
4. 자연수의 합이 n 이라면 count 를 +1 하고 break 해 다은반복문을 돌게한다 
# 배운점 
## 투 포인터 로 최적하 (O(n))
```java
class Solution {
    public int solution(int n) {
        int count = 0;

        int start = 1;
        int end = 1;
        int sum = 1;

        while (start <= n) {

            if (sum == n) {
                count++;
                sum -= start;
                start++;
            }
            else if (sum < n) {
                end++;
                sum += end;
            }
            else {
                sum -= start;
                start++;
            }
        }

        return count;
    }
}
```
- 합이 작으면 end 증가
- 합이 크면 start 증가
- 항상 연속된 구간 유지
## 수학 풀이 (O(√n))
```java
class Solution {
    public int solution(int n) {
        int count = 0;

        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {

                if (i % 2 == 1) count++;

                int pair = n / i;

                if (pair != i && pair % 2 == 1) {
                    count++;
                }
            }
        }

        return count;
    }
}
```