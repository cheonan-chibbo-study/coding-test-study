## 👀 제한 시간 안에 어디까지 해냈는가?

`25분`만에 혼자서 문제를 풀 수 있었다.

---

## 🧑‍🔬 문제 분석

토핑 정보를 담은 리스트 topping이 주어질 때 두 사람이 같은 같은 개수 종류만큼 토핑을 먹을 수 있도록 케이크를 자를 수 있는 경우의 수 개수를 구해 반환하는 문제이다.

문제에 주어지는 제약 조건은 다음과 같다.

### 제한사항

- 1 ≤ `topping`의 길이 ≤ 1,000,000
- 1 ≤ `topping`의 원소 ≤ 10,000

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

우선 토핑 리스트의 최대 요소 개수가 1,000,000이기 때문에 `N2` 이상의 시간이 필요한 알고리즘은 사용할 수 없다고 생각했다. 따라서 `O(N)` 의 시간이 걸리는 풀이를 생각하다 `O(1)`의 시간이 소요되는 자료구조 set/dict을 활용하도록 풀이를 떠올렸다.

1. 빈 set_a, topping 각 요소의 개수를 담은 dict_b 정의
2. topping을 순회하면서 set_a에 현재 요소 추가, dict_b에서 현재 요소의 개수를 1개 감소
    1. 만약 dict_b[현재 요소] 개수가 0이면 해당 요소는 제거
3. set_a의 요소 개수와 dict_b의 키 개수가 같으면 정답 정수를 하나 증가
4. 순회를 마치고 정답 정수 반환

---

## 🏃 코드 작성 과정

### 내가 처음 작성한 코드

```python
from collections import Counter

def solution(topping):
    # 메인 로직
    set_a = set()
    dict_b = Counter(topping)
    answer = 0
    
    for t in topping:
        set_a.add(t)
        dict_b[t] -= 1
        if dict_b[t] == 0:
            del dict_b[t]
        
        if len(set_a) == len(dict_b):
            answer += 1
    
    return answer
```

---

## 🥰 배운점 & 느낀점

- Counter 사용법과 import 방법이 좀 헷갈렸다. 유용하게 사용한 코드 패턴이나 라이브러리를 한번 정리하는 시간을 가질 필요가 있을거 같다.
