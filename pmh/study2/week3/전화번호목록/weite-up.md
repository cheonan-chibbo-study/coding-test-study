# 문제링크
https://school.programmers.co.kr/learn/courses/30/lessons/42577?language=java
# 30분내 어디까지 풀었는가
30분내 풀기완료
# 왜 못풀었는가 - 
### 풀이가 안떠올랐는지 / 어떻게 풀지는 알겠는데 구현을 못하겠다.


# 접근방법
1. 어떤 번호가 다른 번호의 접두어인지 빨리 확인하고 접두어를 가졌다면 정렬시 서로 바로 옆에있을 확률이 높으니 번호 목록을 정렬한다
2. ```java
    phone_book[i+1].startsWith(phone_book[i])
    ```
   을 사용해 접두어 인지 확인하고 맞다면 false를 반환한다
3. 끝까지 확인하고 없을시 접두어가없는것이니 true 를 반환한다
# 시간/공간 복잡도
시간 복잡도:  O(N log N × M)  
문자열 배열 정렬은 : O(N log N)  
N = 전화번호 개수  
M = 전화번호 최대 길이  
따라서 정렬 비용은  
O(N log N × M)  

startsWith()는 앞에서부터 문자를 비교하므로  
O(M) 입니다.  
이를 N번 수행하므로  O(N × M)  
공간 복잡도:O(1)

# 배운점 
## startsWith()란?

문자열이 특정 문자열로 시작하는지 확인하는 메서드입니다.

```java
문자열.startsWith(접두어)
```

반환값

- 시작하면 → `true`
- 시작하지 않으면 → `false`

예시

```java
String str = "1195524421";

System.out.println(str.startsWith("119")); // true
System.out.println(str.startsWith("12"));  // false
```

---

### 전화번호 목록 문제에서

```java
phone_book[i + 1].startsWith(phone_book[i])
```

의 의미는

```java
phone_book[i]
```

가

```java
phone_book[i + 1]
```

의 접두어인지 확인하는 것입니다.

예시

```java
String a = "119";
String b = "1195524421";

b.startsWith(a); // true
```

실제로는

```text
1195524421
^^^
119
```

앞의 3글자가 `"119"`와 같으므로 `true`를 반환합니다.

---

### startsWith() 내부 동작 원리

대략 다음과 비슷하게 동작합니다.

```java
boolean startsWith(String prefix) {
    if (prefix.length() > this.length()) {
        return false;
    }

    for (int i = 0; i < prefix.length(); i++) {
        if (this.charAt(i) != prefix.charAt(i)) {
            return false;
        }
    }

    return true;
}
```

즉,

```java
"1195524421".startsWith("119")
```

는

```java
1 == 1
1 == 1
9 == 9
```

를 순서대로 검사하는 것과 같습니다.

---

# 시간복잡도

접두어 길이를 `K`라고 하면

```java
str.startsWith(prefix)
```

의 시간복잡도는

```text
O(K)
```

입니다.

왜냐하면 최악의 경우 접두어의 모든 문자를 비교해야 하기 때문입니다.

예시

```java
"1234567890".startsWith("12345")
```

→ 5번 비교

```java
"1234567890".startsWith("1234567890")
```

→ 10번 비교

---

### 전화번호 목록 문제에서의 의미

```java
Arrays.sort(phone_book);

for (int i = 0; i < phone_book.length - 1; i++) {
    if (phone_book[i + 1].startsWith(phone_book[i])) {
        return false;
    }
}
```

정렬 후에는 접두어 관계인 문자열들이 서로 붙어 있게 됩니다.

예시

입력:

```java
["119", "97674223", "1195524421"]
```

정렬 후:

```java
["119", "1195524421", "97674223"]
```

따라서

```java
"1195524421".startsWith("119")
```

만 확인하면 바로 접두어 여부를 알 수 있습니다.

이 때문에 전체 탐색 `O(N²)` 대신 **정렬 + 인접 비교**로 해결할 수 있습니다.

---

### 복잡도 정리

- 문자열 하나의 `startsWith()` : `O(K)`
- 인접 원소 비교 : `O(N × M)`
- 정렬 : `O(N log N × M)`

최종 시간복잡도

```text
O(N log N × M)
```

- `N` : 전화번호 개수
- `M` : 전화번호 최대 길이

## startsWith 쓰지않고 하기
```java
import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);

        for (int i = 0; i < phone_book.length - 1; i++) {
            String cur = phone_book[i];
            String next = phone_book[i + 1];

            if (next.length() >= cur.length() &&
                next.substring(0, cur.length()).equals(cur)) {
                return false;
            }
        }

        return true;
    }
}
```