## 👀 제한 시간 안에 어디까지 해냈는가?

- Java로 처음 도전했고 제한 시간 `30분` 안에 문제를 해결하지 못했다.
- Java로 풀이 완료 후 Python으로 2차 풀이를 진행했고 `13분 52초`만에 혼자서 문제를 해결했다.

---

## 🧑‍🔬 문제 분석

데이터 리스트 relation이 주어질 때 relation 테이블에서 만들 수 있는 후보키 전체 개수를 반환하는 문제이다.

- 후보키 조건은 문제를 참고하자.

문제에 주어지는 제약 조건은 다음과 같다.

### 제한사항

- relation은 2차원 문자열 배열이다.
- relation의 컬럼(column)의 길이는 `1` 이상 `8` 이하이며, 각각의 컬럼은 릴레이션의 속성을 나타낸다.
- relation의 로우(row)의 길이는 `1` 이상 `20` 이하이며, 각각의 로우는 릴레이션의 튜플을 나타낸다.
- relation의 모든 문자열의 길이는 `1` 이상 `8` 이하이며, 알파벳 소문자와 숫자로만 이루어져 있다.
- relation의 모든 튜플은 유일하게 식별 가능하다.(즉, 중복되는 튜플은 없다.)

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

- 처음 생각한 시간 복잡도 (어느정도 맞지만 보완이 제법 필요하다고 한다…)

  가장 직관적인 방법은 모든 인적사항 정보를 탐색하면서 후보키 조건에 부합하는 경우를 모두 찾아 카운팅하는 방법이다. 대략적인 플로우와 시간 복잡도는 다음과 같이 판단해볼 수 있다. (인적사항의 총 컬럼 개수 = c, 총 로우 개수 = r)

    - 단일 컬럼을 기준으로 유일성을 보장하는 모든 컬럼을 먼저 찾는다.
        - `c * r` (최대 `8 * 20 (160)`번의 최대 연산 회수가 예상됨)
    - 복합 컬럼의 개수별 조합을 모두 구한다.
        - 복합 컬럼 개수는 2 ~ c이므로 최대 c - 1 번의 복합 컬럼 조합 계산이 필요
        - 재귀를 활용해 조합을 구할 경우 보통 계산되는 조합의 길이만큼의 시간 복잡도가 소요됨
        - c는 최대 8이므로 복합 컬럼의 개수별 조합을 모두 고르는 최악의 시간 복잡도는 (c - 1) * (최대 조합 요소 개수) → `7 * (8C4(70)) (490)`번의 최대 연산 횟수가 예상됨.
    - 위에서 구한 복합 컬럼 조합을 순회하면서 최소성 & 유일성 조건을 만족하는 조합을 찾아 최종 답을 반환한다.
        - 조합 하나의 최대 요소 개수는 70이므로 (c - 1) * (최대 조합 요소 개수) * r → 7 * 70 * 20 (9,800)의 최대 연산 횟수 예상

  따라서 최종 시간 복잡도는 `160 + 490 + 9800 = 10,450`번의 연산 횟수가 예상됨. 이는 만단위의 연산 횟수 이므로 충분히 이 방식으로 문제를 해결할 수 있다고 예상됨.

    - `(c * r) + ((c - 1) * (cCk)) + ((c - 1) * (cCk) * r)`

이 문제를 완전 탐색으로 풀이할 경우 최악의 시간 복잡도는 다음과 같다.

2^8 * 20 * 8 * 8 = `327,680번`

(컬럼 부분집합 개수 * row 최대 개수 * 최대 문자열 길이 (문자열 비교) * column 최대 개수)

- 복합 컬럼이 될 수 있는 부분 집합을 구하다. → 최대 `2^8`
    - 각 컬럼은 2개의 선택지를 가진다. (집합에 속할지, 속하지 않을지)
    - 컬럼의 최대 개수는 8개이므로 `2^8`
- 컬럼 하나의 문자열 최대 길이는 8이므로 최대 컬럼 개수인 8만큼 각 컬럼 문자열을 붙이는 연산을 수행하면 `8 * 8 = 64`번의 최대 연산 횟수가 예상된다.

따라서 완전 탐색을 활용하면 최대 `327,680번` 연산이 필요하기에 문제를 해결할 수 있다.

### 결론

- 내가 생각한 완전 탐색 풀이를 활용하면 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 내가 처음 작성한 코드

처음 Java로 도전했을 때 아래와 같이 코드를 작성해 테스트 케이스는 통과했지만 최종 채점에서 몇몇 오답 케이스가 발생했었다.

- Java 코드

    ```java
    import java.util.*;
    
    class Solution {
        
        String[][] relation;
        List<String> candiKey = new ArrayList<>();
        
        public int solution(String[][] relation) {
            this.relation = relation;
            
            // 메인 로직
            int answer = 0;
            for (int count = 1; count <= relation[0].length; count ++) {
                List<String> candiList = getCandiList(count);
                for (int i = 0; i < candiList.size(); i++) {
                    if (isCandidateKey(candiList.get(i))) {
                        candiKey.add(candiList.get(i));
                        answer += 1;
                    }
                }
            }
            
            return answer;
        }
        
        private List<String> getCandiList(int count) {
            List<String> result = new ArrayList<>();
            getCombi(result, new ArrayList<String>(), count, 0);
            
            return result;
        }
        
        private void getCombi(List<String> result, List<String> temp, int count, int start) {
            if (temp.size() == count) {
                String s = "";
                for (int j = 0; j < temp.size(); j++) {
                    s += temp.get(j);
                }
                
                result.add(s);
                return;
            }
            
            for (int i = start; i < relation[0].length; i++) {
                temp.add(String.valueOf(i));
                getCombi(result, temp, count, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
        
        private boolean isCandidateKey(String k) {
            // 최소성 검사
            if (k.length() >= 2) {
                for (String s : candiKey) {
                    if (k.contains(s)) {
                        return false;
                    }
                }
            }
            
            // 유일성 검사
            Set<String> keySet = new HashSet<>();
            for (String[] r : relation) {
                String s = "";
                for (int i = 0; i < k.length(); i++) {
                    s += r[Integer.valueOf(String.valueOf(k.charAt(i)))];
                }
                keySet.add(s);
            }
            
            if (keySet.size() != relation.length) {
                return false;
            }
            
            return true;
        }
    }
    ```


오답이 발생한 이유는 다음과 같다.

- 후보키 최소성 검사에서 `String k`, `String s`에 대해 `k.contains(s)` 로직을 사용해 검사를 진행했는데 이 로직은 몇몇 케이스를 검사하지 못한다.
- 만약 k가 “abc”, s가 “ac”일 경우 s가 k의 부분 집합임에도 불구하고 문자열 `contains`연산에 의해 부분 집합으로 식별되지 못한다.

이 문제를 해결하기 위해 후보키 형태를 `String`에서 `Set<Integer>`으로 변경 후 유일성 검사 로직을 아래와 같이 수정했다.

```java
if (k.containsAll(item)) {
    return false;
}
```

- `k`, `item`은 모두 자료형이 `Set<Integer>`이다.
- 위 로직은 item이 k의 부분 집합인지 여부를 검사한다.

위 문제만 해결하니 정답 처리가 되었다.

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01 - 조합 로직 직접 구현

    ```python
    def solution(relation):
        candi_key = []
        answer = 0
        
        # 메서드
        def get_candi(count):
            result = []
            get_combi(result, [], count, 0)
            
            return result
        
        def get_combi(result, temp, count, start):
            if len(temp) == count:
                result.append(set(temp))
                return
            
            for k in range(start, len(relation[0])):
                temp.append(k)
                get_combi(result, temp, count, k + 1)
                temp.pop()
        
        def is_candi_key(k):
            # 최소성 검사
            for rk in candi_key:
                if rk.issubset(k):
                    return False
            
            # 유일성 검사
            check_set = set()
            for r in relation:
                s = ""
                for i in k:
                    s += r[i]
                
                check_set.add(s)
            
            if len(check_set) != len(relation):
                return False
            
            return True
        
        # 메인 로직
        for count in range(1, len(relation[0]) + 1):
            candi_list = get_candi(count)
            
            for candi in candi_list:
                if is_candi_key(candi):
                    candi_key.append(candi)
                    answer += 1
        
        return answer
    ```

- solution02 - 조합 라이브러리 활용

    ```python
    from itertools import combinations
    
    def solution(relation):
        key_items = [i for i in range(0, len(relation[0]))]
        candi_key = []
        answer = 0
        
        # 메서드
        def is_candi_key(k):
            # 최소성 검사
            for rk in candi_key:
                if rk.issubset(k):
                    return False
            
            # 유일성 검사
            check_set = set()
            for r in relation:
                s = ""
                for i in k:
                    s += r[i]
                
                check_set.add(s)
            
            if len(check_set) != len(relation):
                return False
            
            return True
        
        # 메인 로직
        for count in range(1, len(relation[0]) + 1):
            candi_list = [set(combi) for combi in combinations(key_items, count)]
            
            for candi in candi_list:
                if is_candi_key(candi):
                    candi_key.append(candi)
                    answer += 1
        
        return answer
    ```


### Java 풀이

- solution01

    ```java
    import java.util.*;
    
    class Solution {
        
        String[][] relation;
        List<Set<Integer>> candiKey = new ArrayList<>();
        
        public int solution(String[][] relation) {
            this.relation = relation;
            
            // 메인 로직
            int answer = 0;
            for (int count = 1; count <= relation[0].length; count++) {
                List<Set<Integer>> candiList = getCandiList(count);
                
                for (Set<Integer> c : candiList) {
                    if (isCandidateKey(c)) {
                        candiKey.add(c);
                        answer += 1;
                    }
                }
            }
            
            return answer;
        }
        
        private List<Set<Integer>> getCandiList(int count) {
            List<Set<Integer>> result = new ArrayList<>();
            getCombi(result, new ArrayList<Integer>(), count, 0);
            
            return result;
        }
        
        private void getCombi(List<Set<Integer>> result, List<Integer> temp, int count, int start) {
            if (temp.size() == count) {
                result.add(new HashSet<>(temp));
                return;
            }
            
            for (int i = start; i < relation[0].length; i++) {
                temp.add(i);
                getCombi(result, temp, count, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
        
        private boolean isCandidateKey(Set<Integer> k) {
            // 최소성 검사
            if (k.size() >= 2) {
                for (Set<Integer> item : candiKey) {
                    if (k.containsAll(item)) {
                        return false;
                    }
                }
            }
            
            // 유일성 검사
            Set<String> keySet = new HashSet<>();
            for (String[] r : relation) {
                StringBuilder sb = new StringBuilder();
                for (int ki : k) {
                    sb.append(r[ki]);
                }
                
                keySet.add(sb.toString());
            }
            
            if (keySet.size() != relation.length) {
                return false;
            }
            
            return true;
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- 이번 문제에서 배운 내용은 다음과 같다.
    - a가 b의 부분 집합인지 여부를 체크하는 방법
    - StringBuilder 활용법
    - 조합 구하는 로직 직접 구현
    - List ↔ Set 자료 구조 변환