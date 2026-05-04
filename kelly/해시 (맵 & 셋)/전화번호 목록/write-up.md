## 👀 제한 시간 안에 어디까지 해냈는가?

`11분 47초`만에 문제를 해결했다.

P & J 트레이닝

- Java로 처음 시도 했는데 최종 채점에서 효율성 체크 하나에 `시간 초과`가 발생했다.
- Python을 활용해 Java와 같은 풀이로 2차 풀이를 진행했고 `2분 11초`만에 문제를 해결했다.

---

## 🧑‍🔬 문제 분석

전화번호 목록 리스트가 주어질 때 하나의 번호가 다른 번호의 접두사인지 여부를 찾아 반환하는 문제이다.

문제에 주어진 제약 조건은 다음과 같다.

### 제한 사항

- phone_book의 길이는 1 이상 1,000,000 이하입니다.
    - 각 전화번호의 길이는 1 이상 20 이하입니다.
    - 같은 전화번호가 중복해서 들어있지 않습니다.

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

주어지는 리스트 크기가 최대 100만이라 `N^2`의 시간 복잡도가 소요되는 풀이로는 문제를 해결할 수 없다. 따라서 `N`의 시간 복잡도가 걸리는 풀이를 고민하다 다음과 같은 풀이를 생각했다.

1. 딕셔너리를 정의한다.
2. 주어진 리스트를 순회하면서 각 요소의 첫 번째 문자부터 끝까지 누적한 값을 딕셔너리 키로 설정하여 개수를 카운팅한다.
3. 2에서 만든 딕셔너리를 순회하면서 개수가 2이상인 값이 있으면 False, 없으면 True를 반환한다.

위 풀이로 코드를 작성하면 최악의 경우 `100만 * 20`의 시간 복잡도가 소요되므로 충분히 이 풀이로 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 내가 처음 작성한 코드

```python
def solution(phone_book):
    num_dict = {}
    for p in phone_book:
        target = ""
        for n in list(p):
            target += n
            if target in num_dict:
                num_dict[target] += 1
            else:
                num_dict[target] = 1
    
    for p in phone_book:
        if num_dict[p] >= 2:
            return False
    
    return True
```

- `defaultdict` 사용도 고려했지만 사용했을 경우 효율성 테스트에서 최대 `400ms` 느린 성능을 보여서 사용하지 않는 방향으로 코드를 작성했다.

### P & J 트레이닝 - 같은 풀이인데 Java만 시간 초과가 발생

파이썬으로 아래와 같이 코드를 작성해 제출하면 정답 처리를 받는다.

```python
def solution(phone_book):
    p_dict = {}
    
    for p in phone_book:
        target = ""
        for c in p:
            target += c
            p_dict[target] = p_dict.get(target, 0) + 1
    
    for p in phone_book:
        if p_dict[p] > 1:
            return False
    
    return True
```

하지만 완전히 같은 풀이를 Java로 작성해 제출하면 효율성 테스트에서 `시간 초과`가 발생한다.

```java
import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Map<String, Integer> map = new HashMap<>();

        for (String num : phone_book) {
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; i < num.length(); i++) {
                String target = sb.append(String.valueOf(num.charAt(i))).toString();
                map.put(target, map.getOrDefault(target, 0) + 1);
            }
        }

        for (String num : phone_book) {
            if (map.get(num) > 1) {
                return false;
            }
        }

        return true;
    }
}
```

![image.png](attachment:308a6af8-57e6-43c3-a9c9-86c10a65af43:image.png)

- 🤖 잼미니한테 왜 java는 시간 초과가 발생하는지 이유를 물어봤다.

  프로그래머스의 '전화번호 목록' 문제를 자바와 파이썬으로 동일한 로직으로 풀었음에도 자바에서만 시간 초과가 발생한 이유는 **String 조작 방식의 차이**와 **객체 생성 비용**에 있습니다.

  로직 자체는 $O(N \times L)$ (N: 전화번호 개수, L: 번호의 평균 길이)로 동일해 보이지만, 자바 코드 내부에서 매 루프마다 발생하는 작업들이 발목을 잡고 있습니다.
    
  ---

  ### 1. 시간 초과의 핵심 원인: `sb.append(...).toString()`

  자바 코드의 안쪽 루프를 보면 다음과 같은 부분이 있습니다.

  Java

  `String target = sb.append(String.valueOf(num.charAt(i))).toString();`

  이 한 줄에는 성능을 깎아먹는 세 가지 요소가 포함되어 있습니다.

    1. **`String.valueOf(char)`**: 매 문자마다 새로운 `String` 객체를 생성합니다.
    2. **`sb.append(...)`**: `StringBuilder`에 추가하는 작업 자체는 빠르지만, 위에서 만든 `String` 객체를 인자로 넘깁니다.
    3. **`toString()` (결정적 원인)**: **이 메서드는 호출될 때마다 내부 문자열을 복사하여 새로운 `String` 객체를 만듭니다.**
        - 번호의 길이가 20이라면, 하나의 번호를 처리할 때마다 20개의 새로운 `String` 객체가 생성되고 맵의 키로 사용됩니다.
        - 파이썬의 `target += c`는 내부적으로 최적화가 잘 되어 있으며, 자바에 비해 객체 생성 오버헤드가 상대적으로 적게 느껴질 수 있습니다.

  ### 2. 맵(Map)의 키 생성 오버헤드

  자바의 `HashMap`은 `put`이나 `get`을 할 때 키 객체의 `hashCode()`를 호출합니다.

    - 자바에서 `String.toString()`으로 생성된 수많은 문자열에 대해 매번 해시값을 계산하고 비교하는 과정이 추가됩니다.
    - 반면, 파이썬은 문자열 결합과 딕셔너리 해싱 과정이 C로 고도로 최적화되어 있어, 단순 루프 내 문자열 연산에서 자바보다 유연하게 대처하는 경우가 있습니다.

    ---

  ### 3. 해결 방법: 로직 최적화

  이 문제를 해결하려면 모든 접두어를 맵에 넣는 방식보다, **전화번호 자체만 맵에 넣고** 확인하는 방식을 사용해야 합니다.

  ### 수정된 Java 코드 (통과 예시)

  Java

  `import java.util.*;

  class Solution {
  public boolean solution(String[] phone_book) {
  Map<String, Integer> map = new HashMap<>();

            // 1. 모든 전화번호를 먼저 Map에 넣습니다. (접두어X, 전체 번호만)
            for (String num : phone_book) {
                map.put(num, 1);
            }
    
            // 2. 각 번호의 접두어가 Map에 존재하는지 확인합니다.
            for (String num : phone_book) {
                for (int i = 1; i < num.length(); i++) { // 자기 자신 제외 (i < length)
                    // substring은 내부적으로 문자열을 추출하지만 루프 횟수가 훨씬 줄어듭니다.
                    if (map.containsKey(num.substring(0, i))) {
                        return false;
                    }
                }
            }
    
            return true;
        }
  }`
    
  ---

  ## 📝 Blog Post: Java vs Python 문자열 연산 성능 차이

  알고리즘 문제를 풀 때 같은 로직임에도 자바에서 시간 초과가 난다면 **객체 생성**을 의심해야 합니다.

    1. **자바의 고정 관념:** `String`은 불변(Immutable) 객체입니다. `+` 연산이나 `toString()`은 매번 메모리에 새로운 공간을 할당합니다.
    2. **StringBuilder의 함정:** `StringBuilder`를 사용하더라도 루프 안에서 매번 `toString()`을 호출해 버리면 `String`을 직접 더하는 것과 다를 바 없는 비용이 발생합니다.
    3. **최적화 팁:** Map을 사용할 때는 가능한 한 **전체 집합을 먼저 구축**한 뒤, 필요한 부분만 잘라내어(`substring`) 대조하는 것이 객체 생성 횟수를 획기적으로 줄이는 방법입니다.

    ---

  ## 💬 Interview Response: 자바 성능 최적화 경험

  **Q. 동일한 로직의 파이썬 코드는 통과하는데, 자바 코드만 시간 초과가 발생한다면 어떻게 대처하시겠습니까?**

  **A.**

  자바의 **객체 할당 오버헤드**와 **GC(Garbage Collection) 부담**을 먼저 체크하겠습니다.

  특히 문자열을 다루는 문제라면 루프 내에서 `String` 객체가 불필요하게 생성되고 있지 않은지 확인해야 합니다. 질문하신 케이스처럼 `StringBuilder` 내부에서 매번 `toString()`을 호출하는 행위는 $O(L)$의 문자열 생성을 반복하게 하여 전체 성능을 저하시킵니다.

  이를 해결하기 위해 맵에는 원본 데이터만 저장하고, 비교 시에만 `substring`을 활용하거나 혹은 배열 정렬 후 인접 원소만 비교하는 방식으로 **객체 생성 횟수 자체를 최소화**하는 방향으로 리팩토링하여 성능을 개선할 수 있습니다.
    
  ---

  ### 💡 힌트

  파이썬 코드가 정답 처리를 받은 이유는 파이썬의 `target += c` 연산이 내부적으로 **In-place** 연산처럼 최적화되는 경우가 많고, 자바보다 동적 객체 관리에 유연하기 때문입니다. 하지만 자바는 타입과 객체 생성에 엄격하므로 더 세밀한 설계가 필요합니다.

- `String.valueOf(num.charAt(i))`, `sb.toString()`, `map.get()` or `map.put()` 과정에서 새로 생성된 String 객체에 대해 모두 해시값을 계산하고 비교하는 작업 등이 오버헤드가 커서 시간 초과가 발생된걸로 추측된다.
- 파이썬에 경우 이 작업들이 내부적으로 C 최적화가 매우 잘되어 있기 때문에 시간 초과가 발생하지 않은거 같다.

결국 java로 이 문제를 해결하기 위해서는 기존 코드보다 더 빠른 연산이 가능한 풀이로 변경해야한다. 아래 풀이는 map에 전화번호의 모든 문자를 넣는게 아닌, 전화번호 자체만 map에 넣고 `substring()`을 활용해 접두사가 존재하는지 검사하는 방식의 코드이다.

```java
import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Map<String, Integer> map = new HashMap<>();

        // 1. 모든 전화번호를 먼저 Map에 넣습니다. (접두어X, 전체 번호만)
        for (String num : phone_book) {
            map.put(num, 1);
        }

        // 2. 각 번호의 접두어가 Map에 존재하는지 확인합니다.
        for (String num : phone_book) {
            for (int i = 1; i < num.length(); i++) { // 자기 자신 제외 (i < length)
                // substring은 내부적으로 문자열을 추출하지만 루프 횟수가 훨씬 줄어듭니다.
                if (map.containsKey(num.substring(0, i))) {
                    return false;
                }
            }
        }

        return true;
    }
}
```

- 🤖 `substring()`이 어떤 메서드야? 그리고 왜 이 로직은 시간 초과가 발생하지 않아?

  `substring()`은 Java `String` 클래스에서 제공하는 **문자열의 일부분을 추출**하는 메서드입니다.

  제시된 로직이 이전 코드보다 훨씬 빠른 이유와 `substring()`의 동작 방식을 정리해 드릴게요.
    
  ---

  ### 1. `substring(int beginIndex, int endIndex)`란?

  문자열의 특정 범위를 잘라내어 새로운 문자열 객체를 반환합니다.

    - **beginIndex:** 시작 인덱스 (포함)
    - **endIndex:** 끝 인덱스 (**미포함**)
    - 예: `"12345".substring(0, 3)` → `"123"` 반환

    ---

  ### 2. 왜 이 로직은 시간 초과가 발생하지 않나요?

  이전 코드와 현재 코드 모두 $O(N \times L)$의 시간 복잡도를 가지지만, **상수 시간(Constant Time)** 효율성에서 엄청난 차이가 납니다.

  ### ① 맵 작업(Put vs Contains)의 횟수 차이

    - **이전 코드:** 모든 전화번호의 모든 접두어를 전부 `Map`에 삽입(`put`)했습니다. `put`은 새로운 메모리를 할당하고 해시 맵의 상태를 변경하는 무거운 작업입니다.
    - **현재 코드:** `Map`에는 딱 `phone_book`의 개수만큼만 데이터를 넣습니다. 그 후에는 조회(`containsKey`)만 수행합니다. 일반적으로 `put`보다 `get/containsKey`가 훨씬 가볍습니다.

  ### ② 문자열 생성 방식의 차이

    - **이전 코드:** `StringBuilder`를 쓰고 매 루프마다 `.toString()`을 호출했습니다. 이는 내부적으로 새로운 `String` 객체를 계속 생성하며 문자 배열을 복사하는 과정을 수반합니다.
    - **현재 코드:** `substring()`도 새로운 문자열을 만들긴 하지만, 이전 코드처럼 `String.valueOf(char)`와 `StringBuilder` 연산을 거치지 않고 바로 내부 배열을 복사하여 생성하므로 오버헤드가 적습니다.

  ### ③ 데이터 비교 대상의 최소화

    - 이전 코드는 "모든 가능한 접두어"를 다 맵에 저장해서 맵의 크기가 매우 커졌습니다.
    - 현재 코드는 맵의 크기를 전화번호의 개수($N$)로 제한하고, 각 번호를 잘라가며 맵에 그 "완성된 번호"가 있는지만 체크합니다. 맵에 들어있는 데이터 자체가 적으니 해시 충돌 가능성도 낮아지고 검색 속도가 빨라집니다.

    ---

  ## 📝 Blog Post: Java String substring()과 효율적인 알고리즘

  문자열 검색 문제에서 성능을 결정짓는 핵심은 "얼마나 적은 객체를 생성하느냐"입니다.

  ### substring() 활용 팁

  Java

  `String phone = "01012345678";
  String prefix = phone.substring(0, 3); // "010"`

    - `substring`은 새로운 객체를 생성하므로 남발하면 안 되지만, 불필요한 `StringBuilder.append().toString()` 보다는 훨씬 직관적이고 빠릅니다.
    - 특히 **"전체 집합을 맵에 넣어두고 부분 문자열로 조회하기"** 패턴은 접두어 문제를 풀 때 가장 효율적인 접근법 중 하나입니다.

    ---

  ## 💬 Interview Response: 문자열 문제의 성능 최적화

  **Q. Java에서 문자열 접두어 검색 기능을 구현할 때 주의할 점은 무엇인가요?**

  **A.**

  가장 주의할 점은 **불필요한 문자열 객체 생성**입니다.

  처음에 시도했던 방식처럼 모든 접두어를 생성하여 `put` 하는 방식은 `Map`의 크기를 과도하게 키우고 삽입 오버헤드를 발생시킵니다.

  대신, **전체 문자열만 맵에 저장**한 뒤 각 문자열을 `substring()`으로 잘라가며 `containsKey()`로 확인하는 방식이 성능상 유리합니다. 이 방식은 `Map`의 크기를 일정하게 유지하면서 조회 속도를 최적화할 수 있습니다. 또한, Java의 `substring()`은 내부적으로 `System.arraycopy()` 등을 사용하여 빠르게 새로운 문자열을 생성하므로 대량의 데이터를 처리할 때 유리합니다.
    
  ---

  ### 💡 한 줄 요약

  **이전 코드**는 맵에 "모든 조각"을 다 넣어서 무거웠고, **현재 코드**는 맵에는 "완성본"만 넣고 조각으로 조회만 하기 때문에 훨씬 빠릅니다!


그리고 위 풀이로 코드를 작성한다면 굳이 map을 사용할 필요 없이 `Hash Set`을 사용해 코드를 작성하면 된다.

```java
import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Set<String> set = new HashSet<>();
        
        for (String num : phone_book) {
            set.add(num);
        }
        
        for (String num : phone_book) {
            for (int i = 1; i < num.length(); i++) {
                if (set.contains(num.substring(0, i))) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
```

위 Java 코드는 정답 처리를 받는다. 위 풀이가 내 기존 풀이보다 효율적이기 때문에 Python도 같은 풀이로 코드를 새로 작성해서 최종 정답 코드에 기록하겠다.

참고로 아래 코드도 정답 처리를 받는다. 아마 이전 풀이와 달리 set에 들어간 데이터의 양이 많지 않기 때문에 어느정도 문자열 생성 오버헤드가 발생해도 문제가 없는거 같다. 그래도 위에 작성한 코드가 더 깔끔하니 위 코드를 최종 정답 코드에 기록하겠다.

```java
import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Set<String> set = new HashSet<>();
        
        for (String num : phone_book) {
            set.add(num);
        }
        
        for (String num : phone_book) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < num.length() - 1; i++) {
                String target = sb.append(num.charAt(i)).toString();
                if (set.contains(target)) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
```

결국 이 문제는…

- Java로 문제를 풀 경우 Set을 사용하는 문제
- Python으로 문제를 풀 경우 Map, Set을 모두 사용할 수 있는 문제

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01 - set을 활용한 풀이

    ```python
    def solution(phone_book):
        p_set = set(phone_book)
        
        for p in phone_book:
            for i in range(1, len(p)):
                if p[0:i] in p_set:
                    return False
        
        return True
    ```

- solution02 - map을 활용한 풀이 (Java는 이 방식으로 코드를 작성하면 시간 초과가 발생한다.)

    ```python
    def solution(phone_book):
        p_dict = {}
        
        for p in phone_book:
            target = ""
            for c in p:
                target += c
                p_dict[target] = p_dict.get(target, 0) + 1
        
        for p in phone_book:
            if p_dict[p] > 1:
                return False
        
        return True
    ```


### Java 풀이

- solution01 - set을 활용한 풀이

    ```java
    import java.util.*;
    
    class Solution {
        public boolean solution(String[] phone_book) {
            Set<String> set = new HashSet<>();
            
            for (String num : phone_book) {
                set.add(num);
            }
            
            for (String num : phone_book) {
                for (int i = 1; i < num.length(); i++) {
                    if (set.contains(num.substring(0, i))) {
                        return false;
                    }
                }
            }
            
            return true;
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- 딕셔너리를 빠르게 떠올릴 수 있는 간단한 문제였다.
- `defaultdict`을 사용하면 성능이 조금 떨어진다는 사실을 처음 알았다. 입력이 매우 클 수 있는 경우에는 사용하지 않는게 좋을거 같다.
- P & J 트레이닝을 통해 기존 풀이가 왜 `Java`에서는 시간 초과가 발생하는지, 더 효율적인 풀이는 무엇이 있는지 배울 수 있었다.
    - 문자열을 다루는 연산은 늘 시간 초과 발생 가능성을 따져볼 필요가 있을거 같다.