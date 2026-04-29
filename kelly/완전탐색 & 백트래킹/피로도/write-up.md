## 👀 제한 시간 안에 어디까지 해냈는가?

- `10분 17초`만에 `Java`를 사용해서 혼자서 문제를 해결했다.
- `3분 38초`만에 `Python`을 사용해서 혼자서 문제를 해결했다.

---

## 🧑‍🔬 문제 분석

현재 피로도 k와 던전 정보를 담은 리스트 dungeons가 주어질 때 문제 조건에 맞춰 던전을 순회할 경우 가장 많이 순회할 수 있는 던전 개수를 반환하는 문제이다.

문제에 주어지는 제약 조건은 다음과 같다.

### 제한사항

- k는 1 이상 5,000 이하인 자연수입니다.
- dungeons의 세로(행) 길이(즉, 던전의 개수)는 1 이상 8 이하입니다.
    - dungeons의 가로(열) 길이는 2 입니다.
    - dungeons의 각 행은 각 던전의 ["최소 필요 피로도", "소모 피로도"] 입니다.
    - "최소 필요 피로도"는 항상 "소모 피로도"보다 크거나 같습니다.
    - "최소 필요 피로도"와 "소모 피로도"는 1 이상 1,000 이하인 자연수입니다.
    - 서로 다른 던전의 ["최소 필요 피로도", "소모 피로도"]가 서로 같을 수 있습니다.

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

주어진 입력의 크기가 작기 때문에 완전 탐색을 활용해 문제를 해결할 수 있겠다는 생각이 들었다. 재귀를 활용해 던전을 탐색할 수 있는 모든 경우의 수를 탐색하면서 중간에 방문 체크와 피로도 체크를 통해 불필요한 탐색 경우를 줄이면 충분히 문제를 해결할 수 있다.

### 결론

- 내가 생각한 풀이를 코드로 구현하면 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 내가 처음 작성한 코드

혼자서 무리없이 코드를 작성했다. 최종 정답 코드를 참고하자.

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01

    ```python
    def solution(k, dungeons):
        d_size = len(dungeons)
        answer = 0
        
        # 메서드
        def search(visited, count):
            nonlocal answer, k
            answer = max(answer, count)
            
            for i in range(d_size):
                if visited[i] or k < dungeons[i][0]:
                    continue
                
                visited[i] = True
                k -= dungeons[i][1]
                search(visited, count + 1)
                
                visited[i] = False
                k += dungeons[i][1]
        
        # 메인 로직
        visited = [False] * d_size
        search(visited, 0)
        
        return answer
    ```


### Java 풀이

- solution01

    ```java
    class Solution {
        
        int d_size;
        int k;
        int[][] dungeons;
        
        int answer = 0;
        
        public int solution(int k, int[][] dungeons) {
            this.k = k;
            this.dungeons = dungeons;
            this.d_size = dungeons.length;
            
            // 메인 로직
            boolean[] visited = new boolean[d_size];
            search(visited, 0);
            
            return answer;
        }
        
        private void search(boolean[] visited, int count) {
            answer = Math.max(answer, count);
            
            for (int i = 0; i < d_size; i++) {
                if (visited[i] || k < dungeons[i][0]) {
                    continue;
                }
                
                visited[i] = true;
                k -= dungeons[i][1];
                search(visited, count + 1);
                
                visited[i] = false;
                k += dungeons[i][1];
            }
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- 크게 어려운 문제는 아니었다. 시간 복잡도 검수와 재귀 이해도가 있다면 쉽게 코드를 작성할 수 있다.