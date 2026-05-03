## 👀 제한 시간 안에 어디까지 해냈는가?

`12분 50초`만에 혼자 문제를 풀긴 했는데 잘못된 로직 하나를 찾지 못해 AI의 도움을 받았다…

P & J 트레이닝

- Java로 도전하여 `21분 36초`만에 문제를 해결했다.
- Python으로 2차 풀이를 시도하여 `4분 24초`만에 문제를 해결했다.

---

## 🧑‍🔬 문제 분석

작업 목록, 각 작업의 하루 진행 속도가 주어질 때 문제 조건에 맞춰 하루에 최대 몇개까지 작업 배포가 가능한지 리스트를 찾아 반환하는 문제이다.

- 자세한 조건은 문제를 참고하자.

문제에 주어지는 제약 조건은 다음과 같다.

### 제한 사항

- 작업의 개수(progresses, speeds배열의 길이)는 100개 이하입니다.
- 작업 진도는 100 미만의 자연수입니다.
- 작업 속도는 100 이하의 자연수입니다.
- 배포는 하루에 한 번만 할 수 있으며, 하루의 끝에 이루어진다고 가정합니다. 예를 들어 진도율이 95%인 작업의 개발 속도가 하루에 4%라면 배포는 2일 뒤에 이루어집니다.

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

큐를 활용하는 풀이를 생각해냈다.

1. 큐가 비어있지 않은동안 순회하면서
    1. 큐의 가장 앞에 있는 요소를 꺼내 해당 작업이 완료되기까지 걸리는 일수를 구한다.
    2. 큐의 두 번째 요소부터 순회하면서 첫 번째 요소의 작업 기간동안 작업이 끝나는 요소를 모두 큐에서 빼두면서 당일 배포 개수를 갱신한다.
    3. 최종 갱신 배포 개수를 정답 리스트에 추가한다.
2. 1 작업을 거치며 최종 반영된 정답 리스트를 반환한다.

---

## 🏃 코드 작성 과정

### 내가 처음 작성한 코드

코드는 거의 혼자서 작성했는데, 잘못된 논리 오류를 하나 빠르게 찾지 못해서 AI의 도움을 받았다.

- 첫 번째 작업이 완료되기 까지 필요한 일수를 구하는 로직에서 올림 처리 로직을 잘못 작성했다. 잘못 작성된 로직과 수정된 로직은 각각 다음과 같다.

  `today_count + 1` → `today_count += 1`


최종적으로 제출한 코드는 다음과 같다. 이 코드는 정답 처리를 받는다.

```python
from collections import deque

def solution(progresses, speeds):
    # 메인 로직
    dq = deque()
    for idx, item in enumerate(progresses):
        dq.append((item, idx))
    
    answer = []
    while dq:
        today_count = 1
        first_p, first_idx = dq.popleft()
        first_need_day = (100 - first_p) // speeds[first_idx]
        if first_need_day * speeds[first_idx] < 100 - first_p:
            first_need_day += 1
        
        while dq:
            cur_item, cur_idx = dq[0]
            if cur_item + (speeds[cur_idx] * first_need_day) >= 100:
                dq.popleft()
                today_count += 1
            else:
                break
        
        answer.append(today_count)
    
    return answer
```

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01

    ```python
    from collections import deque
    from math import ceil
    
    def solution(progresses, speeds):
        dq = deque([i for i in range(len(progresses))]);
        answer = []
        
        while dq:
            popped = dq.popleft()
            count = 1
            need_day = 0
            if (progresses[popped] < 100):
                need_day = ceil((100 - progresses[popped]) / speeds[popped])
            
            for i in range(popped + 1, len(progresses)):
                progresses[i] += need_day * speeds[i]
            
            while dq and progresses[dq[0]] >= 100:
                dq.popleft()
                count += 1
            
            answer.append(count)
        
        return answer
    ```


### Java 풀이

- solution01

    ```java
    import java.util.*;
    
    class Solution {
        public int[] solution(int[] progresses, int[] speeds) {
            Deque<Integer> dq = new ArrayDeque<>();
            for (int i = 0; i < progresses.length; i++) {
                dq.offer(i);
            }
            
            List<Integer> answer = new ArrayList<>();
            while (!dq.isEmpty()) {
                int popped = dq.poll();
                int count = 1;
                int needDay = 0;
                if (progresses[popped] < 100) {
                    needDay = (int) Math.ceil((100.0 - progresses[popped]) / speeds[popped]);
                }
                
                for (int i = popped + 1; i < progresses.length; i++) {
                    progresses[i] += (needDay * speeds[i]);
                }
                
                while (!dq.isEmpty() && progresses[dq.peek()] >= 100) {
                    dq.poll();
                    count++;
                }
                
                answer.add(count);
            }
            
            int[] arrAnswer = new int[answer.size()];
            for (int i = 0; i < answer.size(); i++) {
                arrAnswer[i] = answer.get(i);
            }
            
            return arrAnswer;
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- 혼자서 잘 풀었는데 딱 사소한 하나의 논리 오류 때문에 문제를 틀렸다. 이런 억울한 실수를 하지 않도록 주의할 필요가 있다.
- 소수의 올림, 내림, 반올림 함수를 익혔다.