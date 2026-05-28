# 문제링크
https://school.programmers.co.kr/learn/courses/30/lessons/42885?language=java
# 30분내 어디까지 풀었는가
30분내 풀지못함
# 왜 못풀었는가 - 
### 풀이가 안떠올랐는지 / 어떻게 풀지는 알겠는데 구현을 못하겠다.
풀었지만 풀이가 잘못되서 틀렸다   
작은순으로 정렬해서 작은애들부터 태우는 방식으로 하였는데 틀린 방식이었다  
정렬후 보트가 2명을 태울수있으니 몸무게 가장 작은애 + 가장 큰애 방식으로 했어야했다

# 접근방법
1. 사람을 몸무게가 작은순으로 정렬한다
2. while 문을 사용해 가장작은애 몸무게 + 가장 큰 몸무게 사람을 더한다
3. 만약 더한 몸무게가 limit 보다 클시 가장 큰 몸무게를 가진사람과 그 누구도 같이 타지 못하므로  
가장 큰 몸무게를 가진 사람만을 보낸후 rifht 를 -1 한다
4. 만약 더한 몸무게가 limit 보다 작거나 같을시 left 는 +1 right -1 을 해 두사람을 태워 보낸다
5. lefr == right 가 같은경우 마지막 사람이 남아 태워야하는경우가 있으므로 보트를 태운횟수를 증가시키고 break 한다.

# 최적화 
```java
import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);

        int left = 0;
        int right = people.length - 1;
        int count = 0;

        while (left <= right) {
            // 가장 가벼운 사람 + 가장 무거운 사람이 같이 탈 수 있으면
            if (people[left] + people[right] <= limit) {
                left++;
            }

            // 같이 타든 못 타든, 가장 무거운 사람은 무조건 한 보트에 태움
            right--;
            count++;
        }

        return count;
    }
}
```
- sum 은 누적값이 아니라 매번 비교용이 기때문에 굳이 선언해서 사용할 필요가없다
- 매번 두사람이태운 무게를 비교해서 보트를 태운 횟수를 센다