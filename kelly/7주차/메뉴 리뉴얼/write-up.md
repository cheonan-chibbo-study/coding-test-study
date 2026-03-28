## 👀 제한 시간 안에 어디까지 해냈는가?

제한 시간 `30분` + 추가 시간 `15분`을 사용했지만 문제를 풀지 못했다.

최종 채점 케이스 대부분을 통과했지만 3개 케이스에서 시간 초과가 발생했고 그 시간 초과 문제를 해결하지 못했다.

아래는 마지막으로 작성한 코드이다.

```python
from itertools import combinations

def solution(orders, course):
    # 메서드
    def get_alpha():
        result = set()
        for o in orders:
            for item in o:
                result.add(item)
        
        return list(result)
    
    def make_combi(count, alpha):
        combi_list = []
        temp = combinations(alpha, count)
        for t in temp:
            target = sorted(t)
            combi_list.append(''.join(target))
        
        return combi_list
    
    def get_candi_count(candi):
        count = 0
        for o in orders:
            if set(candi).issubset(set(o)):
                count += 1
        
        return count
    
    # 메인 로직
    alpha = get_alpha()
    answer = []
    
    for c in course:
        result = []
        max_count = float('-inf')
        candidate = make_combi(c, alpha)
        for candi in candidate:
            candi_count = get_candi_count(candi)
            
            if candi_count > max_count:
                result = []
                result.append(candi)
                max_count = candi_count
            elif candi_count == max_count:
                result.append(candi)
        
        if max_count < 2:
            continue
            
        for item in result:
            answer.append(item)
    
    answer.sort()
    return answer
```

---

## 🧑‍🔬 문제 분석

손님들의 주문 내역을 참고하여 조건에 맞는 메뉴 구성을 만들어 반환하는 문제이다.

- 자세한 조건은 문제를 참고하자.

문제에 주어지는 제약 조건은 다음과 같다.

### **[제한사항]**

- orders 배열의 크기는 2 이상 20 이하입니다.
- orders 배열의 각 원소는 크기가 2 이상 10 이하인 문자열입니다.
    - 각 문자열은 알파벳 대문자로만 이루어져 있습니다.
    - 각 문자열에는 같은 알파벳이 중복해서 들어있지 않습니다.
- course 배열의 크기는 1 이상 10 이하입니다.
    - course 배열의 각 원소는 2 이상 10 이하인 자연수가 `오름차순`으로 정렬되어 있습니다.
    - course 배열에는 같은 값이 중복해서 들어있지 않습니다.
- 정답은 각 코스요리 메뉴의 구성을 문자열 형식으로 배열에 담아 사전 순으로 `오름차순` 정렬해서 return 해주세요.
    - 배열의 각 원소에 저장된 문자열 또한 알파벳 `오름차순`으로 정렬되어야 합니다.
    - 만약 가장 많이 함께 주문된 메뉴 구성이 여러 개라면, 모두 배열에 담아 return 하면 됩니다.
    - orders와 course 매개변수는 return 하는 배열의 길이가 1 이상이 되도록 주어집니다.

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

혼자서 생각한 풀이의 흐름은 다음과 같다.

1. 주문에 사용된 알파벳 목록 파싱
2. 코스 개수 만큼의 알파벳 조합을 매번 생성
3. 생성한 조합중 문제 조건에 일치하는 조합을 찾아 정답 배열에 쌓기
4. 정답 배열 반환

내가 생각한 방식으로도 문제에서 요구하는 출력을 만들 수는 있지만 일부 케이스에서 시간 초과가 발생하여 풀이를 찾아보았다.

### 찾아본 풀이

gemini에게 내 코드를 주고 어떤 부분이 시간 초과를 발생시키는지 물어봤다.

- gemini 답변

  # 🚀 프로그래머스 '메뉴 리뉴얼' 시간 초과 원인 분석 및 최적화

  ## 1. 🛑 기존 코드의 시간 초과 원인 (Problem)

  작성하신 코드의 핵심 문제는 **"발생 가능한 모든 알파벳 조합을 만든 뒤, 각 주문에 포함되는지 확인한다"**는 접근 방식에 있습니다.

    - **조합의 폭발적 증가 (`get_alpha` + `make_combi`)**
        - 전체 주문에 사용된 알파벳이 20개이고 `course` 크기가 10이라면, $*{20}C*{10}$은 **184,756개**의 후보군을 생성합니다.
        - 이 모든 후보군을 `orders`와 일일이 대조하는 과정에서 연산량이 기하급수적으로 늘어납니다.
    - **불필요한 후보군 탐색**
        - 문제 조건은 "최소 2명 이상의 손님이 주문한 조합"입니다. 즉, 어떤 손님도 주문하지 않은 알파벳 조합(예: 사용되지 않은 조합)까지 검사할 필요가 없습니다.
    - **반복적인 자료형 변환 (`set(o)`)**
        - `get_candi_count` 함수 내부에서 `set(o)`를 매번 생성하고 있습니다. 주문 개수만큼 매번 세트를 만드는 과정은 실행 시간을 크게 잡아먹는 오버헤드입니다.

    ---

  ## 2. ✅ 최적화 전략 (Solution)

  전체 알파벳이 아닌, **각 손님이 실제 주문한 메뉴(`order`) 내에서만 조합을 추출**해야 합니다.

    1. **실제 주문 기반 조합 생성:** 각 `order`를 정렬한 뒤, 해당 주문 내에서 `course` 길이에 맞는 조합만 뽑습니다.
    2. **계산 효율화:** 모든 주문에서 나온 조합들을 하나의 리스트에 모은 뒤, `Counter`를 이용해 빈도수를 한 번에 계산합니다.
    3. **결과 도출:** 빈도수가 2 이상이며 해당 `course` 크기에서 가장 많이 등장한 조합을 결과에 추가합니다.

    ---

  ## 3. 💻 개선된 코드 (Python)

    ```python
    from itertools import combinations
    from collections import Counter
    
    def solution(orders, course):
        answer = []
    
        for size in course:
            candidates = []
            for order in orders:
                # 1. 각 주문을 정렬한 후, 해당 크기(size)의 조합 생성
                # (정렬을 해야 "AB", "BA"가 동일한 "AB"로 취급됨)
                for combi in combinations(sorted(order), size):
                    candidates.append(''.join(combi))
    
            # 2. 생성된 모든 조합의 빈도수를 한 번에 계산
            counter = Counter(candidates)
    
            # 3. 최다 빈도수 확인 (최소 2번 이상 주문된 경우만)
            if counter and max(counter.values()) >= 2:
                max_val = max(counter.values())
                for menu, count in counter.items():
                    if count == max_val:
                        answer.append(menu)
    
        # 최종 결과를 사전순으로 정렬하여 반환
        return sorted(answer)
    ```
    
  ---

  ## 4. 💡 핵심 요약

    - **AS-IS (기존 방식):** "모든 가능한 알파벳 조합 생성" → "각 주문마다 포함 여부 확인" (데이터가 커질수록 기하급수적으로 느려짐 🐢)
    - **TO-BE (개선 방식):** "실제 주문 내에서만 조합 추출" → "Counter로 빈도수 한 번에 카운트" (불필요한 연산 제거로 매우 빠름 🚀)

### 결론

- 처음 내가 생각한 아이디어로 코드를 작성하면 출력 정확도는 지킬 수 있다.
- 하지만 너무 많은 조합 발생 + 정답에 포함될 여지가 없는 조합까지 검사하므로 연산 횟수가 많아져서 시간 초과가 발생한다.

---

## 🏃 코드 작성 과정

### 찾아본 풀이로 작성한 코드

gemini가 작성해준 코드가 간결하고 효율적이라서 이 코드를 베이스로 학습하면 좋을거 같다.

```python
from itertools import combinations
from collections import Counter

def solution(orders, course):
    # 메인 로직
    answer = []
    new_orders = []
    for order in orders:
        new_orders.append(sorted(order))
    
    for size in course:
        candies = []
        for order in new_orders:
            # 1. 각 주문을 정렬한 뒤 해당 크기의 조합을 생성
            # 정렬을 미리 해야 "AB", "BA"가 같은 것으로 처리됨
            for combi in combinations(order, size):
                candies.append(''.join(combi))
        
        # 2. 해당 크기의 모든 조합의 빈도수 계산
        counter = Counter(candies)
        
        # 3. 가장 많이 주문된 횟수 확인 (최소 2번 이상)
        if len(counter) != 0 and max(counter.values()) >= 2:
            max_value = max(counter.values())
            for menu, count in counter.items():
                if count == max_value:
                    answer.append(menu)
    
    return sorted(answer)
```

- Counter는 특정 리스트에 대해 각 요소값 개수를 담은 딕셔너리를 반환해준다.
- 문자열에 대해 sorted()를 하면 각 문자열 요소를 정렬한 리스트가 반환된다.

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

---

## 🥰 배운점 & 느낀점

- 훨씬 코드도 간결하고 속도도 빠른것에 감탄했다. 알고리즘은 역시 많은 케이스를 접하면서 체화시키는게 정답인거 같다.
- 파이썬이 편하기는 진짜 편한거 같다….

---

## 👻 재시도 기록

### 재시도 1회