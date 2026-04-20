## 👀 제한 시간 안에 어디까지 해냈는가?

혼자서 코드 구현을 하지 못했다.

---

## 🧑‍🔬 문제 분석

다리의 길이와 버틸 수 있는 최대 무게, 트럭 무게 리스트가 주어졌을 때 모든 트럭이 다리를 건너는데 소요되는 최단 시간을 반환하는 문제이다.

문제에 주어지는 제약 조건은 다음과 같다.

### 제한 조건

- bridge_length는 1 이상 10,000 이하입니다.
- weight는 1 이상 10,000 이하입니다.
- truck_weights의 길이는 1 이상 10,000 이하입니다.
- 모든 트럭의 무게는 1 이상 weight 이하입니다.

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

풀이 자체는 사실 직관적이다. 매번 다리위에 트럭을 하나 올리기 전 트럭이 올라갈 수 있는 상황인지 검사하면서 다리위에 있는 트럭의 이동 시간들을 갱신해주도록 코드를 작성하면 된다.

다만 코드 구현에서 계속 비효율적인 코드가 나와 시간 초과를 해결하지 못했고, 결국 다른 사람의 풀이 코드를 참고했다.

[[프로그래머스 / 큐(L.v2)] 다리를 지나는 트럭 - 파이썬](https://velog.io/@snghyun331/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%81%90L.v2-%EB%8B%A4%EB%A6%AC%EB%A5%BC-%EC%A7%80%EB%82%98%EB%8A%94-%ED%8A%B8%EB%9F%AD-%ED%8C%8C%EC%9D%B4%EC%8D%AC#%EC%BD%94%EB%93%9C1%EA%B3%BC%EC%9D%98-%EC%B0%A8%EC%9D%B4%EC%A0%90)

---

## 🏃 코드 작성 과정

### 찾아본 풀이로 작성한 코드

위 블로그에서 풀이 코드를 참고해 아래와 같이 코드를 작성했다. 이 코드를 제출하면 문제를 해결할 수 있다.

```python
from collections import deque

def solution(bridge_length, weight, truck_weights):
    bridge = deque([0] * bridge_length)
    wait = deque(truck_weights)
    cur_weight = 0
    time = 0
    
    while wait:
        time += 1
        cur_weight -= bridge.popleft()
        
        if not wait:
            continue
            
        if cur_weight + wait[0] <= weight:
            cur_weight += wait[0]
            bridge.append(wait.popleft())
        else:
            bridge.append(0)
    
    time += bridge_length
    
    return time
```

---

## 🥰 배운점 & 느낀점

- 다른 사람의 풀이 코드를 보고 매우 감탄했다. 어떻게 이렇게 쉽게 코드 작성을 할 수 있는지..
- 비슷한 유형의 문제가 나왔을 때 이 테크닉을 써먹을 수 있을거 같으니 열심히 복습하자.