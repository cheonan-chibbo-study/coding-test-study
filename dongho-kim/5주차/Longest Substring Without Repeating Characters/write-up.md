# 문제 링크
https://leetcode.com/problems/longest-substring-without-repeating-characters/description/

# 접근 방법
## 1. Brute-Force
2중 for문으로 substr 범위를 정하고 substr을 추출합니다.  
그 substr을 `set()`으로 감싸주어서 중복된 문자를 쉽게 제거합니다.  
Set으로 감싼 결과와 substr 의 길이가 다르다면 중복된 문자가 포함되어 있다는 의미이므로, 다음 범위를 바로 탐색합니다.  
길이가 같다면 최대 길이를 체크합니다.

해당 방법은 2중 for문으로 문자열 `s`의 최대 길이인 5*10^4의 제곱인 O(25 * 10^8) 의 시간 복잡도를 가집니다.  
이는 1억 번 미만이므로 아슬아슬하게 1초 이내에 풀 수 있습니다.

## 2. 슬라이싱 윈도우
슬라이싱 윈도우를 이용하면 최적화가 가능할 것으로 판단되었습니다.  
하지만 슬라이싱 윈도우로 어떻게 풀 수 있을지 쉽게 떠오르지 않아 AI의 도움을 받았습니다.  
AI가 맨 처음 제시한 코드는 아래와 같습니다.

### 2.1. 첫 번째 형태

for문을 이용해서 자동적으로 right 인덱스를 늘려서 윈도우를 확장하는 형태입니다.  
그러다 중복이 생길 때마다 left 인덱스를 우측으로 늘려서 윈도우를 축소합니다.

```python
# 슬라이싱 윈도우 첫 번째 형태
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        answer = 0

        left = 0
        char_map = {}
        for right, char in enumerate(s):
            # 현재 문자가 이미 등장했고, 그 위치가 현재 윈도우 안에 포함되는 경우
            if char in char_map and char_map[char] >= left:
                left = char_map[char] + 1

            # 현재 문자 위치를 최신화
            char_map[char] = right

            answer = max(answer, right - left + 1)

        return answer
```

### 2.2. 두 번째 형태
개인적으로 슬라이싱 윈도우를 처음 배우고 연습한 형태는 위와 같지 않습니다.  
제가 익숙한 방식은 while 문 안에서 조건에 따라 윈도우 크기를 줄이거나 늘리는 방식입니다.  
따라서 해당 방식으로 작성해달라고 하니까 다음과 같이 작성해주었습니다.

코드가 상대적으로 길긴 하지만, 훨씬 직관적이라서 알고리즘 흐름을 이해하기 수월합니다.

```python
# 슬라이싱 윈도우 2번째 형태
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        n = len(s)
        l, r = 0, 0
        used_chars = set()
        answer = 0

        while r < n:
            # 중복이 없는 경우
            if s[r] not in used_chars:
                # 윈도우 확장
                used_chars.add(s[r])
                answer = max(answer, r - l + 1) # 현재 윈도우 길이 체크
                r += 1 # 윈도우 확장

            # 중복이 있는 경우
            else:
                # 윈도우 축소
                used_chars.remove(s[l])
                l += 1

        return answer
```

# 배운 점
1. 오랜만에 슬라이싱 윈도우 기법을 떠올렸다.
2. 슬라이싱 윈도우를 구현하는 방식에는 한 가지가 아님을 이해했다.
