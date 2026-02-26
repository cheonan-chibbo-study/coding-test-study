## 👀 제한 시간 안에 어디까지 해냈는가?

30분안에 풀지 못했다. 코드를 거의 작성했는데 아웃풋 하나가 다르게 나오는 에러를 30분안에 해결하지 못했다. 당시 코드는 아래와 같다.

```python
class Solution:
    def dailyTemperatures(self, temperatures: List[int]) -> List[int]:
        # 전역 데이터
        T = 0
        D = 1

        # 메인 로직
        result = [0 for _ in temperatures]
        stack = []
        for d in range(len(temperatures)):
            t = temperatures[d]
            if not stack:
                stack.append([t, d])
            else:
                while stack:
                    if stack[-1][T] < t:
                        result[stack[-1][D]] = d - stack[-1][D] 
                        stack.pop()
                    else:
                        stack.append([t, d])
                        break
        
        return result

```

---

## 🧑‍🔬 문제 분석

온도 정보를 담은 리스트가 입력되면 각 날짜(인덱스)를 기준으로 현재보다 더 따뜻한 온도가 오기까지 몇일이 걸렸는지 정보들을 리스트로 반환하는 문제이다.

문제에 주어진 제약 조건은 다음과 같다.

**Constraints:**

- `1 <= temperatures.length <= 10^5`
- `30 <= temperatures[i] <= 100`

---

## 🤔 풀이 고민

### Stack 활용

문제를 보자마자 stack 풀이를 떠올렸다.

- stack이 비었다면 현재 온도/날짜 정보를 stack에 삽입
- stack이 비어있지 않다면 stack의 끝부터 현재 온도와 비교해서 현재 온도보다 낮다면 끝 데이터를 꺼내 `현재 날짜 - 꺼낸 데이터의 날짜` 값을 정답 배열에 삽입
    - 만약 끝 데이터의 온도가 더 높다면 현재 온도/날짜 데이터를 stack에 삽입

문제에 주어지는 온도 리스트의 최대 크기는 `10^5`이므로 stack 연산으로 충분히 풀 수 있을거란 확신이 들었다.

### 결론

- stack을 활용해 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 틀린 부분 정정

풀이법은 떠올렸지만 작성한 코드에 실수가 많아 기본 테스트 케이스가 모두 깨졌다. 결국 마지막 실수를 하나 해결하지 못하여 30분안에 문제를 풀지 못했다.

제한 시간동안 작성된 코드는 다음과 같다.

```python
class Solution:
    def dailyTemperatures(self, temperatures: List[int]) -> List[int]:
        # 전역 데이터
        T = 0
        D = 1

        # 메인 로직
        result = [0 for _ in temperatures]
        stack = []
        for d in range(len(temperatures)):
            t = temperatures[d]
            if not stack:
                stack.append([t, d])
            else:
                while stack:
                    if stack[-1][T] < t:
                        result[stack[-1][D]] = d - stack[-1][D] 
                        stack.pop()
                    else:
                        stack.append([t, d])  # 실수 부분
                        break
        
        return result

```

이 코드의 실수 부분은 `stack.append([t, d])` 로직을 while문 내부에 배치한 것이다. 만약 온도 비교 과정에서 모든 stack을 비웠다면 현재 온도/날짜 데이터를 stack에 삽입해야 하는데 이 로직이 while문 내부에 있어 stack이 비교로 인해 모두 비워질 경우 현재 온도/날짜 정보를 stack에 넣지 않고 그대로 넘어가게된다.

따라서 아래와 같이 stack에 현재 온도/날짜 데이터를 삽입하는 로직을 while문 밖에 배치하면 정답 처리가 된다.

```python
class Solution:
    def dailyTemperatures(self, temperatures: List[int]) -> List[int]:
        # 전역 데이터
        T = 0
        D = 1

        # 메인 로직
        result = [0 for _ in temperatures]
        stack = []
        for d in range(len(temperatures)):
            t = temperatures[d]
            if not stack:
                stack.append([t, d])
            else:
                while stack:
                    if stack[-1][T] < t:
                        result[stack[-1][D]] = d - stack[-1][D] 
                        stack.pop()
                    else:
                        break
                stack.append([t, d])
        
        return result
```

### 최종 정답 코드 개선

이 코드는 아래와 같이 더 깔끔하게 개선할 수 있다.

```python
class Solution:
    def dailyTemperatures(self, temperatures: List[int]) -> List[int]:
        result = [0] * len(temperatures)
        stack = []  # 날짜 index만 저장

        for d, t in enumerate(temperatures):
            while stack and temperatures[stack[-1]] < t:
                prev_day = stack.pop()
                result[prev_day] = d - prev_day
            stack.append(d)

        return result
```

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

---

## 🥰 배운점 & 느낀점

- 풀이 방법은 떠올렸는데 사소한 실수를 빨리 해결하지 못해 결국 시간도 많이 잡아먹고 문제를 풀지 못한게 너무 짜증나고 억울했다. 실제 코테에서는 이런 문제가 생기지 않도록 계속 제한 시간내 문제를 푸는 연습을 하면서 위기 대처 능력을 기를 필요가 있다.
- python의 여러 문법을 잘 활용하면 코드를 깔끔하게 개선할 수 있다.