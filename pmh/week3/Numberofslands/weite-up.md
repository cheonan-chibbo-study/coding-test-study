# 문제링크

https://leetcode.com/problems/number-of-islands/

# 접근방법
1. grid 를 탐색하면서 처음 1을 찾았을때 섬 카운트를 +1 하고 해당 인덱스에서 상하좌우로 dfs 탐색을 하면될것같다고 생각했다.
2. gird 가 0 이거나 행, 열 범위가 넘어가면 return
3. gird 가 1 이면 0 으로 바꿔주고 다시 상하좌우 탐색한다
4. 만약 탐색하다 모두 0 을만날시 gird 를 다시 탐색하면서 다음 섬을 찾는다

# 배운점 