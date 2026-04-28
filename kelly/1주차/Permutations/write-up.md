## 👀 제한 시간 안에 어디까지 해냈는가?

`11분 31초`만에 혼자서 문제를 해결했다.

---

## 🧑‍🔬 문제 분석

정수 리스트가 주어질 때 이 정수 리스트 요소를 사용해 만들 수 있는 모든 정수 리스트 길이의 순열을 구해 반환하는 문제이다.

문제에 주어지는 제약 조건은 다음과 같다.

**Constraints:**

- `1 <= nums.length <= 6`
- `10 <= nums[i] <= 10`
- All the integers of `nums` are **unique**.

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

주어진 입력의 크기가 매우 적기 때문에 재귀를 활용해서 문제를 해결할 수 있다. 만약 라이브러리 사용이 가능하다면 파이썬은 `from itertools import permutaions` 모듈을 활용할 수 있다.

### 결론

- 내가 처음 생각한 풀이로 코드를 작성하면 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 내가 처음 작성한 코드

**`[ Python ]`**

```python
class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        # 메서드
        def recursive(temp, result):
            if len(temp) == len(nums):
                result.append(temp[::])
                return

            for n in nums:
                if n in temp:
                    continue
                
                temp.append(n)
                recursive(temp, result)
                temp.pop()
                
        # 메인 로직
        answer = []
        recursive([], answer)

        return answer
```

**`[ Java ]`**

```java
import java.util.*;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        recursive(new ArrayList<Integer>(), answer, nums);

        return answer;
    }

    private void recursive(List<Integer> temp, List<List<Integer>> result, int[] nums) {
        if (temp.size() == nums.length) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (temp.contains(nums[i])) {
                continue;
            }

            temp.add(nums[i]);
            recursive(temp, result, nums);
            temp.remove(temp.size() - 1);
        }
    }
}
```

---

## 🥰 배운점 & 느낀점

- 자바 & 파이썬을 동시에 해보려고 하니까 어렵다.. 익숙해 져야지 그래도…
- 파이썬에 경우 모듈만 사용하면 코드 구현을 까먹는다.. 순열, 부분 집합, 조합을 헷갈리지 않도록 주의하자.