# Java 코테용 람다식 / 스트림 총정리

프로그래머스 기준으로는 대부분 아래 import 하나면 충분하다.

```java
import java.util.*;
```

스트림 관련 클래스를 직접 타입으로 쓸 때는 아래 import가 필요할 수 있다.

```java
import java.util.stream.*;
```

---

## 1. 람다식 기본 형태

### 기존 익명 클래스 방식

```java
Collections.sort(list, new Comparator<Integer>() {
    public int compare(Integer a, Integer b) {
        return a - b;
    }
});
```

### 람다식 방식

```java
Collections.sort(list, (a, b) -> a - b);
```

의미는 다음과 같다.

```java
(a, b) -> a - b
```

- 음수 반환: `a`가 앞
- 양수 반환: `b`가 앞
- 0 반환: 순서 유지

---

## 2. 배열 정렬 람다식

### 2차원 배열 첫 번째 값 기준 오름차순

```java
int[][] arr = {
    {3, 4},
    {1, 2},
    {2, 5}
};

Arrays.sort(arr, (a, b) -> a[0] - b[0]);
```

결과:

```text
[1, 2]
[2, 5]
[3, 4]
```

---

### 첫 번째 값 기준 내림차순

```java
Arrays.sort(arr, (a, b) -> b[0] - a[0]);
```

---

### 첫 번째 값 오름차순, 같으면 두 번째 값 오름차순

```java
Arrays.sort(arr, (a, b) -> {
    if (a[0] == b[0]) {
        return a[1] - b[1];
    }
    return a[0] - b[0];
});
```

예시:

```java
int[][] arr = {
    {1, 5},
    {1, 2},
    {2, 3}
};
```

정렬 결과:

```text
[1, 2]
[1, 5]
[2, 3]
```

---

### 첫 번째 값 오름차순, 같으면 두 번째 값 내림차순

```java
Arrays.sort(arr, (a, b) -> {
    if (a[0] == b[0]) {
        return b[1] - a[1];
    }
    return a[0] - b[0];
});
```

자주 나오는 패턴:

```text
키 오름차순, 몸무게 내림차순
시작 시간 오름차순, 종료 시간 내림차순
```

---

## 3. 오버플로우 방지 정렬

아래처럼 쓰면 간단하다.

```java
Arrays.sort(arr, (a, b) -> a[0] - b[0]);
```

하지만 값이 아주 크면 오버플로우가 날 수 있다.

안전한 방식:

```java
Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));
```

내림차순:

```java
Arrays.sort(arr, (a, b) -> Integer.compare(b[0], a[0]));
```

코테에서는 `a - b`도 많이 쓰지만, 안전하게 하려면 `Integer.compare()`를 쓰는 것이 좋다.

---

## 4. List 정렬

### 오름차순

```java
List<Integer> list = new ArrayList<>();
list.add(3);
list.add(1);
list.add(2);

Collections.sort(list);
```

또는:

```java
list.sort((a, b) -> a - b);
```

---

### 내림차순

```java
list.sort((a, b) -> b - a);
```

또는:

```java
Collections.sort(list, Collections.reverseOrder());
```

---

### 문자열 오름차순

```java
List<String> list = new ArrayList<>();
list.add("banana");
list.add("apple");
list.add("car");

Collections.sort(list);
```

결과:

```text
apple
banana
car
```

---

### 문자열 길이순 정렬

```java
list.sort((a, b) -> a.length() - b.length());
```

---

### 문자열 길이순, 길이가 같으면 사전순

```java
list.sort((a, b) -> {
    if (a.length() == b.length()) {
        return a.compareTo(b);
    }
    return a.length() - b.length();
});
```

---

## 5. PriorityQueue 람다식

### 기본은 최소힙

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
```

작은 값부터 나온다.

```java
pq.add(3);
pq.add(1);
pq.add(2);

System.out.println(pq.poll()); // 1
```

---

### 최대힙

```java
PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
```

또는 안전하게:

```java
PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
```

---

### int[]를 넣는 PriorityQueue

예를 들어 `{노드, 거리}` 구조라면:

```java
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
```

거리 기준 오름차순이다.

```java
pq.add(new int[]{1, 10});
pq.add(new int[]{2, 3});
pq.add(new int[]{3, 7});

int[] cur = pq.poll(); // {2, 3}
```

---

### 거리 기준 오름차순, 같으면 노드 번호 오름차순

```java
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
    if (a[1] == b[1]) {
        return a[0] - b[0];
    }
    return a[1] - b[1];
});
```

---

## 6. List → 배열

### List<String> → String[]

```java
List<String> list = new ArrayList<>();
list.add("ICN");
list.add("JFK");

String[] arr = list.toArray(new String[0]);
```

또는:

```java
String[] arr = list.toArray(String[]::new);
```

---

### List<Integer> → Integer[]

```java
List<Integer> list = List.of(1, 2, 3);

Integer[] arr = list.toArray(new Integer[0]);
```

---

### List<Integer> → int[]

`int[]`는 기본형 배열이라 `toArray()`로 바로 만들 수 없다.

```java
List<Integer> list = List.of(1, 2, 3);

int[] arr = list.stream()
                .mapToInt(Integer::intValue)
                .toArray();
```

---

## 7. 배열 → List

### String[] → List<String>

```java
String[] arr = {"a", "b", "c"};

List<String> list = Arrays.asList(arr);
```

주의:

```java
List<String> list = Arrays.asList(arr);
list.add("d"); // 에러
```

`Arrays.asList()`로 만든 리스트는 크기 변경이 안 된다.

수정 가능한 리스트가 필요하면:

```java
List<String> list = new ArrayList<>(Arrays.asList(arr));
```

---

### int[] → List<Integer>

`int[]`는 기본형 배열이라 바로 `Arrays.asList()`를 쓰면 원하는 결과가 나오지 않는다.

```java
int[] arr = {1, 2, 3};

List<Integer> list = Arrays.stream(arr)
                           .boxed()
                           .toList();
```

다만 `toList()`는 수정 불가능한 리스트일 수 있으므로, 수정할 거면 아래처럼 쓴다.

```java
List<Integer> list = Arrays.stream(arr)
                           .boxed()
                           .collect(Collectors.toList());
```

---

## 8. int[] 스트림 기본

### 합계

```java
int[] arr = {1, 2, 3, 4};

int sum = Arrays.stream(arr).sum();
```

결과:

```text
10
```

---

### 최댓값

```java
int max = Arrays.stream(arr).max().getAsInt();
```

---

### 최솟값

```java
int min = Arrays.stream(arr).min().getAsInt();
```

---

### 평균

```java
double avg = Arrays.stream(arr).average().getAsDouble();
```

---

### 개수

```java
long count = Arrays.stream(arr).count();
```

---

## 9. 필터링

### 짝수만 뽑기

```java
int[] arr = {1, 2, 3, 4, 5, 6};

int[] even = Arrays.stream(arr)
                   .filter(x -> x % 2 == 0)
                   .toArray();
```

결과:

```text
[2, 4, 6]
```

---

### 3보다 큰 수만 뽑기

```java
int[] result = Arrays.stream(arr)
                     .filter(x -> x > 3)
                     .toArray();
```

결과:

```text
[4, 5, 6]
```

---

## 10. map 사용법

`map`은 값을 변환할 때 사용한다.

### 모든 값 2배

```java
int[] arr = {1, 2, 3};

int[] result = Arrays.stream(arr)
                     .map(x -> x * 2)
                     .toArray();
```

결과:

```text
[2, 4, 6]
```

---

### 문자열 길이 배열 만들기

```java
String[] words = {"java", "spring", "db"};

int[] lengths = Arrays.stream(words)
                      .mapToInt(s -> s.length())
                      .toArray();
```

결과:

```text
[4, 6, 2]
```

더 줄이면:

```java
int[] lengths = Arrays.stream(words)
                      .mapToInt(String::length)
                      .toArray();
```

---

## 11. 정렬 스트림

### int[] 오름차순 정렬

```java
int[] arr = {3, 1, 2};

int[] sorted = Arrays.stream(arr)
                     .sorted()
                     .toArray();
```

결과:

```text
[1, 2, 3]
```

---

### int[] 내림차순 정렬

`int[]` 스트림은 바로 내림차순이 불편해서 `boxed()`를 사용한다.

```java
int[] arr = {3, 1, 2};

int[] sortedDesc = Arrays.stream(arr)
                         .boxed()
                         .sorted(Collections.reverseOrder())
                         .mapToInt(Integer::intValue)
                         .toArray();
```

결과:

```text
[3, 2, 1]
```

---

## 12. 중복 제거

### int[] 중복 제거

```java
int[] arr = {1, 2, 2, 3, 3, 3};

int[] result = Arrays.stream(arr)
                     .distinct()
                     .toArray();
```

결과:

```text
[1, 2, 3]
```

---

### 중복 제거 후 정렬

```java
int[] result = Arrays.stream(arr)
                     .distinct()
                     .sorted()
                     .toArray();
```

---

## 13. String 배열 스트림

### 문자열 배열을 리스트로

```java
String[] arr = {"a", "b", "c"};

List<String> list = Arrays.stream(arr)
                          .collect(Collectors.toList());
```

---

### 길이가 3 이상인 문자열만

```java
String[] arr = {"a", "abc", "hello"};

List<String> list = Arrays.stream(arr)
                          .filter(s -> s.length() >= 3)
                          .collect(Collectors.toList());
```

결과:

```text
["abc", "hello"]
```

---

### 문자열 배열 정렬

```java
String[] arr = {"banana", "apple", "car"};

String[] sorted = Arrays.stream(arr)
                        .sorted()
                        .toArray(String[]::new);
```

결과:

```text
["apple", "banana", "car"]
```

---

### 문자열 길이순 정렬

```java
String[] sorted = Arrays.stream(arr)
                        .sorted((a, b) -> a.length() - b.length())
                        .toArray(String[]::new);
```

---

## 14. collect 사용법

스트림 결과를 컬렉션으로 모을 때 사용한다.

### List로 모으기

```java
List<Integer> list = Arrays.stream(arr)
                           .boxed()
                           .collect(Collectors.toList());
```

---

### Set으로 모으기

```java
Set<Integer> set = Arrays.stream(arr)
                         .boxed()
                         .collect(Collectors.toSet());
```

---

### 문자열 합치기

```java
String[] arr = {"a", "b", "c"};

String result = Arrays.stream(arr)
                      .collect(Collectors.joining());
```

결과:

```text
abc
```

구분자 넣기:

```java
String result = Arrays.stream(arr)
                      .collect(Collectors.joining(","));
```

결과:

```text
a,b,c
```

---

## 15. String.join

문자열 리스트나 배열 합칠 때는 이게 제일 간단하다.

### List<String> 합치기

```java
List<String> list = List.of("frodo", "crodo");

String result = String.join(",", list);
```

결과:

```text
frodo,crodo
```

---

### String[] 합치기

```java
String[] arr = {"a", "b", "c"};

String result = String.join("-", arr);
```

결과:

```text
a-b-c
```

밴 아이디 문제에서 조합을 문자열 키로 만들 때 이런 식으로 쓴다.

```java
Collections.sort(copy);
result.add(String.join(",", copy));
```

---

## 16. anyMatch / allMatch / noneMatch

### 하나라도 조건 만족?

```java
boolean exists = Arrays.stream(arr)
                       .anyMatch(x -> x > 10);
```

---

### 전부 조건 만족?

```java
boolean allPositive = Arrays.stream(arr)
                            .allMatch(x -> x > 0);
```

---

### 아무것도 조건 만족 안 함?

```java
boolean noneNegative = Arrays.stream(arr)
                             .noneMatch(x -> x < 0);
```

---

## 17. findFirst

조건에 맞는 첫 번째 값 찾기.

```java
int[] arr = {3, 7, 10, 15};

int first = Arrays.stream(arr)
                  .filter(x -> x > 8)
                  .findFirst()
                  .getAsInt();
```

결과:

```text
10
```

주의: 조건에 맞는 값이 없으면 `getAsInt()`에서 에러가 날 수 있다.

안전하게 하려면:

```java
int first = Arrays.stream(arr)
                  .filter(x -> x > 100)
                  .findFirst()
                  .orElse(-1);
```

---

## 18. reduce

코테에서는 자주 안 쓰지만 알아두면 좋다.

### 곱 구하기

```java
int[] arr = {1, 2, 3, 4};

int product = Arrays.stream(arr)
                    .reduce(1, (a, b) -> a * b);
```

결과:

```text
24
```

---

### 최댓값 직접 구하기

```java
int max = Arrays.stream(arr)
                .reduce(Integer.MIN_VALUE, (a, b) -> Math.max(a, b));
```

하지만 최댓값은 그냥 이게 더 좋다.

```java
int max = Arrays.stream(arr).max().getAsInt();
```

---

## 19. Map 정렬

### Map의 key 기준 정렬

```java
Map<String, Integer> map = new HashMap<>();
map.put("banana", 3);
map.put("apple", 5);
map.put("car", 2);

List<String> keys = new ArrayList<>(map.keySet());
keys.sort((a, b) -> a.compareTo(b));
```

---

### Map의 value 기준 오름차순

```java
List<String> keys = new ArrayList<>(map.keySet());

keys.sort((a, b) -> map.get(a) - map.get(b));
```

---

### Map의 value 기준 내림차순

```java
keys.sort((a, b) -> map.get(b) - map.get(a));
```

---

### value 내림차순, 같으면 key 사전순

```java
keys.sort((a, b) -> {
    if (map.get(a).equals(map.get(b))) {
        return a.compareTo(b);
    }
    return map.get(b) - map.get(a);
});
```

이런 문제에서 자주 쓴다.

```text
많이 나온 단어 순
신고 횟수 순
재생 횟수 순
```

---

## 20. Map 개수 세기

람다식은 아니지만 코테에서 매우 중요하다.

```java
Map<String, Integer> map = new HashMap<>();

map.put(name, map.getOrDefault(name, 0) + 1);
```

예시:

```java
String[] names = {"a", "b", "a", "c", "a"};

Map<String, Integer> map = new HashMap<>();

for (String name : names) {
    map.put(name, map.getOrDefault(name, 0) + 1);
}
```

결과:

```text
a = 3
b = 1
c = 1
```

---

## 21. Comparator.comparing

익숙해지면 정렬 코드가 깔끔해진다.

### 객체 리스트 예시

```java
class Node {
    int x;
    int y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
```

---

### x 기준 오름차순

```java
list.sort(Comparator.comparingInt(node -> node.x));
```

---

### x 기준 내림차순

```java
list.sort((a, b) -> b.x - a.x);
```

또는:

```java
list.sort(Comparator.comparingInt((Node node) -> node.x).reversed());
```

---

### x 오름차순, y 오름차순

```java
list.sort(
    Comparator.comparingInt((Node node) -> node.x)
              .thenComparingInt(node -> node.y)
);
```

---

### x 오름차순, y 내림차순

```java
list.sort(
    Comparator.comparingInt((Node node) -> node.x)
              .thenComparing((a, b) -> b.y - a.y)
);
```

다만 코테에서는 아래 방식이 더 직관적일 때가 많다.

```java
list.sort((a, b) -> {
    if (a.x == b.x) {
        return b.y - a.y;
    }
    return a.x - b.x;
});
```

---

## 22. 코테에서 자주 쓰는 패턴 모음

### 1번: int[] 합계

```java
int sum = Arrays.stream(arr).sum();
```

---

### 2번: int[] 최댓값

```java
int max = Arrays.stream(arr).max().getAsInt();
```

---

### 3번: int[] 정렬

```java
Arrays.sort(arr);
```

스트림보다 이게 더 간단하다.

---

### 4번: int[] 내림차순 정렬

```java
int[] desc = Arrays.stream(arr)
                   .boxed()
                   .sorted(Collections.reverseOrder())
                   .mapToInt(Integer::intValue)
                   .toArray();
```

---

### 5번: List<Integer> → int[]

```java
int[] answer = list.stream()
                   .mapToInt(Integer::intValue)
                   .toArray();
```

---

### 6번: List<String> → String[]

```java
String[] answer = list.toArray(new String[0]);
```

또는:

```java
String[] answer = list.toArray(String[]::new);
```

---

### 7번: 문자열 리스트 합치기

```java
String key = String.join(",", list);
```

---

### 8번: 2차원 배열 정렬

```java
Arrays.sort(arr, (a, b) -> {
    if (a[0] == b[0]) return a[1] - b[1];
    return a[0] - b[0];
});
```

---

### 9번: 우선순위 큐 최소 기준

```java
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
```

---

### 10번: 우선순위 큐 최대 기준

```java
PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
```

---

## 23. 코테에서 스트림 쓸 때 주의할 점

### 1. 너무 복잡하면 for문이 낫다

예를 들어 이런 코드는 스트림으로 작성할 수 있다.

```java
int sum = Arrays.stream(arr)
                .filter(x -> x % 2 == 0)
                .map(x -> x * 2)
                .sum();
```

하지만 for문이 더 디버깅하기 쉬울 때도 많다.

```java
int sum = 0;

for (int x : arr) {
    if (x % 2 == 0) {
        sum += x * 2;
    }
}
```

코테에서는 가독성과 디버깅 쉬움이 중요해서, 스트림을 무조건 쓰는 것이 좋은 것은 아니다.

---

### 2. int[]와 Integer[]는 다르다

```java
int[] arr1 = {1, 2, 3};
Integer[] arr2 = {1, 2, 3};
```

- `int[]`: 기본형 배열
- `Integer[]`: 객체 배열

그래서 `List<Integer>`를 `int[]`로 바꾸려면:

```java
int[] result = list.stream()
                   .mapToInt(Integer::intValue)
                   .toArray();
```

---

### 3. Arrays.asList(int[]) 조심

이 코드는 조심해야 한다.

```java
int[] arr = {1, 2, 3};

List<int[]> list = Arrays.asList(arr);
```

이렇게 하면 `List<Integer>`가 아니라 `List<int[]>`가 된다.

원하는 게 `List<Integer>`라면:

```java
List<Integer> list = Arrays.stream(arr)
                           .boxed()
                           .collect(Collectors.toList());
```

---

### 4. toList()와 collect(Collectors.toList()) 차이

```java
List<Integer> list = Arrays.stream(arr)
                           .boxed()
                           .toList();
```

이렇게 만든 리스트는 수정이 안 될 수 있다.

```java
list.add(4); // 에러 가능
```

수정할 거면 이걸 쓰는 게 안전하다.

```java
List<Integer> list = Arrays.stream(arr)
                           .boxed()
                           .collect(Collectors.toList());
```

---

## 24. 추천 암기 세트

코테에서는 일단 이것들만 외워도 충분하다.

```java
// List<String> -> String[]
String[] arr = list.toArray(new String[0]);

// List<Integer> -> int[]
int[] arr = list.stream()
                .mapToInt(Integer::intValue)
                .toArray();

// int[] 합계
int sum = Arrays.stream(arr).sum();

// int[] 최댓값
int max = Arrays.stream(arr).max().getAsInt();

// int[] 중복 제거
int[] result = Arrays.stream(arr)
                     .distinct()
                     .toArray();

// int[] -> List<Integer>
List<Integer> list = Arrays.stream(arr)
                           .boxed()
                           .collect(Collectors.toList());

// 2차원 배열 정렬
Arrays.sort(arr, (a, b) -> {
    if (a[0] == b[0]) return a[1] - b[1];
    return a[0] - b[0];
});

// PriorityQueue 최소 기준
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

// PriorityQueue 최대힙
PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

// 문자열 합치기
String key = String.join(",", list);
```

---

## 25. 실전 기준 추천

코테에서 스트림은 주로 이런 곳에 쓰는 것을 추천한다.

```text
List<Integer> -> int[]
int[] -> List<Integer>
합계 / 최댓값 / 최솟값
중복 제거
간단한 필터링
문자열 합치기
```

반대로 아래 유형은 그냥 for문이 더 좋다.

```text
BFS
DFS
DP
백트래킹
투포인터
슬라이딩 윈도우
구현/시뮬레이션
```

정리하면 다음과 같다.

```text
변환 / 집계 / 정렬 → 스트림, 람다식 좋음
상태 관리 / 탐색 / 복잡한 로직 → for문이 좋음
```
