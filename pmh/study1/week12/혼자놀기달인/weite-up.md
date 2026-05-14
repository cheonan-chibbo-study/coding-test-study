# 문제링크
https://school.programmers.co.kr/learn/courses/30/lessons/131130
# 30분내 어디까지 풀었는가
풀지못함
# 왜 못풀었는가 - 
### 풀이가 안떠올랐는지 / 어떻게 풀지는 알겠는데 구현을 못하겠다.
풀이가 중간에막힘 그룹을 어케 해결해야할지에서 막힘

# 접근방법
1. visited 함수를 사용해서 반목문을 돌리면서 방문하지않은 상자를 확인한다
2. 반복문안에서 반복문 하나를 더돌려 규칙에 맞게 상자를 열고 count 를 센다
3. 상자 방문이 끝나면 리스트에 카운트를 추가해 그룹을 만든다
4. 그룹 1개라면 0 을 반환하고
5. 그룹이 2개 이상이라면 리스트를 내림차순 정렬하고 가장 큰 2개를 곱해서 서반환한다
# 배운점 
1. 람다식
   Collections.sort(groups, (a, b) -> b - a);

그런데 이 방식은 값 범위가 크면 b - a 에서 오버플로우 위험이 있어서, 보통은 아래가 더 안전합니다.

Collections.sort(groups, (a, b) -> Integer.compare(b, a));
2. List의 sort 사용
   groups.sort((a, b) -> Integer.compare(b, a));