## 👀 제한 시간 안에 어디까지 해냈는가?

`10분 15초`만에 문제를 해결했다.

P & J 트레이닝

- Java로 처음 시도하여 `7분 12초`만에 문제를 해결했다.
- Python으로 2차 풀이를 시도하여 `3분 33초`만에 문제를 해결했다.

---

## 🧑‍🔬 문제 분석

시간별 주식 가격 리스트가 주어질 때 각 시간대에서 가격이 떨어지기까지 얼마나 걸렸는지를 반환하는 문제이다.

문제에 주어진 제약 조건은 다음과 같다.

### 제한사항

- prices의 각 가격은 1 이상 10,000 이하인 자연수입니다.
- prices의 길이는 2 이상 100,000 이하입니다.

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

전형적인 stack 연습 문제이다. 예전에 풀었던 릿코드의 온도 변화 문제와 유사한 유형이라 쉽게 풀이를 떠올릴 수 있었다.

1. 주어진 리스트를 반복할 때 현재 요소의 순서, 값을 기준으로 stack 윗부분에서 현재 주식 가격보다 높은 가격의 요소가 있으면 제거하면서 떨어진 시간을 기록한다.
2. stack에는 값과 함께 해당 값이 속해있던 리스트의 순번을 같이 넣어준다. 이 순번을 가지고 1의 계산을 진행한다.

---

## 🏃 코드 작성 과정

### 내가 처음 작성한 코드

```python
def solution(prices):
    stack = []
    answer = [-1] * len(prices)
    for idx, p in enumerate(prices):
        while stack and stack[-1][1] > p:
            popped = stack.pop()
            answer[popped[0]] = idx - popped[0]
        
        stack.append((idx, p))
    
    for item in stack:
        answer[item[0]] = len(prices) - item[0] - 1
    
    return answer
```

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01

    ```python
    def solution(prices):
        stack = []
        answer = [0] * len(prices)
        
        for day in range(len(prices)):
            if stack:
                while stack and stack[-1][0] > prices[day]:
                    popped = stack.pop()
                    answer[popped[1]] = day - popped[1]
            
            stack.append((prices[day], day))
        
        while stack:
            popped = stack.pop()
            answer[popped[1]] = (len(prices) - 1) - popped[1]
        
        return answer
    ```


### Java 풀이

- solution01

    ```java
    import java.util.*;
    
    class Solution {
        public int[] solution(int[] prices) {
            Deque<int[]> dq = new ArrayDeque<>();
            int[] answer = new int[prices.length];
            
            for (int day = 0; day < prices.length; day++) {
                if (!dq.isEmpty()) {
                    while (!dq.isEmpty() && dq.peek()[0] > prices[day]) {
                        int[] popped = dq.pop();
                        answer[popped[1]] = day - popped[1];
                    }
                }
                
                dq.push(new int[]{prices[day], day});
            }
            
            while (!dq.isEmpty()) {
                int[] popped = dq.pop();
                answer[popped[1]] = (prices.length - 1) - popped[1];
            }
            
            return answer;
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- 쉬운 stack 문제였다.
- 다만 중간에 로직 실수가 있어서 몇번 테스트 케이스 통과를 못했고, 더 빨리 풀 수 있었는데 10분을 초과했다. 이런 실수는 하지 않도록 주의하자.