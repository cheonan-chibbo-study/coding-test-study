## 👀 제한 시간 안에 어디까지 해냈는가?

이번 문제는 아예 풀이법을 떠올리지 못했다. 시간이 10분 지났을 무렵 어차피 이 문제는 30분동안 붙잡고 있어도 풀지 못할거라 생각해서 바로 풀이를 찾아봤다.

---

## 🧑‍🔬 문제 분석

숫자가 오름차순으로 정렬되어 있는 리스트들을 요소로 가지는 nums 배열이 주어졌을 때, 문제에서 제시하는 조건을 만족하는 최소 크기의 정수 범위를 반환하는 문제이다.

- 자세한 문제 조건은 문제를 참고하자.

문제에 주어진 제약 조건은 다음과 같다.

**Constraints:**

- `nums.length == k`
- `1 <= k <= 3500`
- `1 <= nums[i].length <= 50`
- `105 <= nums[i][j] <= 105`
- `nums[i]` is sorted in **non-decreasing** order.

---

## 🤔 풀이 고민

이 문제는 풀이법을 아예 떠올리지 못해서 빠르게 포기하고 풀이법을 찾아봤다. 결론적으로 이 문제는 우선순위 큐와 최소 힙, 그리고 약간의 아이디어를 사용하여 해결할 수 있는 문제이다.

문제를 푸는 풀이는 다음과 같다.

1. 우선 각 리스트의 첫 번째 요소 값을 모두 최소 힙으로 만든 우선순위 큐에 삽입한다. 그리고 이 과정에서 최소, 최대 값을 기록한다.
2. 1번 작업에서 기록한 최대, 최소 값의 차를 구해서 초기 smallest_range로 등록한다.
3. 이제 반복문을 실행하는데 반복문 내부에서는 다음과 같은 작업을 수행한다.
    1. pq를 이용해 가장 작은 값을 pop한다.
    2. pop한 데이터가 속한 리스트에서 pop한 요소의 바로 다음 요소를 찾아 pq에 삽입한다. 만약 pop한 요소가 해당 리스트의 마지막 요소라면 반복문을 종료한다.
    3. 발견한 다음 요소를 가지고 max_value를 업데이트한 후 업데이트한 max_value에서 현재 pq에서 가장 작은 값을 뺀 값으로 smallest_range를 업데이트한다.
    4. 반복문이 종료되면 기록해논 데이터를 바탕으로 정답 범위를 찾아 반환한다.

이 문제에서 요구하는건 모든 리스트의 데이터가 최소 1개는 포함되면서, 가장 짧은 길이의 범위를 찾는게 관건이다. 여기서 특정 범위의 크기를 줄이는 방법은 현재로써는 범위에서 가장 작은 데이터를 더 큰 데이터로 변경하는 것이다.

- [1, 5, 8] 리스트는 범위 크기가 7인데, 여기서 범위를 더 줄이기 위해서는 해당 리스트에서 가장 작은 데이터인 1을 더 큰 수로 변경해야한다. 적어도 1을 2로 변경하기만 해도 범위가 7에서 6으로 감소한다.

이 아이디어를 우선순위 큐와 최소 힙을 활용해서 코드로 구현하면 된다.

참고로 위 아이디어를 사용해 코드를 작성할 경우 시간 복잡도는 다음과 같으며, 충분히 1초안에 실행될 수 있는 시간 복잡도다.

- (3,500(k의 최대 값) * 50(각 리스트의 최대 크기) * log[2, 3,500]) → **`2,060,299`**

### 결론

- 위에 기록한 아이디어를 최소힙으로 구현한 우선순위 큐를 활용해 코드를 작성하면 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 구현하지 못한 부분 구현

위에 적은 아이디어를 코드로 구현하면 다음과 같다. 이 코드는 최종 정답 처리를 받는다.

```python
from heapq import heapify, heappush, heappop

class Solution:
    def smallestRange(self, nums: List[List[int]]) -> List[int]:
        # 전역 데이터
        k = len(nums)

        # 메인 로직
        min_value = float('inf')
        max_value = float('-inf')
        pq = []
        for i in range(k):
            target = nums[i][0]
            min_value = min(min_value, target)
            max_value = max(max_value, target)
            heappush(pq, (target, i))

        answer = [min_value, max_value]
        smallest_range = max_value - min_value

        list_indexes = [0] * k
        while True:
            value, idx = heappop(pq)
            list_indexes[idx] += 1

            if list_indexes[idx] == len(nums[idx]):
                break
            
            target = nums[idx][list_indexes[idx]]
            heappush(pq, (target, idx))

            min_value = pq[0][0]
            max_value = max(max_value, target)
            new_range = max_value - min_value

            if new_range < smallest_range:
                smallest_range = new_range
                answer = [min_value, max_value]

        return answer
```

---

## 🥰 배운점 & 느낀점

- 막상 문제 아이디어를 접하니 생각보다 쉬워서 놀랐다. 이런 아이디어 문제는 결국 많은 케이스를 접하고 기록해서 체화하는게 답인거 같다.
- 이번에 새로 배운 아이디어니 잘 기억했다 비슷한 유형의 문제를 만나면 꼭 써먹어야겠다.