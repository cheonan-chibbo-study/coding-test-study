## 👀 제한 시간 안에 어디까지 해냈는가?

`7분 27초`만에 혼자서 문제를 해결했다.

---

## 🧑‍🔬 문제 분석

정수 n, k가 주어질 때 1 ~ n 정수 중 k개로 만들 수 있는 모든 조합을 구해 반환하는 문제이다.

문제에 주어진 제약 조건은 다음과 같다.

**Constraints:**

- `1 <= n <= 20`
- `1 <= k <= n`

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

주어진 입력이 매우 작기 때문에 재귀를 활용한 조합 생성 코드를 작성하면 문제를 해결할 수 있다.

### 결론

- 내가 생각한 풀이를 코드로 구현하면 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 내가 처음 작성한 코드

**`[ Python ]`**

- solution01 - 별도 라이브러리 없이 직접 코드 구현

    ```python
    class Solution:
        def combine(self, n: int, k: int) -> List[List[int]]:
            # 메서드
            def recursive(temp, result, start):
                if len(temp) == k:
                    result.append(temp[::])
                    return
    
                for i in range(start, n + 1):
                    temp.append(i)
                    recursive(temp, result, i + 1)
                    temp.pop()
    
            # 메인 로직
            answer = []
            recursive([], answer, 1)
            return answer
    ```

- solution02 - 파이썬 모듈 사용

    ```python
    from itertools import combinations
    
    class Solution:
        def combine(self, n: int, k: int) -> List[List[int]]:
            # 메인 로직
            candi = [i for i in range(1, n + 1)]
            combi_list = combinations(candi, k)
            return [list(combi) for combi in combi_list]
    ```


**`[ Java ]`**

- solution01 - 별도 라이브러리 없이 직접 코드 구현

    ```java
    import java.util.*;
    
    class Solution {
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> answer = new ArrayList<>();
            recursive(new ArrayList<Integer>(), answer, 1, n, k);
    
            return answer;
        }
    
        private void recursive(List<Integer> temp, List<List<Integer>> result, int start, int n, int k) {
            if (temp.size() == k) {
                result.add(new ArrayList<Integer>(temp));
                return;
            }
    
            for (int i = start; i <= n; i++) {
                temp.add(i);
                recursive(temp, result, i + 1, n, k);
                temp.remove(temp.size() - 1);
            }
        }
    }
    ```

---

## 🥰 배운점 & 느낀점

- 재귀를 활용해 직접 조합 코드를 작성하는 연습을 할 수 있었다.
- 파이썬은 강력한 조합 라이브러리가 있는데 자바는 별도로 제공하는 조합 라이브러리가 없다… 어쩔 수 없이 직접 조합을 구현하는 코드를 미리 연습해야한다.