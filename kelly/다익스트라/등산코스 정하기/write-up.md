## 👀 제한 시간 안에 어디까지 해냈는가?

제한 시간 `30분` + 추가 시간 `30분`을 사용했지만 문제를 풀지 못했다. 이번에도 확실한 문제 해결책을 떠올리지 못해 결국 코드 작성을 하지 못하고 풀이를 찾아봤다.

P & J 트레이닝

- Python으로 1차 시도를 하였고 이틀동안 혼자서 문제를 풀지 못했다. 이 문제는 이후 추가 복습이 필요하다.
- Java로 2차 시도를 하여 `19분 07초`만에 문제를 해결했다.

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
    # 메인 로직
    summits.sort()
    summits_set = set(summits)
    
    graph = defaultdict(list)
    for i, j, w in paths:
        graph[i].append((w, j))
        graph[j].append((w, i))
    
    pq = []
    visited = [float('inf')] * (n + 1)
    for gate in gates:
        heappush(pq, (0, gate))
        visited[gate] = 0
    
    while pq:
        cur_inten, cur_v = heappop(pq)
        
        if cur_inten > visited[cur_v] or cur_v in summits_set:
            continue
        
        for weight, next_v in graph[cur_v]:
            next_inten = max(cur_inten, weight)
            
            if next_inten < visited[next_v]:
                visited[next_v] = next_inten
                heappush(pq, (next_inten, next_v))
    
    answer = [0, float('inf')]
    for s in summits:
        if visited[s] < answer[1]:
            answer = [s, visited[s]]
    
    return answer
```

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01

    ```python
    from heapq import heapify, heappush, heappop
    from collections import defaultdict
    
    def solution(n, paths, gates, summits):
        graph = defaultdict(list)
        for v1, v2, cost in paths:
            graph[v1].append((v2, cost))
            graph[v2].append((v1, cost))
        
        # 메인 로직
        pq = []
        intensities = [float('inf')] * (n + 1)
        
        for gate in gates:
            heappush(pq, (0, gate))
            intensities[gate] = 0
        
        while pq:
            cur_inten, cur_v = heappop(pq)
            
            if cur_inten > intensities[cur_v] or cur_v in summits:
                continue
            
            for next_v, cost in graph[cur_v]:
                next_inten = max(cur_inten, cost)
                
                if next_inten < intensities[next_v]:
                    heappush(pq, (next_inten, next_v))
                    intensities[next_v] = next_inten
        
        summits.sort()
        answer = [-1, float('inf')]
        for summit in summits:
            if intensities[summit] < answer[1]:
                answer = [summit, intensities[summit]]
        
        return answer
    ```


### Java 풀이

- solution01

    ```java
    import java.util.*;
    
    class Solution {
        public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
            Set<Integer> summitSet = new HashSet<>();
            for (int summit : summits) {
                summitSet.add(summit);
            }
            
            Map<Integer, List<int[]>> graph = new HashMap<>();
            for (int[] path : paths) {
                graph.computeIfAbsent(path[0], key -> new ArrayList<>()).add(new int[]{path[1], path[2]});
                graph.computeIfAbsent(path[1], key -> new ArrayList<>()).add(new int[]{path[0], path[2]});
            }
            
            // 메인 로직
            int[] intensities = new int[n + 1];
            for (int i = 0; i < n + 1; i++) {
                intensities[i] = Integer.MAX_VALUE;
            }
            
            PriorityQueue<Item> pq = new PriorityQueue<>((v1, v2) -> {
                return Integer.compare(v1.intensity, v2.intensity);
            });
            
            for (int gate : gates) {
                pq.offer(new Item(gate, 0));
                intensities[gate] = 0;
            }
            
            while (!pq.isEmpty()) {
                Item cur = pq.poll();
                
                if (
                    cur.intensity > intensities[cur.vertex] || 
                    summitSet.contains(cur.vertex)
                ) {
                    continue;
                }
                
                for (int[] next : graph.computeIfAbsent(cur.vertex, key -> new ArrayList<>())) {
                    int nextIntensity = Math.max(next[1], cur.intensity);
                    
                    if (nextIntensity < intensities[next[0]]) {
                        pq.offer(new Item(next[0], nextIntensity));
                        intensities[next[0]] = nextIntensity;
                    }
                }
            }
            
            List<Integer> summitList = new ArrayList<>(summitSet);
            Collections.sort(summitList);
            
            int[] answer = new int[]{-1, Integer.MAX_VALUE};
            for (int summit : summitList) {
                if (intensities[summit] < answer[1]) {
                    answer = new int[]{summit, intensities[summit]};
                }
            }
            
            return answer;
        }
        
        class Item {
            int vertex;
            int intensity;
            
            public Item(int vertex, int intensity) {
                this.vertex = vertex;
                this.intensity = intensity;
            }
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- 이번 문제도 풀이를 혼자 떠올리지 못해 아쉬웠다. 다익스트라의 조건을 이런식으로 활용할 수 있다는것을 배웠으니 복습할 필요가 있는거 같다.
- P & J 트레이닝에서 이틀이나 이 문제를 풀었는데 매번 혼자 풀어내지 못했다.
    - 세세한 아이디어 떠올리기, 아슬아슬하게 큰 데이터를 상대로 시간 복잡도를 최적화하는 테크닉을 요하는 문제이므로 좋은 연습이 될 수 있는 문제다. 이 문제는 꼭 복습하자.