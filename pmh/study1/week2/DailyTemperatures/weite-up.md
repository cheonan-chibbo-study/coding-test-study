# 문제링크
https://leetcode.com/problems/daily-temperatures/description/
# 접근방법
나보다 더 따뜻한 날 을 찾을 때까지 현재 날짜들을 Stack에 넣어두는 방식으로 해야겠다고 생각함

1. 온도 배열을 하나씩 순회한다.

2. 현재 온도가 스택의 맨 위에 있는 날의 온도보다 높다면, 더 따뜻한 날을 찾은 것으로 간주.

3. 그날의 인덱스 차이를 계산해 결과 배열에 저장하고, 스택에서 해당 날짜를 꺼낸다

4. 현재 온도가 높지 않다면 일단 현재 날짜의 인덱스를 스택에 넣고 다음 배열로 넘어간다.

Stack 대신 Deque 를 사용한 이유 자바의 Stack은 오래된 클래스로 자바 8 이후부터는 Stack 클래스 보다는 Deque 인터페이스와 그 구현체인 ArrayDeque 를 사용하라고
권고 하고있다.
좀더 자세한 이유 :
1. 불필요한 동기화 (Synchronized): Stack은 Vector를 상속받았는데, 모든 메서드에 synchronized가 걸려있다. 혼자 코딩하는 알고리즘 테스트나 일반적인 상황에서는 불필요하게 성능만 갉아먹는 셈이다.

2. 잘못된 상속: Stack이 Vector를 상속받는 바람에, 스택인데도 중간에 값을 넣거나 뺄 수 있는 메서드(insert, remove 등)를 그대로 노출하게 된다. 스택의 "한쪽 끝에서만 넣고 뺀다"는 원칙(Encapsulation)이 깨진다.

3. 성능 차이: ArrayDeque는 내부적으로 가변 배열을 사용하여 인덱스 접근이 매우 빠르고, Stack보다 메모리 오버헤드가 훨씬 적다.
https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html