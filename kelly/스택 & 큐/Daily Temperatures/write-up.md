## 👀 제한 시간 안에 어디까지 해냈는가?

- Java로 처음 도전하여 `7분 40초`만에 문제를 해결했다.
- Python으로 2차 풀이를 진행하여 `3분 20초`만에 문제를 해결했다.

---

## 🧑‍🔬 문제 분석

각 날짜의 온도가 주어질 때 각 날짜별로 현재보다 더 따뜻한 날짜가 오기까지 얼마의 날이 흘렀는지를 찾아 반환하는 문제이다.

문제에 주어진 제약 조건은 다음과 같다.

**Constraints:**

- `1 <= temperatures.length <= 105`
- `30 <= temperatures[i] <= 100`

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

문제를 보자마자 stack 풀이를 떠올렸다.

- stack이 비었다면 현재 온도/날짜 정보를 stack에 삽입
- stack이 비어있지 않다면 stack의 끝부터 현재 온도와 비교해서 현재 온도보다 낮다면 끝 데이터를 꺼내 `현재 날짜 - 꺼낸 데이터의 날짜` 값을 정답 배열에 삽입
    - 만약 끝 데이터의 온도가 더 높다면 현재 온도/날짜 데이터를 stack에 삽입

문제에 주어지는 온도 리스트의 최대 크기는 `10^5`이므로 stack 연산으로 충분히 풀 수 있을거란 확신이 들었다.

### 결론

- Stack을 활용해 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 내가 처음 작성한 코드

혼자서 무리없이 Java & Python으로 풀이 코드를 작성했다. 최종 정답 코드를 참고하자.

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01

    ```python
    class Solution:
        def dailyTemperatures(self, temperatures: List[int]) -> List[int]:
            answer = [0] * len(temperatures)
            stack = []
    
            for day in range(len(temperatures)):
                if not stack:
                    stack.append((day, temperatures[day]))
                    continue
                
                while stack and stack[-1][1] < temperatures[day]:
                    popped = stack.pop()
                    answer[popped[0]] = day - popped[0]
                
                stack.append((day, temperatures[day]))
            
            return answer
    ```


### Java 풀이

- solution01

    ```java
    import java.util.*;
    
    class Solution {
        public int[] dailyTemperatures(int[] temperatures) {
            int[] answer = new int[temperatures.length];
            Deque<int[]> dq = new ArrayDeque<>();
    
            for (int day = 0; day < temperatures.length; day++) {
                if (dq.isEmpty()) {
                    dq.push(new int[]{day, temperatures[day]});
                    continue;
                }
    
                while (!dq.isEmpty() && dq.peek()[1] < temperatures[day]) {
                    int[] popped = dq.pop();
                    answer[popped[0]] = day - popped[0];
                }
    
                dq.push(new int[]{day, temperatures[day]});
            }
    
            return answer;
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- stack을 활용할 수 있는 사례를 배웠다.