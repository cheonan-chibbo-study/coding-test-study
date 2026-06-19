## 👩‍🚀 문제 분석

주어진 문자열 s를 분할했을 때 그 부분 문자열들이 모두 palindrome인 경우를 모두 반환하는 문제이다.

참고로 palindrome은 앞으로 읽거나 뒤로 읽어도 모두 같은 문자열을 의미한다.

문제에 주어진 제한 조건은 다음과 같다.

- `1 <= s.length <= 16`
- `s` contains only lowercase English letters.

P & J 트레이닝

- Python으로 풀이하여 `7분 02초`만에 문제를 해결했다.
- Java로 풀이하여 `10분 37초`만에 문제를 해결했다.

---

## 🤔 풀이 고민

### 완전 탐색

- 처음 생각했던 시간 복잡도 (잘못된 판단이었따…)

  완전 탐색에 경우 첫 번째 문자열을 시작으로 재귀를 사용해 각 문자열 길이를 점점 늘려가면서 부분 문자열을 만들어 각 경우의 palindrome 여부를 판단하는 방법이다.

  예를 들어 `abcde` 를 대상으로 수행한다고 가정하면 주어진 문자열의 길이를 n이라고 할 때

    - a/b/c/d/e, a/b/c/de, a/b/cde, a/bcde → (n - 1)번 연산
    - ab/c/d/e, ab/c/de, ab/cde → (n - 2)번 연산
    - abc/d/e, abc/de → (n - 3)번 연산
    - abcd/e → (n - 4)번 연산
    - abcde → 1번 연산

  총 (n - 1) + (n - 2) + (n - 3) + (n - 4) + 1 == 4n + 9번의 연산이 예상되므로 BigO 표기로 최대 `n` 의 시간 복잡도가 소요됨을 예상할 수 있다.

  문제에 주어지는 문자열의 길이는 최대 16이므로 완전 탐색을 활용해 충분히 문제를 해결할 수 있다.


주어진 문자열 내부에 속한 각 문자들 사이에 파티션을 놓을지 말지에 대한 모든 경우의 수를 찾아 palindrome 여부를 검사하는 방식을 먼저 떠올려 볼 수 있다.

문자열 길이를 `n`이라고 할 때, 문자열 내부의 각 문자 사이에 파티션을 놓을지 말지에 대한 모든 경우의 수는 `2^(n - 1)` 이다.

그리고 각 경우에 대해 palindrome 여부를 검사하는데 최대 n번의 연산이 소요되므로 이 풀이의 최종 Big(O) 시간 복잡도는 `n * 2^(n - 1)`로 예상할 수 있다.

이 문제에 주어지는 최대 n 값은 16이므로 최대 `16 * 2^15 (524,288)`번 연산이 발생할 수 있다. 십만 단위의 연산 횟수 이므로 충분히 이 풀이로 문제를 해결할 수 있다.

### 결론

- 완전 탐색을 활용하면 최대 `n * 2^(n - 1)` → `16 * 2^15 (524,288)` 의 시간 복잡도가 소요되므로 이 방식으로 문제를 해결할 수 있다.

---

## 🧑‍💻 코드 구현

### Python 풀이

- solution01

    ```python
    class Solution:
        def partition(self, s: str) -> List[List[str]]:
            # 메서드
            def back_tracking(temp, start):
                if start == len(s):
                    answer.append(temp[::])
                    return
    
                sub_str = ""
                for i in range(start, len(s)):
                    sub_str += s[i]
    
                    if not is_palindrome(sub_str):
                        continue
                    
                    temp.append(sub_str)
                    back_tracking(temp, i + 1)
    
                    temp.pop()
            
            def is_palindrome(target):
                return target == target[::-1]
    
            # 메인 로직
            answer = []
            back_tracking([], 0)
    
            return answer
    ```


### Java 풀이

- solution01

    ```java
    import java.util.*;
    
    class Solution {
    
        String s;
    
        public List<List<String>> partition(String s) {
            this.s = s;
    
            // 메인 로직
            List<List<String>> answer = new ArrayList<>();
            backTracking(answer, new ArrayList<>(), 0);
    
            return answer;
        }
    
        private void backTracking(List<List<String>> result, List<String> temp, int start) {
            if (start == s.length()) {
                result.add(new ArrayList<>(temp));
                return;
            }
    
            String subStr = "";
            for (int i = start; i < s.length(); i++) {
                subStr += String.valueOf(s.charAt(i));
                if (!isPalindrome(subStr)) {
                    continue;
                }
    
                temp.add(subStr);
                backTracking(result, temp, i + 1);
    
                temp.remove(temp.size() - 1);
            }
        }
    
        private boolean isPalindrome(String target) {
            return target.equals(new StringBuilder(target).reverse().toString());
        }
    }
    ```


---

## ✏️ 배운점 & 느낀점

- 시간 복잡도 계산을 더 많이 연습할 필요가 있다.
- 확실히 문자열 처리 연산은 파이썬이 압도적으로 편하다. 다만 암기가 필요한 부분들이 있으니 Tip 페이지에 잘 정리해놓고 복기할 필요가 있다.
    - 문자열 슬라이싱
    - 문자열 뒤집기
    - 문자열 내부 문자 순회