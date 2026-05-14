# 문제링크

https://leetcode.com/problems/shortest-path-in-binary-matrix/description/

# 접근방법
1. 처음 에는 dfs 방식으로 8 방향을 다 탐색해서 n-1,n-1 에 도달시 해당 경로를 리턴하고 최소값을 비교하는 식으로 풀려고 하였다
2. 그러나 수가 커지면서 int 의 최대값이 넘어가는 상황이 발생함
3. 결국 bfs 방식으로 풀게됨

# 배운점 
- 최단 거리 관련 이면 BFS 를 써야 겠다는걸 알게됨
