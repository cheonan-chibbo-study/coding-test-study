## 👀 제한 시간 안에 어디까지 해냈는가?

혼자서 문제를 풀지 못했다. 수학 공식 자체는 이해했지만 이 공식을 코드로 구현하지 못했다.

---

## 🧑‍🔬 문제 분석

1 ~ n 정수를 활용해 만들 수 있는 모든 순열 중 k번째 순열을 오름 차순에서 찾아 반환하는 문제이다.

문제에 주어진 제약 조건은 다음과 같다.

**Constraints:**

- `1 <= n <= 9`
- `1 <= k <= n!`

---

## 🤔 풀이 고민

### 완전 탐색

제일 먼저 떠올린 방법은 완전 탐색이다. 단순하게 생각하면 1 ~ n의 정수로 구성된 생성 가능한 모든 순열을 생성한 뒤 k - 1번째 순열을 찾아 반환하면 되는 문제이다.

하지만 이렇게 간단하게 풀릴 문제라면 `Hard` Level이 아닐 것이다… 아쉽게도 위 방식으로 풀이 코드를 작성하면 시간 초과가 발생한다.

우선 문제에 주어지는 입력 조건을 확인하면 n은 최대 9가 주어질 수 있다. 그럼 1 ~ 9로 구성된 길이가 9인 순열을 모두 생성할 경우 필요한 시간은 다음과 같다.

- `n! * n` → 9! * 9 == 3,265,920 == 3 * 10^6

단순히 생성할 수 있는 순열의 개수는 `n!` 개지만 순열 하나를 만드는 과정에서 배열 복사가 발생하므로 각 케이스 마다 `n` 만큼의 시간이 소요된다. 따라서 최악의 경우 순열 생성에 대략 `10^6` 번의 연산이 발생하는데 순열을 만드는 로직은 재귀로 작성하기 때문에 오버 헤드를 발생시켜 시간 초과가 발생한다.

따라서 단순한 완전 탐색 방식으로는 이 문제를 해결할 수 없다.

### 수학적 접근

이 문제를 해결하기 위해서는 어느정도 수학적인 접근이 필요하다.

만약 n이 4라고 가정하면 1 ~ 4 정수를 활용해 다음과 같이 순열을 만들 수 있을 것이다.

```java
1 2 3 4
1 2 4 3
1 3 2 4
1 3 4 2
1 4 2 3
1 4 3 2

2 1 3 4
2 1 4 3
2 3 1 4
2 3 4 1
2 4 1 3
2 4 3 1

3 1 2 4
3 1 4 2
3 2 1 4
3 2 4 1
3 4 1 2
3 4 2 1

4 1 2 3
4 1 3 2
4 2 1 3
4 2 3 1
4 3 1 2
4 3 2 1
```

생성된 순열들을 살펴보면 맨 앞 자릿수마다 6개의 순열이 존재함을 확인할 수 있다. 이를 수학 공식으로 나타내면 n 길이의 순열이 존재할 때 각 앞자리마다 생성될 수 있는 순열의 총 개수는 `(n - 1)!` 이다.

이 때 내가 14번째(k = 14) 순열을 찾고 싶다고 가정해보겠다. 앞서 살펴본것 처럼 생성될 모든 순열들은 앞 자릿수마다 `(n - 1)!` 개의 순열이 존재한다. 현재 n은 4이기 때문에 1, 2, 3, 4 각 앞자릿수마다 (4 - 1)! == 6, 즉 6개의 순열이 존재함을 확인할 수 있다.

따라서 14번째 순열은 (현재 0부터 시작하는 인덱스를 사용하므로 정확히는 k - 1 → 13번째)

- 13 / 6 → 2번째 집단에 속함을 확인할 수 있다. 따라서 순열의 맨 앞자리는 `3` 임을 확인할 수 있다.
- 13 % 6 → 2번째 집단에서 1번째 순열이 타겟임을 확인할 수 있다. (위 그림으로 따지면 `3142`)

2번째 집단은 모두 맨 앞자리가 3, 그리고 그 집단에서 1번째 순열이기 때문에 위에서 사용한 방식을 다시 활용해 계산을 이어나갈 수 있다.

```java
3 1 2 4
3 1 4 2

3 2 1 4
3 2 4 1

3 4 1 2
3 4 2 1
```

위 순열들에서 1번째 순열을 구하기 위해

- 현재 두 번째 자릿수는 1, 2, 4로 구성되어 있고 각각 2개의 순열이 존재한다. 순열의 길이를 모두 3으로 가정할 수 있기 때문에 `(3 - 1)!` 개가 존재함으로 공식을 세울 수 있다.
- 여기서 1번째 순열을 구하고 싶기 때문에
    - 1/ 2 → 0이므로 0번째 집단에 속함을 확인할 수 있다. 즉 두번째 자릿수는 `1` 이다.
    - 1 % 2 → 1이므로 0번째 집단에서 1번째 순열이 타겟이다. (`3142`)

```java
3 1 2 4

3 1 4 2
```

마지막으로 연산을 진행하면 각 순열 집단은 `(2 - 1)! -> 1` 개 존재하기 때문에

1번째 순열을 구하면

- 1 /1 → 1번째 집단
- 이때 1번째 집단은 `3142` 순열이 유일하다.

따라서 n = 4인 전체 순열에서 k = 14인 순열은 `3142` 이다.

이 방식은 단순 산술 연산만 진행하기 때문에 시간 복잡도가 거의 1의 수렴한다. 따라서 이 수학적 공식을 활용해 풀이를 작성하면 문제를 해결할 수 있다.

### 결론

- 모든 순열을 구해 k 번째를 구하는 완전 탐색 방식은 이 문제의 입력 조건상 재귀 방식으로 10^6 연산을 수행하기에는 오버 헤드가 발생하므로 시간 초과가 발생한다. 따라서 완전 탐색으로는 문제를 해결할 수 없다.
- 순열의 특성을 활용한 수학적 접근 방식을 활용하면 O(1)에 수렴하는 산술 연산만 활용하기 때문에 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

혼자서 코드 구현을 하지 못해 이전 정답을 참고했다.

- k를 넘길 때 그냥 넘기지 말고 `k - 1`로 넘겨야한다. nums 리스트에 0부터 접근하기 때문이다.

참고로 파이썬은 `from itertools import permutations` 모듈을 사용하면 쉽게 풀 수 있지만 Java는 직접 수학 공식을 활용한 풀이 코드를 작성해야 문제를 풀 수 있다.

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01 - 수학 공식 활용

    ```python
    class Solution:
        def getPermutation(self, n: int, k: int) -> str:
            nums = [str(num) for num in range(1, n + 1)]
    
            # 메서드
            def recursive(k):
                nonlocal answer
    
                if len(nums) == 1:
                    answer += nums[0]
                    return
                
                case_count = facto(len(nums) - 1)
                target = k // case_count
                answer += nums[target]
                nums.pop(target)
    
                recursive(k % case_count)
    
            def facto(num):
                result = 1
                for i in range(2, num + 1):
                    result *= i
                
                return result
    
            # 메인 로직
            answer = ""
            recursive(k - 1)
    
            return answer
    ```

- solution02 - permutaion 모듈 활용

    ```python
    from itertools import permutations
    
    class Solution:
        def getPermutation(self, n: int, k: int) -> str:
            candi = [list(p) for p in permutations([str(num) for num in range(1, n + 1)])]
            return ''.join(candi[k - 1])
    ```


### Java 풀이

- solution01

    ```java
    import java.util.*;
    
    class Solution {
    
        List<String> nums;
        StringBuilder sb;
    
        public String getPermutation(int n, int k) {
            this.nums = new ArrayList<>();
            for (int num = 1; num <= n; num++) {
                nums.add(String.valueOf(num));
            }
    
            this.sb = new StringBuilder();
    
            // 메인 로직
            recursive(k - 1);
            return sb.toString();
        }
    
        private void recursive(int k) {
            if (nums.size() == 1) {
                sb.append(nums.get(0));
                return;
            }
    
            int caseCount = facto(nums.size() - 1);
            int target = k / caseCount;
            sb.append(nums.get(target));
            nums.remove(target);
    
            recursive(k % caseCount);
        }
    
        private int facto(int num) {
            int result = 1;
            for (int i = 2; i <= num; i++) {
                result *= i;
            }
    
            return result;
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- 그래도 이 문제를 완전 처음 봤을 때 보다는 덜 어렵게 느껴지는거 같다.
- 어느정도 수학 공식 + 구현 암기가 필요한 문제이다. 이 문제가 실전에서 사용될지는 잘 모르겠지만 그래도 킬캠에 있던 문제니 공부는 해두면 좋을거 같다.