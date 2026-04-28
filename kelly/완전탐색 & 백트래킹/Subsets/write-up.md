## 👀 제한 시간 안에 어디까지 해냈는가?

`10분 57초`만에 혼자서 문제를 해결했다.

---

## 🧑‍🔬 문제 분석

정수 리스트가 주어질 때 이 리스트 모든 부분 집합을 구해 반환하는 문제이다.

문제에 주어진 제약 조건은 다음과 같다.

**Constraints:**

- `1 <= nums.length <= 10`
- `10 <= nums[i] <= 10`
- All the numbers of `nums` are **unique**.

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

주어지는 입력의 크기가 매우 작기 때문에 재귀 함수를 활용하면 문제를 해결할 수 있다.

### 결론

- 내가 생각한 풀이로 코드를 작성해 제출하면 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 내가 처음 작성한 코드

혼자서 코드 구현에 성공했다. 최종 정답 코드를 참고하자.

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01 - subsets 직접 구현

    ```python
    class Solution:
        def subsets(self, nums: List[int]) -> List[List[int]]:
            # 메서드
            def recursive(temp, result, start):
                result.append(temp[::])
    
                if len(temp) == len(nums):
                    return
    
                for i in range(start, len(nums)):
                    temp.append(nums[i])
                    recursive(temp, result, i + 1)
                    temp.pop()
    
            # 메인 로직
            answer = []
            recursive([], answer, 0)
            return answer
    ```

- solution02 - 파이썬 모듈 활용

    ```python
    from itertools import combinations
    
    class Solution:
        def subsets(self, nums: List[int]) -> List[List[int]]:
            # 메인 로직
            answer = [[]]
            for i in range(1, len(nums) + 1):
                answer.extend(combinations(nums, i))
            
            return answer
    ```

    - 따로 부분 집합 모듈은 없지만 `combinations`를 활용할 수 있다.

### Java 풀이

- solution01

    ```java
    import java.util.*;
    
    class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> answer = new ArrayList<>();
            recursive(new ArrayList<Integer>(), answer, nums, 0);
            return answer;
        }
    
        private void recursive(List<Integer> temp, List<List<Integer>> result, int[] nums, int start) {
            result.add(new ArrayList<Integer>(temp));
            
            if (temp.size() == nums.length) {
                return;
            }
    
            for (int i = start; i < nums.length; i++) {
                temp.add(nums[i]);
                recursive(temp, result, nums, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }
    ```

---

## 🥰 배운점 & 느낀점

- 부분 집합은 다른 친구들에 비해 좀 까다롭다.
- 조합 코드와 비슷한듯 하지만 `start`를 활용해야햔다.