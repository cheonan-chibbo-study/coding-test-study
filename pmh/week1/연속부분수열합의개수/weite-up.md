# 문제링크
https://school.programmers.co.kr/learn/courses/30/lessons/131701?utm_source=chatgpt.com
# 30분내 어디까지 풀었는가
30분내 풀기완료
# 왜 못풀었는가 - 
### 풀이가 안떠올랐는지 / 어떻게 풀지는 알겠는데 구현을 못하겠다.


# 접근방법
1. 원형 배열을 만들기위해 원래배열의 2배 크기를 갖는 배열을 만든다
2. 시작점 i를 고정하고, 길이 len을 1~n까지 늘려가며 합을 구함
elements = [3, 1, 2]  (n=3)  
i=0 : 3 / 3+1 / 3+1+2    
i=1 : 1 / 1+2 / 1+2+3  ← 끝에서 wrap around  
i=2 : 2 / 2+3 / 2+3+1  ← wrap around  
3. 값이 중복되는것은 set을 사용해 중복을 없앤다
4. set.size() 를 반환한다.
시간: O(n²) — 시작점 n개 × 길이 n개  
공간: O(n²) (set에 담기는 합의 수)
# 최적화 
```java
package week1.연속부분수열합의개수;
import java.util.*;
class Solution {
    public int solution(int[] elements) {
        int n = elements.length;
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int len = 0; len < n; len++) {
                sum += elements[(i + len) % n];
                set.add(sum);
            }
        }

        return set.size();
    }
}
```
arr 배열(n*2 크기) 제거 → (i + len) % n 으로 원형 인덱싱   
시간복잡도는 동일하게 O(n²)이지만, O(n) 공간을 절약하고 코드가 훨씬 짧아진다.

# 배운점 
배열을 2배로 늘리지않고 원형 문제룰 푸는방법을 알게되었다.