# 문제링크
https://school.programmers.co.kr/learn/courses/30/lessons/150370
# 30분내 어디까지 풀었는가
- map 을써 약관 종류랑 유효기간 을 저장하고 privacies 배열을  statdata kind 로  split 을 나눠서  저장후  
  약관 종류 를 map.get(약관 종류) 불러 온다는거 까지는 했는데 날짜 계산하는거에서 헤매서 시간초과 
# 접근방법
- map 을써서 약관 종류 유효기간 저장
- split 을 써서 privacies 배열을 startdate , kind 로 나눠서 저장후 map.get(kind) 를 이용해 해당 약관 종류의 유호기간을 불러온다
- 이후 날짜를
```java
    // 유효기간 끝나는 날짜 계산
            int y = startDate / 10000;
            int m = (startDate / 100) % 100;
            int d = startDate % 100;
```
- 로 쪼개서 유효기간 만큼 날짜를 더한다
- 만료날짜 가 시작 날짜 보다 작으면 폐기해야됨
# 배운점 
- split 쓰는법
- 날짜 계산하는법 