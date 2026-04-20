## 👀 제한 시간 안에 어디까지 해냈는가?

`16분 33초`만에 혼자서 문제를 해결했다.

---

## 🧑‍🔬 문제 분석

작업별 우선 순위를 담은 리스트와 대상 작업 순번이 주어졌을 때 대상 작업이 완료되는 순서를 찾아 반환하는 문제이다.

문제에 주어지는 제약 조건은 다음과 같다.

### 제한사항

- `priorities`의 길이는 1 이상 100 이하입니다.
    - `priorities`의 원소는 1 이상 9 이하의 정수입니다.
    - `priorities`의 원소는 우선순위를 나타내며 숫자가 클 수록 우선순위가 높습니다.
- `location`은 0 이상 (대기 큐에 있는 프로세스 수 - 1) 이하의 값을 가집니다.
    - `priorities`의 가장 앞에 있으면 0, 두 번째에 있으면 1 … 과 같이 표현합니다.

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

우선순위 큐를 활용하는 풀이를 떠올렸다.

1. 작업을 담을 큐와, 현재 작업 큐 상태에서 가장 높은 우선순위를 가진 작업을 빠르게 찾기 위해 사용할 우선순위 큐를 정의해서
2. 큐를 순회하며 현재 작업 큐 중 가장 우선순위가 높은 작업이 나오면 큐에서 제거하고,
3. 만약 대상 작업이 큐에서 빠지는 상황일 경우 현재 순서를 반환하도록 코드를 작성하면 문제를 쉽게 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 내가 처음 작성한 코드

```python
from collections import deque
from heapq import heapify, heappush, heappop

def solution(priorities, location):
    # 메인 로직
    dq = deque()
    heap = []
    for idx, pri in enumerate(priorities):
        dq.append((idx, pri))
        heappush(heap, -pri)
    
    answer = 1
    while dq:
        idx, pri = dq.popleft()
        maximum = -heappop(heap)
        
        if pri == maximum:
            if idx == location:
                return answer
            
            answer += 1
            continue
        
        dq.append((idx, pri))
        heappush(heap, -maximum)
    
    return -1
```

---

## 🥰 배운점 & 느낀점

- 우선순위 큐 사용을 위해 `from heapq import` 를 작성할 때 메서드 이름들이 헷갈렸다. 빠른 사용을 위해 직접 정의해야하는 메서드 이름을 한 번 정리하고 복습할 필요가 있다.
    - heapify(list)
    - heappush(list, 1)
    - heappop(list)
