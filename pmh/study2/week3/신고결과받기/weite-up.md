# 문제링크
https://school.programmers.co.kr/learn/courses/30/lessons/92334
# 30분내 어디까지 풀었는가
30분내 풀기실패
# 왜 못풀었는가 - 
### 풀이가 안떠올랐는지 / 어떻게 풀지는 알겠는데 구현을 못하겠다.
Map<String, Set<String>> 은 쓰는방법이 떠오르지 않아서 풀지 못했다

# 접근방법
1. Map<String,Integer> idx 을 통해서 각 id_list 에 있는 유저들의 인덱스를 구한다.
2. Map<String, Set<String> 를 통해 신고 받은 사람 -> 신고 한 사람 으로 저장한다.
   - Set 으로 저장해 한사람이 같은사람을 여러번 신고해도 횟수 1인 채로 유지하게한다.
   - 신고 받은 사람 -> 신고 한 사람 으로 저장해 신고 한 사람 수를 세면 신고 받은 횟수가 되게한다.
3. 신고 받은 횟수(신고 한사람)가 k 이상이 사람들은 찾아서 그 유저를 신고한 사람들의 메일 수를 1씩 증가시킨다.
4. 최종적으로 answer를 반환한다
# 시간/공간 복잡도
시간 복잡도 : O(n + m)  
공간 복잡도 : O(n + m)

# 개선점
```java
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        List<String> list = Arrays.stream(report).distinct().collect(Collectors.toList());
        HashMap<String, Integer> count = new HashMap<>();
        for (String s : list) {
            String target = s.split(" ")[1];
            count.put(target, count.getOrDefault(target, 0) + 1);
        }

        return Arrays.stream(id_list).map(_user -> {
            final String user = _user;
            List<String> reportList = list.stream().filter(s -> s.startsWith(user + " ")).collect(Collectors.toList());
            return reportList.stream().filter(s -> count.getOrDefault(s.split(" ")[1], 0) >= k).count();
        }).mapToInt(Long::intValue).toArray();
    }
}
```
이 풀이는 다음 순서로 동작한다.

```text
1. 중복 신고 제거
2. 각 유저의 신고당한 횟수 계산
3. 각 유저가 신고한 사람 목록 조회
4. 신고한 사람 중 정지된 사람 수 계산
5. 메일 수 반환
```

---

## 예제

```java
id_list = ["muzi", "frodo", "apeach", "neo"]

report = {
    "muzi frodo",
    "apeach frodo",
    "frodo neo",
    "muzi neo"
}

k = 2
```

---

## 1. 중복 신고 제거

```java
List<String> list =
    Arrays.stream(report)
          .distinct()
          .collect(Collectors.toList());
```

### 결과

```java
[
    "muzi frodo",
    "apeach frodo",
    "frodo neo",
    "muzi neo"
]
```

동일한 신고가 여러 번 들어와도 한 번만 남긴다.

예시

```java
[
    "muzi frodo",
    "muzi frodo",
    "apeach frodo"
]
```

↓

```java
[
    "muzi frodo",
    "apeach frodo"
]
```

---

## 2. 신고당한 횟수 계산

```java
HashMap<String, Integer> count = new HashMap<>();

for (String s : list) {
    String target = s.split(" ")[1];
    count.put(target, count.getOrDefault(target, 0) + 1);
}
```

### 계산 과정

```java
"muzi frodo"
```

↓

```java
frodo = 1
```

---

```java
"apeach frodo"
```

↓

```java
frodo = 2
```

---

```java
"frodo neo"
```

↓

```java
neo = 1
```

---

```java
"muzi neo"
```

↓

```java
neo = 2
```

---

### 최종 결과

```java
{
    frodo = 2,
    neo = 2
}
```

의미

```text
frodo는 2번 신고당함
neo는 2번 신고당함
```

---

## 3. 각 유저별로 처리

```java
Arrays.stream(id_list)
```

각 유저를 하나씩 확인한다.

---

## 4. 현재 유저가 신고한 목록 찾기

예를 들어

```java
user = "muzi"
```

일 때

```java
List<String> reportList =
    list.stream()
        .filter(s -> s.startsWith(user + " "))
        .collect(Collectors.toList());
```

결과

```java
[
    "muzi frodo",
    "muzi neo"
]
```

즉,

```text
현재 유저가 신고한 목록
```

을 구하는 과정이다.

---

## 5. 정지된 사람 수 계산

```java
reportList.stream()
          .filter(
              s -> count.getOrDefault(
                      s.split(" ")[1], 0
                  ) >= k
          )
          .count();
```

### muzi의 경우

신고 목록

```java
[
    "muzi frodo",
    "muzi neo"
]
```

---

### frodo 확인

```java
count.get("frodo")
```

↓

```java
2
```

---

```java
2 >= k(2)
```

성공

---

### neo 확인

```java
count.get("neo")
```

↓

```java
2
```

---

```java
2 >= k(2)
```

성공

---

따라서

```java
count()
```

결과

```java
2
```

---

의미

```text
muzi는 정지된 유저를 2명 신고했다.
```

메일 2개 수신

---

## 각 유저별 결과

### muzi

```java
[
    "muzi frodo",
    "muzi neo"
]
```

↓

```text
메일 2개
```

---

### frodo

```java
[
    "frodo neo"
]
```

↓

```text
메일 1개
```

---

### apeach

```java
[
    "apeach frodo"
]
```

↓

```text
메일 1개
```

---

### neo

```java
[]
```

↓

```text
메일 0개
```

---

## 최종 결과

```java
[2, 1, 1, 0]
```

---

## 한 줄 요약

```text
중복 신고를 제거한 후,

1. 각 사람이 몇 번 신고당했는지 계산하고,
2. 각 유저가 신고한 사람들 중
3. k번 이상 신고당해 정지된 사람이 몇 명인지 세어
4. 그 개수를 메일 수로 반환하는 풀이
```
# 배운점 
```java
map.putIfAbsent(key, new HashSet<>());  // 없으면 생성
map.get(key).add(value);                // 값 추가

map.computeIfAbsent(key, k -> new HashSet<>()).add(value);
// 없으면 생성 + 바로 add까지 한 줄로!

  for(Map.Entry<String, Set<String>> entry : tarToRep.entrySet()){
Set<String> reporters = entry.getValue();
           if(reporters.size() >= k){
        for(String reporter : reporters){
answer[idx.get(reporter)]++;
        }


        }
        }
```
Map 안에 Set을 쓰는방법과 Map 을 entry 로 꺼내는법을 다시한번 상기하게 되었다.  
또한 이런 문법들을 안쓰면 계속 까먹으니 정리해서 주기적으로 봐야겠다고 생각했다.