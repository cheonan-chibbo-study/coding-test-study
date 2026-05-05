## 👀 제한 시간 안에 어디까지 해냈는가?

- Java로 처음 시도하여 `25분 35초`만에 문제를 해결했다.
- Python으로 2차 풀이를 시도하여 `5분 37초`만에 문제를 해결했다.

---

## 🧑‍🔬 문제 분석

음악 장르 리스트, 장르 리스트 요소별 재생 횟수 리스트가 주어질 때 문제 조건에 맞춰 요소의 번호를 담은 리스트를 반환해주는 문제이다.

- 자세한 문제 조건은 문제를 참고하자.

문제에 주어진 제약 조건은 다음과 같다.

### 제한사항

- genres[i]는 고유번호가 i인 노래의 장르입니다.
- plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
- genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
- 장르 종류는 100개 미만입니다.
- 장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
- 모든 장르는 재생된 횟수가 다릅니다.

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

우선 해시 맵을 활용해서 장르별 재생 횟수를 구한 후 (재생 횟수, 음악 장르 문자열) 데이터를 우선순위 큐에 넣어 순회한다.

1. 우선순위 큐에서 가장 우선순위가 높은 (재생 횟수, 음악 장르) 데이터를 꺼내 음악 장르에 속한 음악들을 찾아 새로운 우선순위 큐를 만들고 (음악별 재생 횟수, 음악 번호) 형식으로 데이터를 넣는다.
    1. 이 때 우선순위 큐의 규칙을 음악별 재생 횟수가 높음 → 음악 번호가 낮음으로 설정한다.
2. 순회를 2번 돌면서 장르마다 음악을 최대 2개씩 정답 배열에 넣는다.

모든 순회가 끝나면 정답 배열을 반환한다.

이 방식을 코드로 구현하면 문제를 해결할 수 있다.

### 결론

- 내가생각한 방법으로 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 내가 처음 작성한 코드

혼자서 Java & Python으로 쉽게 코드를 작성했다. 최종 정답 코드를 참고하자.

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01

    ```python
    from heapq import heappush, heappop
    
    def solution(genres, plays):
        category_map = {}
        for i in range(len(plays)):
            category_map[genres[i]] = category_map.get(genres[i], 0) + plays[i]
        
        category_pq = []
        for k, v in category_map.items():
            heappush(category_pq, (-v, k))
        
        answer = []
        while category_pq:
            cur_category = heappop(category_pq)[1]
            music_pq = []
            for idx in range(len(plays)):
                if genres[idx] == cur_category:
                    heappush(music_pq, (-plays[idx], idx))
            
            for i in range(2):
                if not music_pq:
                    break
                
                answer.append(heappop(music_pq)[1])
        
        return answer
    ```


### Java 풀이

- solution01

    ```java
    // 1. 장르의 재생 횟수, -> 2. 장르 내에서 많이 재생된 노래 -> 3. 고유 번호가 낮은 노래
    
    import java.util.*;
    
    class Solution {
        public int[] solution(String[] genres, int[] plays) {
            Map<String, Integer> playCount = new HashMap<>();
            for (int i = 0; i < genres.length; i++) {
                playCount.put(genres[i], playCount.getOrDefault(genres[i], 0) + plays[i]);
            }
            
            PriorityQueue<Category> categoryPq = new PriorityQueue<>((v1, v2) -> Integer.compare(v2.count, v1.count));
            for (String k : playCount.keySet()) {
                categoryPq.offer(new Category(playCount.get(k), k));
            }
            
            List<Integer> answer = new ArrayList<>();
            while (!categoryPq.isEmpty()) {
                Category curCategory = categoryPq.poll();
                
                PriorityQueue<Music> musicPq = new PriorityQueue<>((v1, v2) -> {
                    if (v1.count != v2.count) return Integer.compare(v2.count, v1.count);
                    return Integer.compare(v1.idx, v2.idx);
                });
                
                for (int idx = 0; idx < plays.length; idx++) {
                    if (genres[idx].equals(curCategory.name)) {
                        musicPq.offer(new Music(plays[idx], idx));
                    }
                }
                
                for (int i = 0; i < 2; i++) {
                    if (musicPq.isEmpty()) {
                        break;
                    }
                    
                    Music curMusic = musicPq.poll();
                    answer.add(curMusic.idx);
                }
            }
            
            int[] arrAnswer = new int[answer.size()];
            for (int i = 0; i < answer.size(); i++) {
                arrAnswer[i] = answer.get(i);
            }
            
            return arrAnswer;
        }
    }
    
    class Category {
        
        int count;
        String name;
        
        public Category(int count, String name) {
            this.count = count;
            this.name = name;
        }
    }
    
    class Music {
        
        int count;
        int idx;
        
        public Music(int count, int idx) {
            this.count = count;
            this.idx = idx;
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- 혼자서 시간안에 문제를 풀 수 있을지 나도 몰랐다. 이제 여러 자료 구조를 적절하게 사용하는 감각이 이전보다 늘었다는게 체감되어서 뿌듯하다.
- Java에서 우선순위 큐의 규칙을 직접 설정하는걸 연습할 수 있는 좋은 문제 사례인거 같다.