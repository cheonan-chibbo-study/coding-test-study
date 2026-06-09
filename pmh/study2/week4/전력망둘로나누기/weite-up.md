# 문제링크
https://school.programmers.co.kr/learn/courses/30/lessons/86971?gad_source=1&gad_campaignid=23037984604&gbraid=0AAAAAC_c4nDFamOy7v-PFU6Rugj7GdNla&gclid=Cj0KCQjwrZTRBhDSARIsAHidYfeMVafGjHFwyk2WXRxnKXg1his5MbOU-A66isZ44gn25PHI47aryTMaAtfyEALw_wcB
# 30분내 어디까지 풀었는가
30분내 풀기완료
# 왜 못풀었는가 - 
### 풀이가 안떠올랐는지 / 어떻게 풀지는 알겠는데 구현을 못하겠다.


# 접근방법
1. 이미 풀었던것이지만 기존에는 그래프를 이미 끓어서 그때그떄 그래프 만들었던 방식이었다.
2. 이 방식말고 다른 방식으로 풀어보고 싶어 그래프를 미리 연결시키고 전선을 끓고 수를 세고 다시 연결하는방식으로 다시 풀었다.
3. n 의 갯수에맞게 리스트 배열 크기를 만들어준다
4. 리스트 배열을 통해 wires 에 맞게 양방향 그래프로 연결해준다
5. 그후 wires 를 0~n-1 까지 탐색하면서 그래프를 하나씩 끓는다
6. 끓은후 남은 노드를 dfs 를 통해 센후 차이를 계산한다.
7. 다시 그래프를 연결한다
8. wires 를 끝까지 돈후 최소 차이를 가지는 갯수를 반환한다.
# 시간/공간 복잡도
## 시간 복잡도:

- 전선(`wires`)의 개수는 `n - 1`개
- 각 전선을 한 번씩 끊어보므로 `O(n)`번 반복
- 전선을 끊을 때 `ArrayList.remove(Object)`는 해당 원소를 찾는 과정이 필요하므로 `O(n)`
- 끊은 후 DFS로 연결된 송전탑 개수를 구하는 데 `O(n)`
- 다시 연결(`add`)하는 것은 `O(1)`

따라서 전체 시간 복잡도는  
O(n) × (O(n) + O(n)) = O(n²)  

## 공간 복잡도:
사용하는 추가 공간은

- 인접 리스트(`g`) : `O(n)`
- 방문 배열(`visited`) : `O(n)`
- DFS 재귀 호출 스택 : 최악의 경우 `O(n)`

따라서

**최종 공간 복잡도: `O(n)`**

# 배운점

`ArrayList<Integer>`의 `remove()`는 오버로딩되어 있으며 두 가지 방식으로 동작한다.

```java
remove(int index);   // 인덱스 삭제
remove(Object o);    // 값 삭제
```

따라서 아래 코드처럼 `int` 타입의 값을 전달하면 값을 삭제하는 것이 아니라 해당 인덱스를 삭제하려고 시도한다.

```java
g[u].remove(v);
g[v].remove(u);
```

예를 들어,

```java
g[1] = [3];

g[1].remove(3);
```

위 코드는 값 `3`을 삭제하는 것이 아니라 **3번 인덱스를 삭제**하려고 한다.

하지만 `g[1]`의 크기는 1이므로 3번 인덱스가 존재하지 않아 `IndexOutOfBoundsException`이 발생한다.

따라서 `ArrayList<Integer>`에서 특정 값을 삭제하려면 `Integer` 객체로 변환하여 `remove(Object)`가 호출되도록 해야 한다.

```java
g[u].remove(Integer.valueOf(v));
g[v].remove(Integer.valueOf(u));
```

이렇게 작성하면 인덱스가 아닌 **값 자체를 기준으로 삭제**할 수 있다.

## 정리

```java
list.remove(3);
```

- `remove(int index)` 호출
- 3번 인덱스 삭제

```java
list.remove(Integer.valueOf(3));
```

- `remove(Object o)` 호출
- 값 3 삭제

`ArrayList<Integer>`에서는 숫자를 그대로 전달하면 Java가 인덱스로 해석하므로, 값을 삭제할 때는 `Integer.valueOf()` 또는 `(Integer)` 캐스팅을 사용해야 한다.