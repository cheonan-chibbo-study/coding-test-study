# 문제링크
https://leetcode.com/problems/coin-change/
# 30분내 어디까지 풀었는가
재귀함수를 짜다가 시간초과
# 접근방법
- 재귀로 amount - coin[] 배열을 돌려 각 배열을 더함으로 써 amount 가 되는 수의 합을 구한다
- 그중 최솟값을 best 로하고 memo[i] 에 값을 넣는다

# 배운점 
생각보다 재귀를 어떻게 짜야할지 생각하는것도 시간을 많이 잡아먹었다
