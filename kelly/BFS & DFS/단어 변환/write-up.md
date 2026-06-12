## 👀 제한 시간 안에 어디까지 해냈는가?

`21분 43초`만에 혼자서 문제를 해결할 수 있었다.

P & J 트레이닝

- Python으로 1차 시도를 하여 `10분 26초`만에 문제를 해결했다.
- Java로 2차 시도를 하여 `14분 06초`만에 문제를 해결했다.

---

## 🧑‍🔬 문제 분석

시작 문자열, 목표 문자열 begin, target과 문자열 리스트 words가 주어졌을 때 시작 문자열을 기준으로 문제 조건을 만족하며 한자리씩 문자를 변경할 때 목표 문자열이 되기 까지의 최소 단계를 구하는 문제이다.

- 구체적은 문제 조건은 문제를 참고하자.

문제에 주어진 제약 조건은 다음과 같다.

### 제한사항

- 각 단어는 알파벳 소문자로만 이루어져 있습니다.
- 각 단어의 길이는 3 이상 10 이하이며 모든 단어의 길이는 같습니다.
- words에는 3개 이상 50개 이하의 단어가 있으며 중복되는 단어는 없습니다.
- begin과 target은 같지 않습니다.
- 변환할 수 없는 경우에는 0를 return 합니다.

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

문제를 보자마자 BFS 풀이가 떠올랐다. 우선 문제에 주어지는 입력의 크기가 매우 작은편이기 때문에 어느정도 브루트포스가 허용될거라는 생각이 들었다. 따라서 특정 문자열 상태를 기준으로 각 자리 문자를 변경하는 BFS 탐색을 진행하여 목표 문자열까지 도달하는 최단 경로를 찾아 반환하면 문제를 풀 수 있다는 생각이 들었다.

### 결론

- 내가 생각한 풀이로 코드를 작성하면 문제를 통과할 수 있다.

---

## 🏃 코드 작성 과정

### 내가 처음 작성한 코드

위에 기술한 내 풀이를 코드로 구현하면 다음과 같다.

```python
from collections import deque

def solution(begin, target, words):
    # 전역 데이터
    word_size = len(begin)
    board = [set() for _ in range(word_size)]
    for word in words:
        for i in range(word_size):
            board[i].add(word[i])
    
    # 메서드
    def bfs():
        dq = deque()
        visited = set()
        dq.append((begin, 0))
        visited.add(begin)
        
        while dq:
            cur_w, cur_s = dq.popleft()
            
            if cur_w == target:
                return cur_s
            
            for i in range(word_size):
                for next_c in board[i]:
                    next_w = cur_w[:i] + next_c + cur_w[i + 1:]
                    
                    if next_w in words and next_w not in visited:
                        dq.append((next_w, cur_s + 1))
                        visited.add(next_w)
        
        return 0
                    
    # 메인 로직
    if target not in words:
        return 0
    
    return bfs()
```

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01

    ```python
    from collections import deque, defaultdict
    
    def solution(begin, target, words):
        graph = defaultdict(set)
        for word in words:
            for i in range(len(word)):
                graph[i].add(word[i])
                
        # 메인 로직
        if target not in words:
            return 0
        
        dq = deque([(begin, 0)])
        visited = set([begin])
        
        while dq:
            cur_w, step = dq.popleft()
            
            if cur_w == target:
                return step
            
            for i in range(len(cur_w)):
                for next_ch in graph[i]:
                    cur_w_list = list(cur_w)
                    cur_w_list[i] = next_ch
                    next_w = ''.join(cur_w_list)
                    
                    if next_w not in words or next_w in visited:
                        continue
                    
                    dq.append((next_w, step + 1))
                    visited.add(next_w)
        
        return 0
    ```


### Java 풀이

- solution01

    ```java
    import java.util.*;
    
    class Solution {
        public int solution(String begin, String target, String[] words) {
            Set<String> wordSet = new HashSet<>();
            Map<Integer, Set<Character>> graph = new HashMap<>();
            
            for (String word : words) {
                wordSet.add(word);
                
                for (int i = 0; i < word.length(); i++) {
                    graph.computeIfAbsent(i, key -> new HashSet<>()).add(word.charAt(i));
                }
            }
            
            // 메인 로직
            if (!wordSet.contains(target)) {
                return 0;
            }
            
            Deque<Node> dq = new ArrayDeque<>();
            Set<String> visited = new HashSet<>();
            
            dq.offer(new Node(begin, 0));
            visited.add(begin);
            
            while (!dq.isEmpty()) {
                Node cur = dq.poll();
                
                if (cur.word.equals(target)) {
                    return cur.step;
                }
                
                for (int i = 0; i < cur.word.length(); i++) {
                    for (char ch : graph.get(i)) {
                        String left = cur.word.substring(0, i);
                        String right = cur.word.substring(i + 1, cur.word.length());
                        String newWord = left + String.valueOf(ch) + right;
                        
                        if (!wordSet.contains(newWord) || visited.contains(newWord)) {
                            continue;
                        }
                        
                        dq.offer(new Node(newWord, cur.step + 1));
                        visited.add(newWord);
                    }
                }
            }
            
            return 0;
        }
        
        class Node {
            String word;
            int step;
            
            public Node(String word, int step) {
                this.word = word;
                this.step = step;
            }
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- 그다지 어려운 문제가 아니었다.
- 다만 중간에 다음 경로를 큐에 넣을지 판단하는 로직에서 조건 하나를 누락해 디버깅 시간이 살짝 소요되었다. 이런 실수를 하지 않도록 조심할 필요가 있다.