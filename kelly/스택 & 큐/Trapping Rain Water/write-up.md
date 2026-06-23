## 👀 제한 시간 안에 어디까지 해냈는가?

`30`분 안에 문제를 해결하지 못했다. 22분쯤 문제를 어떻게 풀지 아이디어를 떠올리는데 성공하고 시간 4초를 남기고 코드를 작성해 제출했지만 테스트 케이스 하나를 만족하지 못해 오답 처리를 받았다. 떠올린 아이디어 자체는 올바른 방향이었지만 낮은 쪽 벽을 선정하는 부분과 중간 벽을 순회하기 위해 시작점을 선정하는 부분에 고려하지 못한 부분이 많아서 30분안에 풀지 못했을 것이다. 마지막으로 작성한 코드는 다음과 같다.

```python
# 22분에 아이디어 떠올림
# 4초 남기고 코드 작성은 했지만 테스트 케이스 1번 틀림

from collections import deque

class Solution:
    def trap(self, height: List[int]) -> int:
        # 메인 로직
        answer = [0] * len(height)
        dq = deque([])
        for i in range(len(height)):
            if not dq or height[dq[-1]] >= height[i]:
                dq.append(i)
                continue
            
            last_pop = None
            while dq and height[dq[-1]] < height[i]:
                last_pop = dq.pop()
            for j in range(last_pop + 1, i):
                answer[j] = height[last_pop] - height[j]
        
        return sum(answer)
```

시간안에 풀지는 못했지만 문제를 해결할 아이디어의 방향은 옳았기 때문에 미처 고려하지 못한 부분을 고민하면서 코드를 개선했고 결국 혼자서 정답 코드를 작성하는데 성공했다.

```python
from collections import deque

class Solution:
    def trap(self, height: List[int]) -> int:
        # 메인 로직
        answer = [0] * len(height)
        dq = deque([])
        for i in range(len(height)):
            if not dq or height[dq[-1]] >= height[i]:
                dq.append(i)
                continue
            
            last_pop = None
            while dq and height[dq[-1]] < height[i]:
                last_pop = dq.pop()

            low_wall = last_pop if not dq else i
            start = last_pop if not dq else dq[-1]
            for j in range(start + 1, i):
                answer[j] = height[low_wall] - height[j]
            
            dq.append(i)
        
        return sum(answer)
```

- 위에서 언급한 것 처럼 낮은 벽을 선정하는 코드와, 분기 시작 위치를 선정하는 코드에 고려하지 못한 여러 변수들이 있어 시간안에 풀지 못했다.
- 위 코드는 그러한 부분을 추가 고려해 작성한 코드이며 최종 정답 처리를 받은 코드이다.

P & J 트레이닝

- 천안 오프라인 스터디에서 파이썬으로 도전했는데 테케는 모두 맞췄지만 최종 채점에서 실패했다.

---

## 🧑‍🔬 문제 분석

일렬로 세워진 벽의 높이들을 담은 리스트가 입력되면 물이 고일 수 있는 최대양을 구해 반환하는 문제이다.

문제에 주어진 제약 조건은 다음과 같다.

**Constraints:**

- `n == height.length`
- `1 <= n <= 2 * 104`
- `0 <= height[i] <= 105`

---

## 🤔 풀이 고민

입력 배열의 최대 크기가 10^4이기 때문에 O(N)의 시간 복잡도 안으로 풀어야 하므로 제일 먼저 stack이나 queue 자료구조를 떠올렸다. 문제에 주어진 그림 예시를 살펴보면서 고일 수 있는 물의양을 어떻게 구할 수 있을까 생각해보니 queue보다는 stack이 더 적합하다는 생각이 들었다.

물론 이 문제는 단순히 stack을 활용하는걸 넘어 어느정도 아이디어를 떠올려야 하는 문제이다. 이미 이전에 stack + 아이디어가 필요한 문제를 풀어봐서인지 시간은 좀 걸렸지만 22분만에 문제를 해결할 아이디어를 떠올리긴했다. 다만 아이디어의 방향성 자체는 맞았지만 고려하지 못한 부분이 많아 30분만에 정답 코드를 작성할 수는 없었다. 그래도 고려하지 못한 부분을 생각하며 코드를 개선하니 다행이 정답 처리를 받을 수 있었다.

코드에 반복문이 들어가기는 하지만 리스트 전체를 매번 반복하는 코드가 아니고 대부분 stack의 pop, push, peek 작업이 대부분이기 때문에 Stack 활용으로 이 문제를 충분히 풀 수 있었다.

### 결론

- Stack 활용 + 아이디어 방식으로 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 틀린 부분 정정

30분안에 최종 작성한 코드는 다음과 같다.

```python
# 22분에 아이디어 떠올림
# 4초 남기고 코드 작성은 했지만 테스트 케이스 1번 틀림

from collections import deque

class Solution:
    def trap(self, height: List[int]) -> int:
        # 메인 로직
        answer = [0] * len(height)
        dq = deque([])
        for i in range(len(height)):
            if not dq or height[dq[-1]] >= height[i]:
                dq.append(i)
                continue
            
            last_pop = None
            while dq and height[dq[-1]] < height[i]:
                last_pop = dq.pop()
            for j in range(last_pop + 1, i):
                answer[j] = height[last_pop] - height[j]
        
        return sum(answer)
```

이 코드는 오답 처리를 받았는데 잘못된 부분은 다음과 같다.

- 고이는 물의 높이를 계산하려면 낮은쪽 벽을 기준으로 안쪽을 검사해야하는데 이 낮은쪽 벽을 선정하는 과정에 오류가 있었다. 단순 last_pop으로 선정하는게 아니라 현재 stack에 남아있는벽이 높은지, 검사 대상으로 선정한 벽이 낮은지를 고려해야했다.
- 두 벽 내부 고인물을 검사하는 반복문의 시작 지점을 선정하는 로직도 따로 작성해야한다. 낮은쪽 벽이 왼쪽인지 오른쪽인지에 따라 시작 지점이 달라지기 때문이다.

위 틀린 부분을 정정해 정답 처리를 받은 코드는 다음과 같다.

```python
from collections import deque

class Solution:
    def trap(self, height: List[int]) -> int:
        # 메인 로직
        answer = [0] * len(height)
        dq = deque([])
        for i in range(len(height)):
            if not dq or height[dq[-1]] >= height[i]:
                dq.append(i)
                continue
            
            last_pop = None
            while dq and height[dq[-1]] < height[i]:
                last_pop = dq.pop()

            low_wall = last_pop if not dq else i
            start = last_pop if not dq else dq[-1]
            for j in range(start + 1, i):
                answer[j] = height[low_wall] - height[j]
            
            dq.append(i)
        
        return sum(answer)
```

### 최종 정답 코드 개선

GPT에게 부탁해서 풀이를 다음과 같이 개선했다.

```python
from collections import deque

class Solution:
    def trap(self, height: List[int]) -> int:
        stack = []
        total = 0

        for i in range(len(height)):
            while stack and height[stack[-1]] < height[i]:
                bottom = stack.pop()

                if not stack:
                    break

                left = stack[-1]
                width = i - left - 1
                water_height = min(height[left], height[i]) - height[bottom]
                total += width * water_height

            stack.append(i)

        return total
```

- 이 코드의 아이디어는 골짜기를 찾을 때 마다 계산하는 방식인데 처음에는 코드만 보고 이해가 잘 안갈 수 있다. 풀이를 보며 문제에 대입해보면 금방 이해가 된다.
- 이 풀이를 사용하면 기존에 작성한 코드보다 훨씬 빠르다.
- 참고로 이 풀이를 모노토닉 스택이라고 부른다.
    - 🤖 모노토닉 스택 풀이가 빠른 이유

      **2. 두 번째 코드: "가로 블록 쌓기 풀이 (표준 모노토닉 스택)"**

      두 번째 코드는 LeetCode 등에서 이 문제를 스택으로 풀 때 사용하는 **가장 정석적이고 효율적인 "모노토닉 스택(Monotonic Stack)" 풀이**입니다.
      • **동작 특징:**
      ◦ 웅덩이의 가로 전체를 한 번에 채우는 게 아니라, **"바닥(bottom), 왼쪽 벽(left), 오른쪽 벽(i)"** 구조를 찾아내어 **웅덩이를 아래에서부터 가로 블록 형태로 한 층씩 쌓아 올리며 계산**합니다.
      ◦ 한 번 계산된 바닥(`bottom`)은 스택에서 완전히 제거(`pop`)되므로, 다음 연산에서 중복으로 계산되지 않습니다. 모든 원소가 스택에 최대 한 번 들어가고 한 번 나오기 때문에 완벽한 **$O(N)$ 시간 복잡도**를 보장합니다.
      • **이름을 붙인다면:** **"정석 모노토닉 스택 풀이"** 또는 "가로 층별 누적 풀이"라고 부릅니다.


---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01 - 모노토닉 스택

    ```python
    class Solution:
        def trap(self, height: List[int]) -> int:
            stack = []
            answer = 0
    
            for i in range(len(height)):
                while stack and height[stack[-1]] < height[i]:
                    bottom = stack.pop()
    
                    if not stack:
                        break
                    
                    left = stack[-1]
                    water = min(height[left], height[i]) - height[bottom]
                    answer += water * (i - left - 1)
                
                stack.append(i)
            
            return answer
    ```

- solution02 - 스터디에서 사용했던 방식

    ```python
    class Solution:
        def trap(self, height: List[int]) -> int:
            answer = [0] * len(height)
            stack = []
    
            for i in range(len(height)):
                if not stack or height[stack[-1]] >= height[i]:
                    stack.append(i)
                    continue
                
                last_popped = None
                while stack and height[stack[-1]] < height[i]:
                    last_popped = stack.pop()
                
                low_wall = last_popped if not stack else i
                start = last_popped if not stack else stack[-1]
                for j in range(start + 1, i):
                    answer[j] = height[low_wall] - height[j]
                
                stack.append(i)
            
            return sum(answer)
    ```


### Java 풀이

- solution01 - 모노토닉 스택

    ```java
    import java.util.*;
    
    class Solution {
        public int trap(int[] height) {
            Deque<Integer> stack = new ArrayDeque<>();
            int answer = 0;
    
            for (int i = 0; i < height.length; i++) {
                while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                    int bottom = stack.pop();
    
                    if (stack.isEmpty()) {
                        break;
                    }
    
                    int left = stack.peek();
                    int water = Math.min(height[left], height[i]) - height[bottom];
                    answer += water * (i - left - 1);
                }
    
                stack.push(i);
            }
    
            return answer;
        }
    }
    ```

- solution02 - 스터디에서 사용했던 방식

    ```java
    import java.util.*;
    
    class Solution {
        public int trap(int[] height) {
            Deque<Integer> stack = new ArrayDeque<>();
            int[] water = new int[height.length];
    
            for (int i = 0; i < height.length; i++) {
                if (stack.isEmpty() || height[stack.peek()] >= height[i]) {
                    stack.push(i);
                    continue;
                }
    
                int lastPopped = -1;
                while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                    lastPopped = stack.pop();
                }
    
                int lowWall = (stack.isEmpty()) ? lastPopped : i;
                int start = (stack.isEmpty()) ? lastPopped + 1 : stack.peek() + 1;
    
                for (int j = start; j < i; j++) {
                    water[j] = height[lowWall] - height[j];
                }
    
                stack.push(i);
            }
    
            int answer = 0;
            for (int w : water) {
                answer += w;
            }
    
            return answer;
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- 아이디어는 떠올렸지만 고려하지 못한 부분도 있었고 손이 더 빨랐으면 아마 테스트케이스는 통과하는 코드를 작성했을것이다. 코드를 더 빨리 구현하는 연습을 우선 해야겠다.