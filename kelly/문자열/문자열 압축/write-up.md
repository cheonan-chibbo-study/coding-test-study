## 👀 제한 시간 안에 어디까지 해냈는가?

5주차 첫 문제이기 때문에 학습을 위해 따로 제한 시간을 두지 않고 풀었다.

결론적으로 혼자서 문제를 풀지 못해 풀이를 찾아보았다. 찾아본 풀이로 작성한 코드를 복기하면서 문자열을 다루는 테크닉을 체화할 필요가 있다.

P & J 트레이닝

- Python & Java 모두 풀이를 떠올리지 못해서 이전 write-up을 보고 풀었다.
- 다음에 복습해서 꼭 혼자서 풀 수 있도록 연습하자!!!!

---

## 🧑‍🔬 문제 분석

문자열 S가 주어졌을 때 문제에 주어지는 압축 조건을 활용했을 때 가장 짧게 압축되는 문자열의 길이를 반환하는 문제이다.

- 자세한 압축 조건은 문제를 참고하자.

문제에 주어진 제약 조건은 다음과 같다.

### **제한사항**

- s의 길이는 1 이상 1,000 이하입니다.
- s는 알파벳 소문자로만 이루어져 있습니다.

---

## 🤔 풀이 고민

문제를 처음 풀 때 혼자서 풀이를 떠올리지 못했다. 아래는 찾아본 풀이를 정리한 내용이다.

문제에서 요구하는 값은 문자열 압축의 결과 중 길이가 가장 짧은 문자열의 길이이다. 이를 구하기 위해 문자열을 직접 압축해야할 필요가 있다.

문제에서 제시하는 문자열의 압축과정은 다음과 같다.

문자열을 일정한 단위로 나누고, 나뉜 문자열중 **연속적**으로 일치하는 문자열들에 대해 압축을 진행한다.

이때 `연속된횟수` `패턴` 형태로 압축이 진행된다. (ex.`ababab`  == `3ab` )

문제에서 요구하는 값은 여러 길이의 단위로 문자열을 압축 했을때 가장 짧게 압축되는 문자열의 길이를 구하고자 하는것이다.

이를 위해 단위별로 문자열을 압축해야하는데 압축하는 단위의 범위는 다음과 같다.

`1` ≤ `압축 단위` ≤ `문자열길이/2`

그렇기에 압축 단위를 `1`부터 `문자열길이/2` 까지 늘려가며 압축을 진행하고 각각의 압축 문자열 길이를 구해야한다.

이를 수행하려면 압축 단위 마다 일일이 문자열끼리 비교를 하고, 모든 압축 단위에 대해서 이 작업(`완전탐색`)을 수행한다. 이때의 시간복잡도는 다음과 같다.

`s = "ababcdcdababcdcd"` 일때 문자열 `s`의 길이는 `n`이라고 하자.

- 압축 단위  1 : `"a"` `"b"` `"a"` `"b"` `"c"` `"d"` `"c"` `"d"` `"a"` `"b"` `"a"` `"b"` `"c"` `"d"` `"c"` `"d"`
    - 연속된 중복 패턴이 없어 압축 불가
    - 시간복잡도 O(n)이 소요
- 압축 단위 2 : `"ab"` `"ab"` `"cd"` `"cd"` `"ab"` `"ab"` `"cd"` `"cd"`
    - `"2ab2cd2ab2cd"` 로 압축 ⇒ 길이 : 12
    - 시간복잡도 O(n)이 소요
- 압축 단위 3 : `"aba"` `"bcd"` `"cda"` `"bab"` `"cdc"` `"d"`
    - 연속된 중복 패턴이 없어 압축 불가
    - 시간복잡도 O(n)이 소요
- 압축 단위 4 : `"abab"` `"cdcd"` `"abab"` `"cdcd"`
    - 연속된 중복 패턴이 없어 압축 불가
    - 시간복잡도 O(n)이 소요
- …
- 압축 단위 n/2: `"ababcdcd"` `"ababcdcd"`
    - `"2ababcdcd"` 로 압축 ⇒ 길이 : 9
    - 시간복잡도 O(n)이 소요

시간복잡도 O(n)이 걸리는 작업이 총 n/2번 반복되기 때문에 총 시간복잡도는 $O(n^2)$이다. 문제의 제약조건은 n ≤ 1000 이였기 때문에 ‘완전탐색’을 적용해도 통과할 수 있다. 따라서 이대로 진행을 해보자.

문자열 압축 단위의 최댓값은 주어진 문자열 길이의 절반이다. 그렇기에 압축 단위를 1부터 문자열 길이의 절반까지 (n/2 번 반복 ⇒ O(n)) 설정하며 문자열을 압축하고 그 길이를 비교한다.

문자열을 압축하기 위해 현재 압축 단위만큼 문자열을 나눈 후 나눠진 문자열을 순회하며 현재 부분 문자열과 이전 부분 문자열을 비교해(이 과정을 결국 n개의 문자에 대해서 일일이 다 비교를 하게 되기 때문에 시간복잡도 O(n)이 걸린다. O(n)이 소요되는 문자열 비교과정을 모든 압축단위에 대해서 해야되기 때문에 n/2 * O(n) = O(n^2)이 된다.) 압축 가능 여부를 확인한다. 만약 이전 문자열과 동일한 경우, 압축 가능한 문자열이기에 동일하지 않은 문자열이 나올 때까지 중복된 문자열의 개수를 기록하고 기록된 개수를 기반으로 압축 문자열을 갱신한다.

이 때 이전 부분 문자열과 비교를 해야한다는 특성 상 스택 자료구조를 적용할 수 있다.

### 결론

- 아이디어를 떠올려 문자열 슬라이싱으로 문제를 해결할 수 있다.
- 위에서 떠올린 아이디어를 Stack을 활용해서 구현할 수도 있다.

---

## 🏃 코드 작성 과정

### 구현하지 못한 부분 구현

위에 기술한 풀이를 코드로 구현하면 다음과 같다. 코드는 단순 문자열 연산을 사용한 코드와 Stack을 사용한 코드를 각각 작성했다.

[ 문자열 연산 활용 ]

```python
def solution(s):
    # 메서드
    def compress(text, length):
        #✅ 현재 압축 단위에 따라 문자열을 나눈다.
        words = [text[i:i + length] for i in range(0, len(text), length)]
        compressed = ""
        prev_word = ''
        count = 0
        
        #✅ 이전 부분 문자열과 현재 부분 문자열을 비교한다.
        for word in words:
            #✅ 만약 같다면, 이전 부분 문자열의 개수를 1 증가시킨다.
            if word == prev_word:
                count += 1
            #✅ 만약 다르다면, 이전 부분 문자열의 개수와 문자열을 압축 문자열에 이어붙인다.
            else:
                if count > 1:
                    compressed += str(count)
                compressed += prev_word
                #✅ 현재 부분 문자열을 이전 부분 문자열로 지정하고, 그 개수를 1로 설정한다.
                prev_word = word
                count = 1
        
            #✅ 마지막 부분 문자열을 처리한다.
        if count > 1:
            compressed += str(count)
        compressed += prev_word

        return len(compressed)
    
    # 메인 로직
    if len(s) == 1:
        return 1
    
    return min(compress(s, length) for length in range(1, len(s) // 2 + 1))
```

[ Stack 활용 ]

```python
def solution(s):
    # 전역 데이터
    str_len = len(s)
    answer = str_len
    
    for size in range(1, str_len // 2 + 1):
        #✅ 현재 압축 단위에 따라 문자열을 나눈다.
        words = [s[i:i+size] for i in range(0, str_len, size)]
        stack = [(words[0], 1)]
        
        #✅ stack의 top과 현재 부분 문자열을 비교한다.
        for word in words[1:]:
            #✅ 만약 같다면, top의 중복 개수를 1 증가시킨다.
            if stack[-1][0] == word:
                tmp = stack.pop()
                stack.append([tmp[0], tmp[1] + 1])
            #✅ 만약 다르다면, 현재 부분 문자열과 1을 push한다.
            else:
                stack.append([word, 1])
        
        #✅ 압축 문자열을 만든다.
		#✅ 중복 개수가 1보다 크면, 중복 개수와 부분 문자열 w를 압축 문자열에 이어붙인다.
        compressed = ('').join([str(cnt) + w if cnt > 1 else w for w, cnt in stack])
        answer = min(answer, len(compressed))
    
    return answer
```

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01 - 문자열 연산 활용

    ```python
    def solution(s):
        # 메서드
        def compress(length):
            words = [s[i:i + length] for i in range(0, len(s), length)]
            
            result = ""
            prev_word = ""
            count = 1
            
            for word in words:
                if word == prev_word:
                    count += 1
                    continue
                
                if count > 1:
                    result += str(count)
                result += prev_word
                
                prev_word = word
                count = 1
            
            if count > 1:
                result += str(count)
            result += prev_word
            
            return len(result)
        
        # 메인 로직
        if len(s) == 1:
            return 1
        
        return min(compress(i) for i in range(1, (len(s) // 2) + 1))
    ```

- solution02 - Stack 활용

    ```python
    def solution(s):
        # 메인 로직
        if len(s) == 1:
            return 1
        
        answer = len(s)
        
        for length in range(1, (len(s) // 2) + 1):
            words = [s[i:i + length] for i in range(0, len(s), length)]
            stack = [[words[0], 1]]
            
            for word in words[1:]:
                if word == stack[-1][0]:
                    stack[-1][1] += 1
                else:
                    stack.append([word, 1])
            
            result = ''.join([str(cnt) + w if cnt > 1 else w for w, cnt in stack])
            answer = min(answer, len(result))
        
        return answer
    ```


### Java 풀이

- solution01 - 문자열 연산 활용

    ```java
    import java.util.*;
    
    class Solution {
        
        String s;
        
        public int solution(String s) {
            this.s = s;
            
            // 메인 로직
            if (s.length() == 1) {
                return 1;
            }
            
            List<Integer> compressed = new ArrayList<>();
            for (int length = 1; length < (s.length() / 2) + 1; length++) {
                compressed.add(compress(length));
            }
            
            return Collections.min(compressed);
        }
        
        private int compress(int length) {
            List<String> words = getWords(length);
            StringBuilder sb = new StringBuilder();
            String prevWord = "";
            int count = 1;
            
            for (String word : words) {
                if (word.equals(prevWord)) {
                    count++;
                    continue;
                }
                
                if (count > 1) {
                    sb.append(String.valueOf(count));
                }
                
                sb.append(prevWord);
                
                prevWord = word;
                count = 1;
            }
            
            if (count > 1) {
                sb.append(String.valueOf(count));
            }
            sb.append(prevWord);
            
            return sb.length();
        }
        
        private List<String> getWords(int length) {
            List<String> words = new ArrayList<>();
            
            int start = 0;
            while (start + length <= s.length()) {
                words.add(s.substring(start, start + length));
                start += length;
            }
            
            if (start < s.length()) {
                words.add(s.substring(start, s.length()));
            }
            
            return words;
        }
    }
    ```

- solution02 - Stack 활용

    ```java
    import java.util.*;
    
    class Solution {
        
        String s;
        
        public int solution(String s) {
            this.s = s;
            
            // 메인 로직
            int answer = s.length();
            
            for (int length = 1; length < (s.length() / 2) + 1; length++) {
                List<String> words = getWords(length);
                Deque<Item> stack = new ArrayDeque<>();
                stack.push(new Item(words.get(0), 1));
                
                for (int i = 1; i < words.size(); i++) {
                    if (stack.peek().word.equals(words.get(i))) {
                        stack.peek().count++;
                    } else {
                        stack.push(new Item(words.get(i), 1));
                    }
                }
                
                StringBuilder sb = new StringBuilder();
                for (Item item : stack) {
                    if (item.count > 1) {
                        sb.append(String.valueOf(item.count) + item.word);
                    } else {
                        sb.append(item.word);
                    }
                }
                
                answer = Math.min(answer, sb.length());
            }
            
            return answer;
        }
        
        private List<String> getWords(int length) {
            List<String> words = new ArrayList<>();
            
            int start = 0;
            while (start + length <= s.length()) {
                words.add(s.substring(start, start + length));
                start += length;
            }
            
            if (start < s.length()) {
                words.add(s.substring(start, s.length()));
            }
            
            return words;
        }
        
        class Item {
            String word;
            int count;
            
            public Item(String word, int count) {
                this.word = word;
                this.count = count;
            }
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- 문자열을 활용하는 다양한 방식 + stack을 활용하는 새로운 사례를 학습할 수 있어 좋았다.
- 연습해서 비슷한 유형을 만나면 잘 대처할 수 있도록 연습해야겠다.