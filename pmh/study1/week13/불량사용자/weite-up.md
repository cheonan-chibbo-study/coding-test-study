# 문제링크
https://school.programmers.co.kr/learn/courses/30/lessons/64064?utm_source=chatgpt.com
# 30분내 어디까지 풀었는가
50분 걸려서 풀기완료
# 왜 못풀었는가 - 
### 풀이가 안떠올랐는지 / 어떻게 풀지는 알겠는데 구현을 못하겠다.

# 접근방법
1. 모든 조합을 봐야하므로 dfs 탐색을 써야겠다고 생각함
2. dfs 를 통해 user_id 를 한개씩 탐사할때 user_id 가 banned_id 매칭 되는지 확인하기위한 diff() 함수를만든다 
3. diff()
   - 길이 다를시 false 반환 같을때만 차이 확인
   - 일일히 확인하면서 서로 다른 문자일시 count ++
   - 만약 banned_id 가 * 이라면 start++
   - 모두 탐색후 count == start 라면 매핑된다고 판단 true 반환
4. diff 함수를 통해 true 반환되고 아직 탐색하지않은 user_id 일시 매칭되는 아이디라고 판단하고 cur 에 추가한다
5. cur.size == banned_id.lengrh() 라면 이 cur 리스트는 탐색이 끝났다고 판단한다
6. 똑같은 경우의 수지만 문자열 순서가 달라 다른 취급하는것을 막기위해 cur 을 copy 리스트로 복사후 정렬한다
7. 정렬한 copy 리스트를 set 자료구조인 result 에 추가한다
8. result.size() 를 반환한다.
# 비트마스킹으로 푼경우
선택된 유저 집합만 같으면 같은 경우로 봐야하기 떄문에 비트마스킹으로 풀기가 가능하다  
- 유저를 하나 선택하면 해당 인덱스 비트를 1로 켠다
- banned_id를 끝까지 다 매칭하면
  - 지금까지 선택한 유저 집합을 mask 하나로 표헌가능
  - 그 mask를 Set<Integer> 에 넣을시 중복 제거
- String.join(",", copy) 로 중복제 제거하던과정 삭제 가능

## 코드
```java
import java.util.*;

class Solution {
    Set<Integer> result = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        dfs(0, 0, user_id, banned_id);
        return result.size();
    }

    void dfs(int depth, int mask, String[] user_id, String[] banned_id) {
        // banned_id를 전부 매칭한 경우
        if (depth == banned_id.length) {
            result.add(mask);
            return;
        }

        for (int i = 0; i < user_id.length; i++) {
            // i번째 유저를 이미 사용한 경우 skip
            if ((mask & (1 << i)) != 0) continue;

            // 현재 banned_id[depth]와 user_id[i]가 매칭 가능하면 선택
            if (match(user_id[i], banned_id[depth])) {
                dfs(depth + 1, mask | (1 << i), user_id, banned_id);
            }
        }
    }

    boolean match(String user, String banned) {
        if (user.length() != banned.length()) return false;

        for (int i = 0; i < user.length(); i++) {
            if (banned.charAt(i) == '*') continue;
            if (user.charAt(i) != banned.charAt(i)) return false;
        }

        return true;
    }
}
```
## 설명
```java
if ((mask & (1 << i)) != 0) continue;

if (match(user_id[i], banned_id[depth])) {
    dfs(depth + 1, mask | (1 << i), user_id, banned_id);
}
```
- mask = 지금까지 사용한 유저들 표시판
- 1 << i = i번째 유저만 가리키는 스위치
- mask & (1 << i) = i번째 유저를 이미 썼는지 검사
- mask | (1 << i) = i번째 유저를 사용 처리
### 1 << i
1 << i 는
숫자 1을 왼쪽으로 i칸 밀기
### 예시
```java
1 << 0 = 00001
1 << 1 = 00010
1 << 2 = 00100
1 << 3 = 01000
1 << 4 = 10000

```
즉,  
1 << 0 : 0번 유저  
1 << 1 : 1번 유저  
1 << 2 : 2번 유저  
### mask
예를 들어 유저가 5명이라 가정 

0번 유저 사용  
2번 유저 사용  
했다면 mask 는 이렇게 된다  
```java
00101
```
비트 위치를 오른쪽부터 보면:

0번째 비트 = 1 → 0번 유저 사용됨  
1번째 비트 = 0 → 1번 유저 아직 안씀  
2번째 비트 = 1 → 2번 유저 사용됨  
3번째 비트 = 0  
4번째 비트 = 0  

즉 mask = 5 다,  00101(2) = 5(10) 라서.
###  mask & (1 << i) 
```java
(mask & (1 << i)) != 0
```
i번째 비트가 이미 켜져있는지 확인하는 코드  
### 예시  
```java
mask = 00101
```
라고 가정 , 즉 0,2 번 유저 사용중  
i = 2 검사  
1 << 2 = 00100

그러면
```java
mask      = 00101
1 << 2    = 00100
---------------- &
00100

```
결과가 0이 아님  
즉,  
2번 자리 비트가 켜져 있다  
2번 유저는 이미 사용했다 라고 할수있다  

i = 1 검사  
1 << 1 = 00010

그러면
```java
mask      = 00101
1 << 1    = 00010
---------------- &
00000

```
결과가 0
즉,  
1번 자리 비트가 꺼져 있다  
1번 유저는 아직 안 썼다  -> 1번 유저는 선택가능
### mask | (1 << i)
```java
mask | (1 << i)
```
i번째 비트를 켠 새로운 mask 를 만드는 코드
### 예시

현재
```java
mask = 00101
```
이고
이번에 1번 유저를 선택한다고 해보자.

1 << 1 = 00010

그러면
```java
mask      = 00101
1 << 1    = 00010
---------------- |
00111
```
즉 결과는:  
00111  
이제  
0번 사용  
1번 사용  
2번 사용  
상태가 됨 