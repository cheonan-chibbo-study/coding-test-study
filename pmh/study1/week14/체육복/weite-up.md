# 문제링크
https://school.programmers.co.kr/learn/courses/30/lessons/42862?language=java
# 30분내 어디까지 풀었는가
30분에 풀었지만 테스트 실패
# 왜 못풀었는가 - 
### 풀이가 안떠올랐는지 / 어떻게 풀지는 알겠는데 구현을 못하겠다.


# 접근방법
1. 배열 인덱스를 학생번호를 쓰고 배열 안에 수를 학생이 가진 체육복이라고 생각하고 품
2. lost 인애들은 -1 
3. reserve 인 애들은 +1 해주고
4. lost 를 정렬해 학생번호가 앞인애들부터 빌려준다
5. lost 1부터 돌아서 바로 앞뒤 학생을 확인해 빌려줄수있으면 빌려준다
6. 학생이 가진 체육복이 1 이상애들로 수를 센다
# 최적화
있어. 지금 방식도 충분히 통과 가능하지만, 더 깔끔하고 안정적으로 최적화하려면 lost, reserve를 정렬하고 두 포인터로 풀 수 있다.

reserve[j]가 lost[i]에게 빌려줄 수 있는지 확인

빌려줄 수 있는 조건은:

Math.abs(lost[i] - reserve[j]) == 1

다만 중요한 예외가 있다.
- 여벌 체육복이 있는 학생이 도난당한 경우
- 자기 체육복만 입을 수 있고 남에게 빌려줄 수 없음

그래서 먼저 lost와 reserve에서 겹치는 학생을 제거해야한다.
최적화된 풀이: 정렬 + 투포인터 + 그리디
```java
import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        Arrays.sort(lost);
        Arrays.sort(reserve);

        boolean[] lostRemoved = new boolean[lost.length];
        boolean[] reserveRemoved = new boolean[reserve.length];

        // 1. 여벌도 있고 도난도 당한 학생 제거
        int i = 0;
        int j = 0;

        while (i < lost.length && j < reserve.length) {
            if (lost[i] == reserve[j]) {
                lostRemoved[i] = true;
                reserveRemoved[j] = true;
                i++;
                j++;
            } else if (lost[i] < reserve[j]) {
                i++;
            } else {
                j++;
            }
        }

        // 2. 실제로 체육복이 없는 학생 수만큼 answer 감소
        int answer = n;

        for (int k = 0; k < lost.length; k++) {
            if (!lostRemoved[k]) {
                answer--;
            }
        }

        // 3. 투포인터로 빌려주기
        i = 0;
        j = 0;

        while (i < lost.length && j < reserve.length) {
            if (lostRemoved[i]) {
                i++;
                continue;
            }

            if (reserveRemoved[j]) {
                j++;
                continue;
            }

            int lostStudent = lost[i];
            int reserveStudent = reserve[j];

            if (reserveStudent < lostStudent - 1) {
                // reserve 학생 번호가 너무 작아서 현재 lost 학생에게 못 빌려줌
                j++;
            } else if (reserveStudent > lostStudent + 1) {
                // reserve 학생 번호가 너무 커서 현재 lost 학생은 못 빌림
                i++;
            } else {
                // 차이가 1이면 빌려줄 수 있음
                answer++;
                i++;
                j++;
            }
        }

        return answer;
    }
}
```
최적화된 풀이: Set + 정렬 + 그리디
```java

import java.util.*;

class Solution {
public int solution(int n, int[] lost, int[] reserve) {
Arrays.sort(lost);
Arrays.sort(reserve);

        Set<Integer> lostSet = new HashSet<>();
        Set<Integer> reserveSet = new HashSet<>();

        for (int x : lost) {
            lostSet.add(x);
        }

        for (int x : reserve) {
            reserveSet.add(x);
        }

        // 여벌도 있고 도난도 당한 학생 제거
        for (int x : reserve) {
            if (lostSet.contains(x)) {
                lostSet.remove(x);
                reserveSet.remove(x);
            }
        }

        for (int x : reserve) {
            // 이미 자기 것도 잃어버린 학생이면 못 빌려줌
            if (!reserveSet.contains(x)) continue;

            if (lostSet.contains(x - 1)) {
                lostSet.remove(x - 1);
            } else if (lostSet.contains(x + 1)) {
                lostSet.remove(x + 1);
            }
        }

        return n - lostSet.size();
    }
}
```

더 추천하는 방식: 배열 풀이

사실 이 문제는 n이 작아서 배열 풀이가 제일 직관적이고 빠르다.

```java
import java.util.*;

class Solution {
public int solution(int n, int[] lost, int[] reserve) {
int[] clothes = new int[n + 2];

        for (int i = 1; i <= n; i++) {
            clothes[i] = 1;
        }

        for (int x : lost) {
            clothes[x]--;
        }

        for (int x : reserve) {
            clothes[x]++;
        }

        for (int i = 1; i <= n; i++) {
            if (clothes[i] == 0) {
                if (clothes[i - 1] == 2) {
                    clothes[i]++;
                    clothes[i - 1]--;
                } else if (clothes[i + 1] == 2) {
                    clothes[i]++;
                    clothes[i + 1]--;
                }
            }
        }

        int answer = 0;

        for (int i = 1; i <= n; i++) {
            if (clothes[i] > 0) {
                answer++;
            }
        }

        return answer;
    }
}
```
여기서 n + 2로 만든 이유는 i - 1, i + 1 검사할 때 경계 조건을 줄이기 위해서이다

예를 들어 i = 1일 때도:

clothes[i - 1] // clothes[0]

접근 가능하고, i = n일 때도:

clothes[i + 1] // clothes[n + 1]

접근 가능하다.

그래서 이런 조건을 쓰지 않아도 된다  
- if (i > 1)  
- if (i < n)
# 배운점 