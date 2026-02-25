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

---

## 🥰 배운점 & 느낀점

- 아이디어는 떠올렸지만 고려하지 못한 부분도 있었고 손이 더 빨랐으면 아마 테스트케이스는 통과하는 코드를 작성했을것이다. 코드를 더 빨리 구현하는 연습을 우선 해야겠다.