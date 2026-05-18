# 문제링크
https://school.programmers.co.kr/learn/courses/30/lessons/178870?utm_source=chatgpt.com
# 30분내 어디까지 풀었는가
10분 에 풀기 완료
# 왜 못풀었는가 - 
### 풀이가 안떠올랐는지 / 어떻게 풀지는 알겠는데 구현을 못하겠다.


# 접근방법
1. 투포인터 방식을 사용한다
2. right 를 늘려가면서 sum 에 더한다
3. sum > k 이가 될시 sum k 보다 작아질때까지 sum 을 legt 를 늘려가면서 sequence 배열 0 부터 뺀다
4. sum == k 일시 right - left 를 통해 길이를 구하고 최소길이보다 길이가 작을시 bestR, bestL 를 갱신한다
# 배운점 