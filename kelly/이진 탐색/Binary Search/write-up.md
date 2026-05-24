## 👀 제한 시간 안에 어디까지 해냈는가?

P & J 트레이닝

- Python으로 1차 시도를 하여 `5분 8초`만에 문제를 해결했다.
- Java로 2차 시도를 하여 `1분 43초`만에 문제를 해결했다.

---

## 🧑‍🔬 문제 분석

정수 배열과 target이 주어질 때 주어진 정수 배열에서 target의 인덱스를 찾아 반환하는 문제이다. 만약 target이 존재하지 않는다면 -1을 반환한다.

- 참고로 `O(log n)`의 시간 복잡도 풀이를 문제 지문에서 강제하고 있다.

문제에 주어진 제약 조건은 다음과 같다.

**Constraints:**

- `1 <= nums.length <= 104`
- `104 < nums[i], target < 104`
- All the integers in `nums` are **unique**.
- `nums` is sorted in ascending order.

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

전형적인 이진 탐색 연습 문제이다. 이미 문제에서 지문으로 `O(log n)` 풀이를 강제하고 있기 때문에 배열 요소를 처음부터 모두 순회하는 완전 탐색으로는 문제를 풀 수 없다. 따라서 `O(log n)` 의 시간 복잡도가 소요되는 `이진 탐색`을 활용한 풀이를 작성하면 문제를 해결할 수 있다.

### 결론

- 이진 탐색을 활용한 풀이를 작성하면 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 내가 처음 작성한 코드

혼자서 쉽게 코드를 작성했다. 아래 최종 정답 코드를 참고하자.

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01

    ```python
    class Solution:
        def search(self, nums: List[int], target: int) -> int:
            left = 0
            right = len(nums) - 1
    
            while left <= right:
                mid = (left + right) // 2
    
                if nums[mid] == target:
                    return mid
                elif nums[mid] < target:
                    left = mid + 1
                else:
                    right = mid - 1
            
            return -1
    ```


### Java 풀이

- solution01

    ```java
    class Solution {
        public int search(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;
    
            while (left <= right) {
                int mid = (left + right) / 2;
    
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
    
            return -1;
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- 쉬운 이진 탐색 연습 문제였다.