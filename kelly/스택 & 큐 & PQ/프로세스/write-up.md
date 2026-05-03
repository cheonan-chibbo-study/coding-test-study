## 👀 제한 시간 안에 어디까지 해냈는가?

`16분 33초`만에 혼자서 문제를 해결했다.

P & J 트레이닝

- Java로 처음 시도하여 `15분 34초`만에 문제를 해결했다.
- Python으로 2차 풀이를 시도하여 `7분 56초`만에 문제를 해결했다.

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

혼자서 무리없이 코드를 작성했다. 최종 정답 코드를 참고하자.

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01

    ```python
    from heapq import heappush, heappop
    from collections import deque
    
    def solution(priorities, location):
        pq = []
        dq = deque()
        for i, v in enumerate(priorities):
            heappush(pq, -v)
            dq.append((v, i))
        
        answer = 1
        while dq:
            v, i = dq.popleft()
            
            if v == -pq[0]:
                if i == location:
                    return answer
                
                heappop(pq)
                answer += 1
            else:
                dq.append((v, i))
        
        return -1
    ```


### Java 풀이

- solution01

    ```java
    import java.util.*;
    
    class Solution {
        public int solution(int[] priorities, int location) {
            Deque<int[]> dq = new ArrayDeque<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            
            for (int i = 0; i < priorities.length; i++) {
                dq.offer(new int[]{priorities[i], i});
                pq.offer(priorities[i]);
            }
            
            int answer = 1;
            while (!dq.isEmpty()) {
                int[] cur = dq.poll();
                
                if (cur[0] == pq.peek()) {
                    if (cur[1] == location) {
                        return answer;
                    }
                    
                    pq.poll();
                    answer++;
                } else {
                    dq.offer(cur);
                }
            }
            
            return -1;
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- Java & Python의 우선순위 큐 사용법을 익혔다.
- 우선순위 큐 사용을 위해 `from heapq import` 를 작성할 때 메서드 이름들이 헷갈렸다. 빠른 사용을 위해 직접 정의해야하는 메서드 이름을 한 번 정리하고 복습할 필요가 있다.
    - heapify(list)
    - heappush(list, 1)
    - heappop(list)