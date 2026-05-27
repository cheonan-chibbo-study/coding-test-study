## 👀 제한 시간 안에 어디까지 해냈는가?

문제를 보자마자 어떻게 풀어야 할 지 감이 왔지만 파이썬 문법이 미숙해서 코드 작성을 혼자 하지 못했다. 결국 GPT를 활용해 미숙한 문법을 찾아 풀이 코드를 작성했다.

P & J 트레이닝

- 문자열 학습을 위해 이 문제는 P & J 모두 시간 측정을 하지 않고 풀었다.
- 이 문제에서 얻은 문자열 관련 테크닉을 정리했다.

---

## 🧑‍🔬 문제 분석

문자열과 조건이 주어지며 입력 문자열을 문제에서 제시하는 조건에 맞춰 가공 후 반환하는 문제이다. 총 7단계의 가공 조건을 걸고 있으며 각 단계마다 크게 어려운 부분이 없다. 그냥 순수 문자열 가공 문제이다.

- 자세한 단계 조건은 문제를 참고하자.

문제에 주어지는 제약 조건은 다음과 같다.

### **[제한사항]**

new_id는 길이 1 이상 1,000 이하인 문자열입니다.

new_id는 알파벳 대문자, 알파벳 소문자, 숫자, 특수문자로 구성되어 있습니다.

new_id에 나타날 수 있는 특수문자는 `-_.~!@#$%^&*()=+[{]}:?,<>/` 로 한정됩니다.

---

## 🤔 풀이 고민

문제에 주어지는 각 단계별 문자열 가공을 구현하면 되는 문제이다.

주어지는 문자열의 길이도 최대 길이가 1,000으로 매우 작기 때문에 각 단계마다 N^2으로 구현해도 문제를 해결할 수 있다.

### 결론

- 순수 문자열 가공 문제이다.

---

## 🏃 코드 작성 과정

### 구현하지 못한 부분 구현

풀이는 간단하지만 파이썬 문법이 미숙해서 혼자 코드를 작성하지 못했다. 아래 풀이 코드를 복습하면서 문자열 관련 함수와 테크닉을 연습하고 체화할 필요가 있다.

```python
def solution(new_id):
    # 메인 로직
    
    # 1단계 (문자를 모두 소문자로 변환)
    step1 = new_id.lower()  
    
    # 2단계 (주어진 문자열에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거한다.)
    allowed = {'-', '_', '.'}
    step2 = ''.join(
        c for c in step1
        if c.islower() or c.isdigit() or c in allowed
    )
    
    # 3단계 (주어진 문자열에서 처음과 끝에 위치한 마침표(.)를 제거한다.)
    step3 = step2
    while '..' in step3:
        step3 = step3.replace('..', '.')
        
    # 4단계 (주어진 문자열에서 처음과 끝에 위치한 마침표(.)를 제거한다.)
    step4 = step3.strip('.')
    
    # 5단계 (주어진 문자열이 빈 문자열이라면, "a"를 대입한다.)
    step5 = step4 if step4 else "a"
    
    # 6단계 (주어진 문자열의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거한다.)
    step6 = step5[:15].rstrip('.')
    
    # 7단계 (주어진 문자열의 길이가 2자 이하라면, 마지막 문자를 문자열의 길이가 3이 될 때까지 반복해서 끝에 붙인다.)
    step7 = step6
    while len(step7) <= 2:
        step7 += step7[-1]
    
    return step7
```

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01

    ```python
    def solution(new_id):
        # step 01 (문자를 모두 소문자로 변환)
        step1 = new_id.lower()
        
        # step 02 (주어진 문자열에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거한다.)
        allowed = {'-', '_', '.'}
        step2 = ''.join(
            c for c in step1
            if c.islower() or c.isdigit() or c in allowed
        )
        
        # step 03 (주어진 문자열에서 처음과 끝에 위치한 마침표(.)를 제거한다.)
        step3 = step2
        while '..' in step3:
            step3 = step3.replace('..', '.')
        
        # step 04 (주어진 문자열에서 처음과 끝에 위치한 마침표(.)를 제거한다.)
        step4 = step3.strip('.')
        
        # step 05 (주어진 문자열이 빈 문자열이라면, "a"를 대입한다.)
        step5 = step4 if step4 else "a"
        
        # step 06 (주어진 문자열의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거한다.)
        step6 = step5[:15].rstrip('.')
        
        # step 07 (주어진 문자열의 길이가 2자 이하라면, 마지막 문자를 문자열의 길이가 3이 될 때까지 반복해서 끝에 붙인다.)
        step7 = step6
        while len(step7) <= 2:
            step7 += step7[-1]
        
        return step7
    ```


### Java 풀이

- solution01

    ```java
    import java.util.*;
    
    class Solution {
        public String solution(String new_id) {
            StringBuilder sb;
            
            // step 01 (문자를 모두 소문자로 변환)
            String step1 = new_id.toLowerCase();
    
            // step 02 (주어진 문자열에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거한다.)
            Set<Character> allowed = Set.of('-', '_', '.');
            sb = new StringBuilder();
            
            for (char ch : step1.toCharArray()) {
                if (
                    Character.isLowerCase(ch) ||
                    Character.isDigit(ch) ||
                    allowed.contains(ch)
                ) {
                    sb.append(ch);
                }
            }
            
            String step2 = sb.toString();
    
            // step 03 (주어진 문자열에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환한다.)
            String step3 = step2;
            
            while (step3.contains("..")) {
                step3 = step3.replace("..", ".");
            }
            
    
            // step 04 (주어진 문자열에서 처음과 끝에 위치한 마침표(.)를 제거한다.)
            String step4 = step3;
            
            while (!step4.isEmpty() && step4.charAt(0) == '.') {
                step4 = step4.substring(1);
            }
            
            while (!step4.isEmpty() && step4.charAt(step4.length() - 1) == '.') {
                step4 = step4.substring(0, step4.length() - 1);
            }
    
            // step 05 (주어진 문자열이 빈 문자열이라면, "a"를 대입한다.)
            String step5 = !step4.isEmpty() ? step4 : "a";
    
            // step 06 (주어진 문자열의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거한다. 만약 제거 후 마침표(.)가 끝에 위치한다면 끝의 마침표(.)를 제거한다.)
            String step6 = step5;
            if (step6.length() > 15) {
                step6 = step6.substring(0, 15);
            }
            
            while (!step6.isEmpty() && step6.charAt(step6.length() - 1) == '.') {
                step6 = step6.substring(0, step6.length() - 1);
            }
    
            // step 07 (주어진 문자열의 길이가 2자 이하라면, 마지막 문자를 문자열의 길이가 3이 될 때까지 반복해서 끝에 붙인다.)
            String step7 = step6;
            
            while (step7.length() <= 2) {
                step7 += step7.charAt(step7.length() - 1);
            }
            
            return step7;
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- 문제 자체는 쉽지만 파이썬 문법이 미숙해 혼자 코드를 작성하지 못한게 많이 아쉽다. 이런 문자열 가공 테크닉은 암기가 필요한 부분이기 때문에 이 문제를 교보재로 삼아 관련 테크닉을 체화시킬 필요가 있다.