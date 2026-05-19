# 문제링크
https://school.programmers.co.kr/learn/courses/30/lessons/42883?language=java
# 30분내 어디까지 풀었는가
30분내 풀지못함
# 왜 못풀었는가 - 
### 풀이가 안떠올랐는지 / 어떻게 풀지는 알겠는데 구현을 못하겠다.
풀이가 떠올라 풀고있었지만 구현중 시간초과 그러나 최종적으로 틀려서 테스트 실패

# 접근방법
1. 처음에는 k 갯수 만큼 숫자를 제거해서 만들수있는 모든 수를 구해 정렬후 반환하는 방식을 생각함
2. 그러나 그렇게할시 이중 반목문이 생기게되고 O(n^2) 이 되고 최대 number 가 1,000,000 이므로   
   1,000,000 ^ 2 가 되 시간초과 가 발생할수있다
3. 스택 or 덱 을 사용해 number 의 수를 푸쉬하되 최근 푸쉬한것이 다음수보다 작을경우 최근 푸쉬한수를 빼고 다음수를 푸쉬한다  
4. 숫자를 제거한것이 되니 k-- 를 한다
5. 다음수가 최근푸쉬한수보다 작을경우 그냥 푸쉬한다
6. 제일 큰수가 남도록 스택을 푸쉬하고 팝한다
6. k 가 0 되지 않았다면 제거횟수를 만족하지않았으므로 k 가 0 일 될때까지 pop 한다

# 시간/공간 복잡도
## 시간 복잡도
**O(N)**  
각 수자가 push 1번 pop 최대 1번을 하므로 총 연산수는 최대 2N 이다
## 공간 복잡도
**O(N)**  
덱에 숫자를 저장하니까:  
최악의 경우 아무것도 제거 안 되면 숫자 N개 저장됨.  
예:  
987654321   
이런 경우 전부 stack/deque 에 들어간다
# 배운점 
스택 과 덱이 그냥 출력도 가능하다는 사시을 알게되었다  
스택 :아래 -> 위 순으로 출력되고 
```java
Stack<Integer> stack = new Stack<>();
stack.push(4);
stack.push(1);
stack.push(7);

System.out.println(stack);

//출력
[4, 1, 7]
```
덱   :위 -> 아래 순서로 출력된다

```java
import java.util.ArrayDeque;
import java.util.Deque;

Deque<Integer> deque = new ArrayDeque<>();
deque.push(4);
deque.push(1);
deque.push(7);
System.out.println(deque);

//출력
[7,1,4]
```