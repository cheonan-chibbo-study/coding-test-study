# 문제링크
https://www.codetree.ai/ko/frequent-problems/samsung-sw/problems/street-light-installation/submissions?page=1&page_size=20
# 30분내 어디까지 풀었는가
풀지못함
# 왜 못풀었는가 - 
### 풀이가 안떠올랐는지 / 어떻게 풀지는 알겠는데 구현을 못하겠다.
풀이를 떠올리지 못했다.

# 접근방법

처음에는 `ArrayList`에 가로등 위치를 저장한 뒤,

- 200 : 가장 긴 구간을 탐색하여 중간에 삽입
- 300 : 해당 가로등 삭제
- 400 : 모든 구간을 탐색하여 최대 거리 계산

방식으로 생각하였다.

하지만 Q의 크기가 크기 때문에 매 연산마다 전체를 탐색하면 시간초과가 발생한다.

따라서 다음과 같은 자료구조를 사용하였다.

- `PriorityQueue` : 가장 긴 구간을 빠르게 찾기 위해 사용
- `prev[]`, `next[]` : 가로등들의 인접 관계를 연결 리스트 형태로 관리
- `alive[]` : 삭제 여부 관리
- `pos[]` : 가로등 번호별 위치 저장

구간은

```java
Gap(left, right, dist)
```

형태로 PriorityQueue에 저장한다.

PriorityQueue는

1. 구간 길이(dist)가 큰 순
2. 길이가 같다면 왼쪽 가로등의 좌표가 작은 순

으로 정렬한다.

가로등이 추가되거나 삭제되면 기존 구간 정보가 PriorityQueue 안에 남게 되는데, 이를 즉시 삭제하면 O(N)이 발생한다.

따라서 Lazy Deletion 기법을 사용하였다.

구간을 사용할 때마다

```java
alive[left]
alive[right]
next[left] == right
```

를 확인하여 현재도 유효한 구간인지 검사하고, 유효하지 않은 구간은 제거한다.

### 200 (가로등 추가)

가장 긴 유효 구간을 꺼낸 뒤

```java
(L + R + 1) / 2
```

위치에 새로운 가로등을 설치한다.

이후

```text
left --- new --- right
```

형태로 연결 관계를 수정하고,

```text
(left, new)
(new, right)
```

두 구간을 PriorityQueue에 추가한다.

### 300 (가로등 제거)

삭제할 가로등의 양 옆 가로등을 연결한다.

```text
left --- delete --- right
```

↓

```text
left ----------- right
```

새로 생성된 구간을 PriorityQueue에 추가한다.

### 400 (최소 전력 계산)

최소 전력 r에 2를 곱한 값은

```text
왼쪽 끝까지 필요한 거리 × 2
오른쪽 끝까지 필요한 거리 × 2
인접한 가로등 사이 최대 거리
```

중 최댓값과 같다.

따라서

```java
max(
    maxGap,
    2 * (pos[first] - 1),
    2 * (N - pos[last])
)
```

을 출력한다.

---

# 시간/공간 복잡도

시간 복잡도:

- 200 : O(log Q)
- 300 : O(log Q)
- 400 : O(log Q)

전체:

```text
O(Q log Q)
```

공간 복잡도:

- pos[]
- prev[]
- next[]
- alive[]
- PriorityQueue

```text
O(Q)
```

---

# 배운점

- 연결 리스트를 배열(`prev[]`, `next[]`)로 구현하면 삽입/삭제를 O(1)에 처리할 수 있다.
- PriorityQueue에서 중간 원소를 삭제하는 것은 비효율적이므로 Lazy Deletion 기법을 사용할 수 있다.
- 구간 정보를 PriorityQueue에 저장하면 현재 가장 긴 구간을 O(log N)에 찾을 수 있다.
- 삼성 기출에서는 `PriorityQueue + 연결 리스트 + Lazy Deletion` 조합이 자주 등장한다.
- ArrayList로는 삽입/삭제 시 O(N)이 발생하므로 대량의 연산이 있는 문제에서는 적절하지 않을 수 있다.