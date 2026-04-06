## 👀 제한 시간 안에 어디까지 해냈는가?

제한 시간 `30분` + 추가 시간 `4분 4초`만에 문제를 풀었지만… 중간에 코드 구현이 막혀 몇몇 부분을 서치하면서 풀 수 있었다.

---

## 🧑‍🔬 문제 분석

정수가 문자열 타입으로 주어졌을 때 그 정수를 구성하는 각 숫자들을 조합해 만들 수 있는 모든 정수들 중 소수의 개수를 구해 반환하는 문제이다.

문제에 주어진 제약 조건은 다음과 같다.

### 제한사항

- numbers는 길이 1 이상 7 이하인 문자열입니다.
- numbers는 0~9까지 숫자만으로 이루어져 있습니다.
- "013"은 0, 1, 3 숫자가 적힌 종이 조각이 흩어져있다는 의미입니다.

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

문제 풀이를 떠올리는건 매우 간단했다. 주어지는 정수 문자열의 길이가 최대 17로 매우 작기 때문에 아래 풀이를 활용해도 문제를 해결할 수 있다고 판단했다.

- 주어진 문자열 형태의 정수를 기반으로 만들 수 있는 모든 부분 정수를 구한다.
- 위에서 구한 부분 정수를 모두 탐색해서 소수를 찾아 카운팅 후 반환한다.

결론적으로 내가 생각한 풀이 자체는 문제를 해결할 수 있었지만 이 풀이를 코드로 구현하는 과정에서 실수가 좀 많아 시간이 많이 걸렸다.

### 결론

내가 생각한 풀이로 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 내가 처음 작성한 코드

풀이 자체는 금방 떠올렸지만 코드 작성에서 많은 이슈가 있었다. 결국 어찌저찌해서 다음과 같은 코드를 작성했다.

```python
def solution(numbers):
    # 메서드
    def recursive(temp, visited):
        if temp:
            candi_set.add(int("".join(temp)))
    
        for i in range(len(num_list)):
            if visited[i]:
                continue
            
            temp.append(num_list[i])
            visited[i] = True
            recursive(temp, visited)
            
            temp.pop()
            visited[i] = False
    
    def is_target(num):
        if num == 0 or num == 1:
            return False
        
        for i in range(2, num):
            if num % i == 0:
                return False
        
        return True
            
    # 메인 로직
    num_list = sorted(list(numbers))
    candi_set = set()
    recursive([], [False] * len(num_list))
    
    answer = 0
    for candi in candi_set:
        if is_target(candi):
            answer += 1
    
    return answer
```

이 코드는 문제를 해결하는 코드이지만 정말 빡센 완탐 풀이이기 때문에 실행 시간이 좀 소요된다.

### 시간 복잡도 최적화

내가 처음 작성한 코드는 소수 판별에서 특히나 시간이 오래 걸린다. 내가 작성한 코드를 시간 복잡도상으로 개선하기위해 제미나이에게 부탁했고 다음과 같은 풀이 코드를 받았다.

```python
from itertools import permutations

def solution(numbers):
    # 메서드
    def get_candi_set():
        result = set()
        for i in range(1, len(numbers) + 1):
            for p in permutations(numbers, i):
                result.add(int("".join(p)))
        
        return result
    
    def is_target(num):
        if num < 2:
            return False
        
        for i in range(2, int(num ** 0.5) + 1):
            if num % i == 0:
                return False
        
        return True
    
    # 메인 로직
    candi_set = get_candi_set()
    print(candi_set)
    
    answer = 0
    for candi in candi_set:
        if is_target(candi):
            answer += 1
    
    return answer
```

- 소수 판별 로직을 2 ~ x까지 검사하는 방식이 아니라 정수 x에 대한 제곱근(Square Root)까지만 계산하는 방식으로 개선되었다.
- candi를 구하는 과정을 속도가 빠른 `permutations` 내장 함수를 사용하도록 개선되었다.

---

## 🥰 배운점 & 느낀점

- 솔직히 구현이 어려운 문제도 아닌데 해매서 기분이 좀 좋지 않았다. 몇일 코테를 쉬웠더니 이런 참사가 난 거 같아서 반성하게 되었다… 다시 빡세게 해야지…