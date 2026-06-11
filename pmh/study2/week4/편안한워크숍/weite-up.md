# 문제링크
https://www.codetree.ai/ko/frequent-problems/hsat/problems/easy-workshop/submissions?page=1&page_size=20
# 30분내 어디까지 풀었는가
30분넘어서 풀었지만 테케는 통과했지만 제출에서 시간초과로 실패
# 왜 못풀었는가 - 
### 풀이가 안떠올랐는지 / 어떻게 풀지는 알겠는데 구현을 못하겠다.


# 접근방법
1. 등산할수있는 등산로의 조합의수를 모두 찾아 그중 등산로 차이를 계산하고 그중 최솟값을 찾으면 된다고생각했다.
2. 그래서 dfs() 로 방식으로 풀려고했다.
3. 테스트케이스는 통과했지만 결과제출에서 시간초과가 나타났다.
```java
import java.util.Scanner;

public class Main {

    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int[][] grid = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = true;
                dfs(n, k, grid, i, j, 1, 0);
                visited[i][j] = false;
            }
        }
        if(min == Integer.MAX_VALUE){
            System.out.println(-1);    
            return;
        }
        System.out.println(min);
    }

    public static void dfs(int n, int k, int[][] grid,
                           int r, int c, int curLen, int maxDiff) {

        if (curLen >= k ) {
            min = Math.min(min, maxDiff);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nr = r + dx[i];
            int nc = c + dy[i];

            if (nr >= 0 && nr < n &&
                nc >= 0 && nc < n &&
                !visited[nr][nc] &&
                grid[nr][nc] > grid[r][c]
                && grid[nr][nc] - grid[r][c] <= maxDiff) {

                int nextMaxDiff =
                        Math.max(maxDiff, grid[nr][nc] - grid[r][c]);

                visited[nr][nc] = true;
                dfs(n, k, grid, nr, nc, curLen + 1, nextMaxDiff);
                visited[nr][nc] = false;
            }
        }
    }
}
```
4. 이렇게 풀시 n x n 을 전부 탐색하는 n^2 과 4방향을 k 방향 만큼 탐색하는 dfs의 4x4x4x... = 4^k 가 되버린다
5. 그래서 결과적으로 O(n^2 * 4^k) 가디고 n 과 k 의 최대값은 100 으로 100^2 * 4 ^ 100 = 약 1.6 × 10^64 이 된다
6. 시간제한이 3000ms 로 3초 10^8 연산이 1초로 친다고할때 시간이 훨씬넘어 시간초과 가 발생한다.
7. 따라서 모든 등산로를 직접 탐색하는 방식이 아니라 문제의 조건을 다른 관점에서 해석해야 했다.

8. 문제에서 구해야 하는 값은 **"등산로에서의 최대 높이 차의 최솟값"** 이다. 이를 정답 후보 `D`라고 가정해보자.

9. 만약 최대 높이 차를 `D` 이하로 제한했을 때 길이 `K` 이상의 등산로가 존재한다면, `D`보다 큰 값에서도 반드시 등산로가 존재한다.

10. 반대로 `D`에서 불가능하다면 `D`보다 작은 값에서도 모두 불가능하다.

11. 즉,

    ```text
    불가능 불가능 불가능 가능 가능 가능 ...
    ```

    과 같은 단조성이 성립하므로 정답을 이분 탐색할 수 있다.

12. 이분 탐색 과정에서 특정 `D`가 주어졌을 때 길이 `K` 이상의 등산로가 존재하는지 확인해야 한다.

13. 이때

    ```text
    다음 칸의 높이 > 현재 칸의 높이
    &&
    높이 차 <= D
    ```

    인 경우만 이동하도록 그래프를 구성한다.

14. 높이가 증가하는 방향으로만 이동하므로 사이클이 존재하지 않는 DAG(Directed Acyclic Graph)가 된다.

15. 따라서 DFS + 메모이제이션을 이용하여 각 칸에서 시작할 수 있는 최대 등산로 길이를 계산할 수 있다.

16. 메모이제이션 배열 `memo[r][c]`는

    ```text
    (r,c)에서 시작했을 때 만들 수 있는 최대 등산로 길이
    ```

    를 의미한다.

17. 각 칸은 한 번만 계산되므로 `check(D)`의 시간복잡도는 `O(N²)`가 된다.

18. 최종적으로 정답은

    ```text
    이분 탐색 O(log H)
    ×
    DFS + 메모이제이션 O(N²)
    ```

    이므로

    ```text
    O(N² log H)
    ```

    에 해결할 수 있다.

# 시간복잡도

```text
check(D) : O(N²)

이분 탐색 : O(log H)

전체 : O(N² log H)
```

- `N` : 격자의 한 변의 길이
- `H` : 최대 높이 - 최소 높이

# 공간복잡도

```text
O(N²)
```

- `grid[N][N]`
- `memo[N][N]`

를 저장하기 위해 `O(N²)`의 공간이 필요하다.
# 시간/공간 복잡도
시간 복잡도:  
공간 복잡도:

# 배운점 