## 👀 제한 시간 안에 어디까지 해냈는가?

`13분 34초`만에 혼자서 문제를 해결할 수 있었다. 큰 어려움 없이 쉽게 문제를 풀었다.

P & J 트레이닝

- Python으로 1차 풀이를 시도하여 `13분 42초`만에 문제를 해결했다.
- Java로 2차 풀이를 시도하여 `17분 14초`만에 문제를 해결했다.

---

## 🧑‍🔬 문제 분석

유저 닉네임 리스트, 신고자 → 대상자 문자열 정보를 담은 리스트와 이용 정지 조건의 신고 횟수가 주어질 때 각 사용자들이 신고 정지에 관련해 몇번 이메일 알림을 받을지를 리스트로 반환해야하는 문제이다.

- 자세한 문제 조건은 문제를 참고하자.

문제에 주어진 제약 조건은 다음과 같다.

### 제한사항

- 2 ≤ `id_list`의 길이 ≤ 1,000
    - 1 ≤ `id_list`의 원소 길이 ≤ 10
    - `id_list`의 원소는 이용자의 id를 나타내는 문자열이며 알파벳 소문자로만 이루어져 있습니다.
    - `id_list`에는 같은 아이디가 중복해서 들어있지 않습니다.
- 1 ≤ `report`의 길이 ≤ 200,000
    - 3 ≤ `report`의 원소 길이 ≤ 21
    - `report`의 원소는 "이용자id 신고한id"형태의 문자열입니다.
    - 예를 들어 "muzi frodo"의 경우 "muzi"가 "frodo"를 신고했다는 의미입니다.
    - id는 알파벳 소문자로만 이루어져 있습니다.
    - 이용자id와 신고한id는 공백(스페이스)하나로 구분되어 있습니다.
    - 자기 자신을 신고하는 경우는 없습니다.
- 1 ≤ `k` ≤ 200, `k`는 자연수입니다.
- return 하는 배열은 `id_list`에 담긴 id 순서대로 각 유저가 받은 결과 메일 수를 담으면 됩니다.

---

## 🤔 풀이 고민

### 내가 처음 떠올린 풀이

문제를 읽자마자 쉽게 풀이를 떠올릴 수 있었다. 이 문제는 해시 테이블과 해시 셋을 활용하면 간단하게 해결할 수 있다.

1. 신고자 → 대상자 배열을 순회하면서 딕셔너리에 `dict[대상자] = set(신고자의 인덱스)` 구조로 신고 정보를 가공한다.
2. 위에서 가공한 딕셔너리를 순회하면서 신고당한 횟수가 k이상인 데이터를 골라 사용자별 이메일 수신 횟수를 업데이트한다.
3. 위에서 업데이트한 최종 수신 횟수 리스트를 반환한다.

문제에 주어지는 리스트들의 입력이 크지 않으므로 위에 기술한 흐름으로 코드를 작성해도 충분히 문제를 풀 수 있다.

### 찾아본 풀이

문제에서 정의한 신고에 의하면, 중복되는 신고 정보에 대해 무시한다고 한다. 따라서 set을 이용해서 중복되는 신고 정보를 제외한다.

사용자가 처리 결과 메일을 받는 조건은 사용자가 신고한 유저가 최종적으로 k번 이상의 신고를 받아서 정지를 당하는 경우다. 따라서 사용자별 신고당한 횟수를 기록하기 위해 “`key`: 사용자 ID, `value`: 신고 당한 횟수” 로 하여 해시테이블을 구성한다. 그리고 set으로 정리된 신고 정보에 기반하여 사용자별 신고당한 횟수를 기록한다.

마지막으로 모든 신고 정보를 확인하며, 신고당한 사용자의 신고 횟수가 일정 기준치(k번) 이상이라면, 신고자가 메일을 수신하는 횟수를 1 증가시킨다.

```python
#✅ report를 해시셋에 추가해 중복을 제거한다.
#✅ 각 id마다 신고당한 횟수를 저장하는 해시테이블을 생성한다.
#✅ report 해시셋을 순회하며 신고대상의 신고당한 횟수가 k를 넘어서는 경우를 찾는다.
	#✅ 신고자의 메일 받는 횟수를 1 증가시킨다.
```

### 결론

- 간단한 문자열 처리 테크틱 + 해시 테이블 + 해시 셋을 활용하면 문제를 충분히 해결할 수 있다.
- 코드 흐름 자체는 찾아본 풀이가 훨씬 깔끔한거 같다.

---

## 🏃 코드 작성 과정

### 최종 정답 코드 개선

혼자 작성한 코드는 다음과 같다.

```python
from collections import defaultdict

def solution(id_list, report, k):
    # 메인 로직
    indexes = {}
    for idx, name in enumerate(id_list):
        indexes[name] = idx

    record = defaultdict(set)
    for r in report:
        req, target = r.split(" ")
        record[target].add(indexes[req])
    
    answer = [0] * len(id_list)
    for v in record.values():
        if len(v) >= k:
            for idx in v:
                answer[idx] += 1
        
    return answer
```

하지만 찾아본 풀이가 훨씬 간결하기도 하고 시간 복잡도 상으로도 유리해서 아래 코드를 복습하는게 좋을거 같다.

```python
def solution(id_list, report, k):
    report_set = set(report)
    answer = [0] * len(id_list)
    
    record = {x: 0 for x in id_list}
    for r in report_set:
        record[r.split()[1]] += 1
    
    for r in report_set:
        req, target = r.split()
        if record[target] >= k:
            answer[id_list.index(req)] += 1

    return answer
```

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01

    ```python
    from collections import defaultdict
    
    def solution(id_list, report, k):
        # 메인 로직
        history = defaultdict(set)
        
        for r in report:
            req, target = r.split(" ")
            history[target].add(req)
        
        call_count = defaultdict(int)
        for v in history.values():
            if len(v) < k:
                continue
            
            for req in v:
                call_count[req] += 1
        
        answer = []
        for id in id_list:
            answer.append(call_count[id])
        
        return answer
    ```


### Java 풀이

- solution01

    ```java
    import java.util.*;
    
    class Solution {
        public int[] solution(String[] id_list, String[] report, int k) {
            Map<String, Set<String>> history = new HashMap<>();
            for (String r : report) {
                String[] rSplit = r.split(" ");
                history.computeIfAbsent(rSplit[1], key -> new HashSet<>()).add(rSplit[0]);
            }
            
            Map<String, Integer> callCount = new HashMap<>();
            for (String id : id_list) {
                Set<String> reqs = history.computeIfAbsent(id, key -> new HashSet<>());
                
                if (reqs.size() < k) {
                    continue;
                }
                
                for (String req : reqs) {
                    callCount.put(req, callCount.getOrDefault(req, 0) + 1);   
                }
            }
            
            int[] answer = new int[id_list.length];
            for (int i = 0; i < id_list.length; i++) {
                answer[i] = callCount.getOrDefault(id_list[i], 0);
            }
            
            return answer;
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- 이번 문제는 혼자서 쉽게 해결할 수 있었다. 딕셔너리와 셋 사용법이 능숙해진거 같아서 뿌듯하다.
- 그래도 혼자 작성한 코드보다는 찾아본 풀이가 더 나은거 같아 이 풀이를 복기해야겠다.