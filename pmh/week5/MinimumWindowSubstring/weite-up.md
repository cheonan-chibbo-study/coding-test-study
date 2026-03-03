# 문제링크
https://leetcode.com/problems/minimum-window-substring/
# 30분내 어디까지 풀었는가
슬라이딩 윈도우를 써야겠다고 생각후
set 써서 할려고했는데 잘못된접근이여서 틀림

# 접근방법

# 배운점 
```java
 if(need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0) +1);

                if(window.get(c).equals(need.get(c))){
                    formed++;
                }


            }
// 원래는 window.get(c) ==need.get(c)) 이렇게썼는데 테스트케이스도 통과하고 제출후 테스트도거의 90% 통과했는데 문자열이 너무 길어지니깐 
// == 비교시 Integer -> int 로 바꿔주는데 숫자가 너무커지니 인트 범위를 넘어서서 제출을 실패함
```