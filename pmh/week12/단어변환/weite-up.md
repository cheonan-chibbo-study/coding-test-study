# 문제링크
https://school.programmers.co.kr/learn/courses/30/lessons/43163?utm_source=chatgpt.com
# 30분내 어디까지 풀었는가
30분내 풀기 시류ㅐ
# 왜 못풀었는가 - 
### 풀이가 안떠올랐는지 / 어떻게 풀지는 알겠는데 구현을 못하겠다.
풀이가 떠오르지 않음

# 접근방법
1. bfs , dfs 를 이용해 탐색한다
2. begin 부터 시작해 탐색하면서 현재 탐색하는 글자와 words 에나오는 글자모두를 비교한다
3. 비교하면서 글자 차이수를 구한다
4. 글자 차이수가 1 인애들 다음 탐색 단어로 지정한후 count+1 을 같이 한다
5. 현재 탐색 단어가 타텟단어와 같다면 count를 반환한다
    - 여기서 dfs 라면 계속 최솟값을 갱신한다
    - bfs 는 탐색후 타겟을 찾는 과정이 최솟값을 보장하므로 그냥 반환한다
6. 찾지 못할시 0 을 반환한다
# 배운점 
문자 비교까지는 떠올려서 문자 차리를 만드는 함수를 만드는데 toCharArray() 를 사용해 일일 해 
char 배열을 만들어서했는데 그냥 String.charAt() 를 사용하면 된다는걸 알게됬다

# DFS 풀이
```java
import java.util.*;

class Solution {
    int answer = Integer.MAX_VALUE;
    boolean[] visited;

    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];

        dfs(begin, target, words, 0);

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    public void dfs(String current, String target, String[] words, int count) {
        if (current.equals(target)) {
            answer = Math.min(answer, count);
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (!visited[i] && canChange(current, words[i])) {
                visited[i] = true;
                dfs(words[i], target, words, count + 1);
                visited[i] = false; // 백트래킹
            }
        }
    }

    public boolean canChange(String a, String b) {
        int diff = 0;

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
            }
        }

        return diff == 1;
    }
}
```