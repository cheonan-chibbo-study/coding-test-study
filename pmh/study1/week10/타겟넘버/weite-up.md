# 문제링크
https://school.programmers.co.kr/learn/courses/30/lessons/43165
# 30분내 어디까지 풀었는가
30분 내 풀기 실패
# 왜 못풀었는가 - 
### 풀이가 안떠올랐는지 / 어떻게 풀지는 알겠는데 구현을 못하겠다.
dfs 재귀방식으로 풀어야겠다고 생각했다.  
그러나 +,- 분기방식을 떠올리지 못했다.

# 접근방법
numbers 를 dfs 재귀방식으로 탐색한다.
탐색할때마다 numbers[i] 값을 더한dfs 뺀dfs 로 분기로 나눠 재귀를 2개 호출한다
index가 5일때 sum 이 target과 같은경우 count++ 를한다.
# 배운점 
dfs 재귀방식에 분기를써서 여러 재귀를 쓰는방법에대해 다시한번 기억하게됬다.
