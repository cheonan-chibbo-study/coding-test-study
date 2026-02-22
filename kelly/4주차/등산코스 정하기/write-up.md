## 👀 제한 시간 안에 어디까지 해냈는가?

제한 시간 `30분` + 추가 시간 `30분`을 사용했지만 문제를 풀지 못했다. 이번에도 확실한 문제 해결책을 떠올리지 못해 결국 코드 작성을 하지 못하고 풀이를 찾아봤다.

---

## 🧑‍🔬 문제 분석

등산로 정보와 출입구, 산봉우리 정보가 주어질 때 문제에서 요구하는 [산봉우리 위치, 최소 intensity]를 찾아 반환하는 문제이다.

- 문제 조건이 제법 복잡해서 자세한건 문제를 참고하자.

문제에 주어지는 제약 조건은 다음과 같다.

### 제한사항

- 2 ≤ `n` ≤ 50,000
- `n` - 1 ≤ `paths`의 길이 ≤ 200,000
- `paths`의 원소는 `[i, j, w]` 형태입니다.
    - `i`번 지점과 `j`번 지점을 연결하는 등산로가 있다는 뜻입니다.
    - `w`는 두 지점 사이를 이동하는 데 걸리는 시간입니다.
    - 1 ≤ `i` < `j` ≤ `n`
    - 1 ≤ `w` ≤ 10,000,000
    - 서로 다른 두 지점을 직접 연결하는 등산로는 최대 1개입니다.
- 1 ≤ `gates`의 길이 ≤ `n`
    - 1 ≤ `gates`의 원소 ≤ `n`
    - `gates`의 원소는 해당 지점이 출입구임을 나타냅니다.
- 1 ≤ `summits`의 길이 ≤ `n`
    - 1 ≤ `summits`의 원소 ≤ `n`
    - `summits`의 원소는 해당 지점이 산봉우리임을 나타냅니다.
- 출입구이면서 동시에 산봉우리인 지점은 없습니다.
- `gates`와 `summits`에 등장하지 않은 지점은 모두 쉼터입니다.
- 임의의 두 지점 사이에 이동 가능한 경로가 항상 존재합니다.
- return 하는 배열은 `[산봉우리의 번호, intensity의 최솟값]` 순서여야 합니다.

---

## 🤔 풀이 고민

혼자서 문제를 어떻게 풀지 감을 잡지못해 결국 문제 풀이를 찾아봤다.

이 문제의 풀이 핵심은 다음과 같다.

- 무방향 가중치 그래프이기 때문에 다익스트라를 활용하는건 맞지만 기존 다익스트라와 다르게 가중치의 총합이 아닌 문제에서 요구하는 최소 `intensity`를 기준으로 다익스트라를 진행해야 한다.

문제에서 제시하는 목표는 하나의 출입구로부터 출발하여 산봉우리를 방문하고 다시 출발한 출입구로 돌아올때 intensity가 가장 적은 경로를 찾는 것이다. 여기서 intensity란 경로가 포함하는 모든 edge들 중 가중치(weight)가 가장 높은 값을 의미한다.

여기서 핵심은 반드시 출입구로 다시 되돌아온다는 생각을 안해도 된다는 것이다.

출입구가 A고 산봉우리가 B라면 문제에서 제시하는 경로는 다음과 같이 나와야한다.

`A` → ? → ? ….. → `B` → ? → ?  …… → `A`

여기서 A에서 B로 가는 경로의 intensity의 최소값은 B에서 A로 가는 intensity의 최소값과 같다. (결국 왔던 길을 되돌아가는것이 최소이기 때문)

그렇기 때문에 `A`-> ? → `B` 로 가는 경로들 중에서 intensity가 최소가 되는 값을 가지는 경로를 구하면 된다.

그럼 여기서 경로를 구하는 방식이 중요하다.

결국은 intensity를 최소가 되게끔 노드들을 방문하는것이 중요하다.

다익스트라를 조금 수정하면 이 문제를 해결 할 수 있다.

다익스트라 알고리즘이란 시작 노드로부터 모든 노드까지의 최단경로(최소비용 경로)를 구하는 알고리즘이다.

- 여기서 최단경로(최소비용 경로)를 계산하는 방법은 경로가 가지는 edge 가중치들의 총합을 기준으로 계산하면 된다.
- 이 문제에서는 현재까지의 경로의 가중치 총합 대신 가중치들 중 최대값(intensity)으로 계산하는 방법으로 수정하면 된다.

따라서 intensity를 최소가 되게끔 다익스트라 알고리즘을 수행한다면 문제에서 요구하는 경로가 나오게 된다.

### 결론

- 가중치들 중 최대갓을 가지는 조건으로 계산하는 다익스트라 알고리즘으로 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 최종 정답 코드 개선

찾아본 풀이를 바탕으로 구현한 코드는 다음과 같다.

```python
from collections import defaultdict
from heapq import heappush, heappop

def solution(n, paths, gates, summits):
    summits.sort()
    summit_set = set(summits)

    graph = defaultdict(list)
    for i, j, w in paths:
        graph[i].append((w, j))
        graph[j].append((w, i))

    hq = []
    visited = [float('inf')] * (n + 1)

		#✅ 모든 출입구를 우선순위큐에 삽입한다.
    for gate in gates:
        heappush(hq, (0, gate))
        visited[gate] = 0

		#✅ intensity를 기준으로 다익스트라를 진행한다.
    while hq:
        intensity, node = heappop(hq)
        if intensity > visited[node] or node in summit_set:
            continue

        for weight, next_node in graph[node]:
            next_intensity = max(weight, intensity)
            if next_intensity < visited[next_node]:
								#✅ 다익스트라 진행 중 각 노드에 도달하는 과정의 최대 intensity값을 저장한다.
                visited[next_node] = next_intensity
                heappush(hq, (next_intensity, next_node))

		#✅ 다익스트라 완료 후 산봉우리들을 순회하며 정답을 찾는다.
    min_intensity = [0, float('inf')]
    for summit in summits:
        if min_intensity[1] > visited[summit]:
            min_intensity[0] = summit
            min_intensity[1] = visited[summit]

    return min_intensity
```

---

## 🥰 배운점 & 느낀점

- 이번 문제도 풀이를 혼자 떠올리지 못해 아쉬웠다. 다익스트라의 조건을 이런식으로 활용할 수 있다는것을 배웠으니 복습할 필요가 있는거 같다.