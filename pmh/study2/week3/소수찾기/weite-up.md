# 문제링크
https://school.programmers.co.kr/learn/courses/30/lessons/42839
# 30분내 어디까지 풀었는가
30분내 풀기 실패
# 왜 못풀었는가 - 
### 풀이가 안떠올랐는지 / 어떻게 풀지는 알겠는데 구현을 못하겠다.
어떻게 풀지는 생각했지만 소수찾는 식 이 기억이 안났고 구현중 시간이 초과함

# 접근방법
1. dfs 를 통안 완전 탐색을 통해 조합할수있는 모든 숫자를 구하고 set에다가 저장해 중복숫자를 방지한다
2. 그렇게 만든 숫자의 조합을 isPrime 를 통해 소수인지 판별한다
```java
 private boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        int r = (int) Math.sqrt(n);
        for (int i = 3; i <= r; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
```
3. 소수가 맞다면 count++ 를 한다.
# 시간/공간 복잡도
시간 복잡도:  
공간 복잡도:

# 배운점 

### 소수란?

소수(Prime Number)란 **1과 자기 자신만 약수로 가지는 수**를 말한다.

예시

- 소수: 2, 3, 5, 7, 11, 13
- 소수가 아닌 수: 1, 4, 6, 8, 9, 10

---

### 소수 판별 코드

```java
private boolean isPrime(int n) {
    // 2보다 작은 수는 소수가 아님
    if (n < 2) return false;

    // 2는 유일한 짝수 소수
    if (n == 2) return true;

    // 2를 제외한 짝수는 소수가 아님
    if (n % 2 == 0) return false;

    // √n 까지만 검사
    int r = (int) Math.sqrt(n);

    for (int i = 3; i <= r; i += 2) {
        if (n % i == 0) {
            return false;
        }
    }

    return true;
}
```

---

### 왜 √N 까지만 검사할까?

어떤 수 `N`이 합성수라면 반드시 약수 쌍이 존재한다.

예를 들어 36의 경우

```text
36 = 2 × 18
36 = 3 × 12
36 = 4 × 9
36 = 6 × 6
```

`√36 = 6`

6보다 큰 약수를 찾게 되면 이미 그에 대응되는 더 작은 약수가 존재한다.

즉, 약수는 항상 √N을 기준으로 쌍을 이루기 때문에 **√N까지만 검사해도 모든 약수를 확인할 수 있다.**

---

### 예시 1 : 17

```java
Math.sqrt(17) ≈ 4.12
```

검사 범위

```text
3
```

```java
17 % 3 != 0
```

더 이상 검사할 수 있는 수가 없으므로

```java
return true;
```

17은 소수이다.

---

### 예시 2 : 21

```java
Math.sqrt(21) ≈ 4.58
```

검사 범위

```text
3
```

```java
21 % 3 == 0
```

약수가 존재하므로

```java
return false;
```

21은 소수가 아니다.

---

### 최적화 포인트

#### 1. 2보다 작은 수 제거

```java
if (n < 2) return false;
```

- 0과 1은 소수가 아니다.

#### 2. 2는 바로 처리

```java
if (n == 2) return true;
```

- 2는 유일한 짝수 소수이다.

#### 3. 짝수 제거

```java
if (n % 2 == 0) return false;
```

- 2를 제외한 모든 짝수는 소수가 아니다.

#### 4. 홀수만 검사

```java
for (int i = 3; i <= r; i += 2)
```

- 짝수는 이미 제거했다.
- 홀수만 검사하여 탐색 횟수를 절반으로 줄일 수 있다.

---

### 시간복잡도

#### 일반적인 방법

```java
for (int i = 2; i < n; i++)
```

시간복잡도

```text
O(N)
```

#### 현재 방법

```java
for (int i = 3; i <= Math.sqrt(n); i += 2)
```

시간복잡도

```text
O(√N)
```

---

### 핵심 정리

1. 소수는 1과 자기 자신만 약수로 가진다.
2. 2는 유일한 짝수 소수이다.
3. 2를 제외한 짝수는 모두 소수가 아니다.
4. 약수 검사는 √N까지만 하면 된다.
5. 짝수를 제외했으므로 홀수만 검사한다.
6. 시간복잡도를 O(N)에서 O(√N)으로 줄일 수 있다.