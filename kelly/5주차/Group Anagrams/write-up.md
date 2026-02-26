## 👀 제한 시간 안에 어디까지 해냈는가?

`13분 58초`만에 혼자서 아이디어를 떠올려 문제를 해결할 수 있었다. 하지만 파이썬 문법 미숙 문제로 코드 구현에 어려움이 있어 GPT의 도움으로 코드를 작성했다. 아래 코드는 최종 정답 처리를 받는다.

```python
from collections import defaultdict

class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        # 메인 로직
        groups = defaultdict(list)

        for s in strs:
            groups[tuple(sorted(s))].append(s)
        
        return list(groups.values())
```

---

## 🧑‍🔬 문제 분석

문자열 리스트가 주어졌을 때 애너그램인 문자열 그룹을 찾아 리스트로 반환해야하는 문제이다.

문제에 주어진 조건은 다음과 같다.

**Constraints:**

- `1 <= strs.length <= 104`
- `0 <= strs[i].length <= 100`
- `strs[i]` consists of lowercase English letters.

---

## 🤔 풀이 고민

떠올린 아이디어는 다음과 같다.

- 입력으로 주어진 문자열 리스트를 순회해서 문자열을 하나씩 꺼낸 후 그 문자열의 요소를 분리한 튜플을 만들어 딕셔너리에 키로 설정 후 값 리스트에 해당 문자열을 삽입한다.
- 위 과정을 반복 후 마지막에 딕셔너리에 쌓인 값들을 리스트로 변환해 반환한다.

이 방식을 사용할 경우 시간 복잡도는 다음과 같이 계산해볼 수 있다.

- GPT 계산

  ## 🔎 단계별 시간 복잡도 분석

  ### 1️⃣ 반복문

    ```
    for s in strs:
    ```

    - 총 n번 반복
    - 시간 복잡도: O(n)

    ---

  ### 2️⃣ sorted(s)

    ```
    sorted(s)
    ```

    - 문자열 길이 = k
    - 정렬 시간 복잡도 → O(k log k)

    ---

  ### 3️⃣ tuple(...)

    ```
    tuple(sorted(s))
    ```

    - 길이 k만큼 복사
    - 시간 복잡도 → O(k)

    ---

  ### 4️⃣ 딕셔너리 삽입

    ```
    groups[key].append(s)
    ```

    - 평균 시간 복잡도 → O(1)

    ---

  ## 🔥 한 문자열 처리 비용

    ```
    O(k log k) + O(k) + O(1)
    ≈ O(k log k)
    ```
    
  ---

  ## 🔥 전체 시간 복잡도

    ```
    O(n × k log k)
    ```
    
  ---

  ## 📌 제약 조건 대입

    - n ≤ 10⁴
    - k ≤ 100

      k log k ≈ 100 × log(100)
      ≈ 100 × 7
      ≈ 700

      최대 연산 수 ≈ 10⁴ × 700
      ≈ 7,000,000


    → 충분히 통과 가능한 수준


### 결론

- 내가 떠올린 아이디어로 문제를 충분히 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 구현하지 못한 부분 구현

아이디어는 떠올렸지만 파이썬 문법 미숙과 실수로 혼자서 정답 코드를 작성하지는 못했다. 어려웠던 부분과 실수했던 부분을 아래 기록하겠다.

```python
from collections import defaultdict

class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        # 메인 로직
        groups = defaultdict(list)

        for s in strs:
            groups[tuple(sorted(s))].append(s)
        
        return list(groups.values())

```

- 문자열의 각 요소를 분리해 tuple로 만들고 싶다면 `tuple(s)` 이렇게 하면 된다.
- 튜플은 불변이기 때문에 tuple.sort()는 할 수 없지만 정렬된 리스트로 변환 후 다시 튜플로 변환하는 방식으로 정렬할 수 있다.
    - `new_tuple = tuple(sorted(original))`
    - 참고로 sorted에 문자열을 넣으면 각 요소를 분리해 리스트로 만든 후 정렬해서 반환해준다. 만약 문자열 형태를 유지한채로 정렬하고 싶다면 `“”.join(sorted(s))`
- 딕셔너리 키로 list는 설정할 수 없지만 tuple은 설정할 수 있다. 딕셔너리 키 조건은 다음과 같다.
    1. **hashable 해야 하고**
        - hashable은 hash 값이 변하지 않는 성질이다.
        - 1, “1” 같은 리터럴은 hash가 변할일이 없지만 [1, 2, 3] 같은 리스트는 내부 요소의 변화에 따라 hash 값이 변할 수 있어 hashable하지 못하다.
        - tupe(1, 2, 3)은 hashable하지만 tuple(1, 2, [1, 2, 3])은 hashable하지 않는다. 따라서 전자는 딕셔너리 키로 설정할 수 있지만 후자는 불가능하다.
    2. **immutable(불변)해야한다.**
        1. tuple은 불변하다.
- 같은 요소를 가진 튜플이라도 그 요소의 정렬 순서가 다르면 다른 튜플로 취급한다. 따라서 이 문제에 경우 튜플을 정렬시켜서 딕셔너리 키로 설정하고 접근해야한다.
- 딕셔너리의 값들을 리스트로 만들고 싶다면 다음과 같이 하면 된다.
    - `list(dict.values())`
    - `dict.values()` 는 이터러블 객체이지, list가 아니라서 변환이 한 번 필요하다.

---

## 🥰 배운점 & 느낀점

- 이번에는 아이디어를 직접 떠올릴 수 있었지만 문법 미숙과 실수로 정답 코드까지 혼자 작성하지 못한게 많이 아쉬웠다.
- 이런 실수를 하지 않도록 문법 복습을 열심히 해야겠다.