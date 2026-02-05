## 👀 제한 시간 안에 어디까지 해냈는가?

`14분`만에 문제를 풀었다. 제출한 정답 코드는 다음과 같다.

```python
class Solution:
    def canVisitAllRooms(self, rooms: List[List[int]]) -> bool:
        # 메인 로직
        queue = []
        visited = [False for _ in rooms]
        
        queue.append(0)
        visited[0] = True
        while queue:  # 실수한 부분
            cur = queue[0]
            queue.pop(0)

            for next in rooms[cur]:
                if not visited[next]:
                    queue.append(next)
                    visited[next] = True
        
        print(visited)
        for v in visited:
            if not v:
                return False
        
        return True
```

- 풀이법(BFS/DFS)은 시작 2분만에 떠올렸지만 코드 실수로 인해 시간을 많이 잡아먹고 결국 정답 처리를 받는데 14분이나 걸렸다.
- `while queue` 부분을 `while not queue` 로 잘못 작성해서 처음에 좀 많이 해맸다.
- queue의 첫번재 요소를 꺼낸 후 제거하고 싶은데 `queue.pop(index)` 문법을 떠올리지 못해 결국 중간에 검색을 했다.

---

## 🧑‍🔬 문제 분석

다른 방에 접근할 수 있는 key 리스트를 요소로한 리스트가 주어졌을 때 0번째 요소에서 시작하여 모든방을 탐색할 수 있는지 여부를 반환하는 문제이다.

문제에 주어진 제약 조건은 다음과 같다.

**Constraints:**

- `n == rooms.length`
- `2 <= n <= 1000`
- `0 <= rooms[i].length <= 1000`
- `1 <= sum(rooms[i].length) <= 3000`
- `0 <= rooms[i][j] < n`
- All the values of `rooms[i]` are **unique**.

---

## 🤔 풀이 고민

### BFS/DFS

문제를 보자마자 전형적인 BFS/DFS 연습 문제임을 알 수 있었다. 입력되는 배열의 크기도 최대 1000, 각 요소에 들어있는 다른방의 키 개수도 최대 1000으로 비교적 작기 때문에 BFS/DFS중 하나로 충분히 문제를 해결할 수 있다.

### 결론

- 전형적인 BFS/DFS 연습 문제이다. 둘 중 아무거나 사용해도 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 최종 정답 코드 개선

처음 정답 처리를 받은 코드는 다음과 같다.

```python
class Solution:
    def canVisitAllRooms(self, rooms: List[List[int]]) -> bool:
        # 메인 로직
        queue = []
        visited = [False for _ in rooms]
        
        queue.append(0)
        visited[0] = True
        while queue:  # 실수한 부분
            cur = queue[0]
            queue.pop(0)

            for next in rooms[cur]:
                if not visited[next]:
                    queue.append(next)
                    visited[next] = True
        
        print(visited)
        for v in visited:
            if not v:
                return False
        
        return True
```

- 어찌어찌 문제를 해결했지만 위에 기술한대로 로직 실수 + list.pop(index) 문법이 떠오르지 않아 시간을 14분이나 사용했다.

GPT에게 부탁해 코드를 더 깔끔하게 개선해보았다.

```python
from collections import deque

class Solution:
    def canVisitAllRooms(self, rooms: List[List[int]]) -> bool:
        queue = deque([0])
        visited = [False for _ in rooms]
        visited[0] = True

        while queue:
            cur = queue.popleft()
            for nxt in rooms[cur]:
                if not visited[nxt]:
                    visited[nxt] = True
                    queue.append(nxt)

        return all(visited)
```

- 단순히 리스트를 사용하는것 보다 deque를 사용하면 시간 복잡도에서 더 성능이 좋다고 한다.
    - list.pop(0) → **`O(n)`** (첫 요소를 빼면, 나머지 모든 요소를 한 칸씩 앞으로 당김)
    - deque.popleft() → **`O(1)`** (양방향 큐 구조라 바로 제거 가능)
- `all()`은 리스트 요소 중 하나가 False로 평가되면 False를 반환한다고 한다.
    - False, 0, None, "" (빈 문자열), [], {}, () (빈 컨테이너)
    - 대신 빈 리스트면 True 반환

---
## 🥰 배운점 & 느낀점

- 이번에도 풀이법은 빠르게 떠올렸는데 로직 실수 + 적절한 파이썬 문법이 떠오르지 않아 오랜 시간이 걸렸다.
- 유용한 파이썬 문법은 한번 정리해서 암기가 필요한거 같다.
- Deque가 list보다 삽입/삭제가 빠른 이유 (내부적으로 Doubled Linked List 사용)

  [[파이썬] 덱 vs 리스트 속도 차이? (deque vs list speed 차이)](https://wellsw.tistory.com/122#google_vignette)