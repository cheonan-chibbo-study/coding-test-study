# 문제 링크
https://leetcode.com/problems/group-anagrams/

# 접근 방법
각 문자열을 오름차순 정렬했을 때, 동일한 Anagram Group이라면 항상 같은 순서를 띄는 점이 떠올랐습니다.  
그래서 `strs` 배열을 1번 순회하면서 각 문자열에 대해 오름차순 정렬을 한 문자열을 Key로 하고, 해당 Key에 `str`을 넣어주는 방식으로 구현했습니다.

```python
# 8ms / 22MB
from collections import defaultdict

class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        d = defaultdict(list)
        for s in strs:
            sorted_s = sorted(s)
            key = ''.join(sorted_s)
            d[key].append(s)

        return list(d.values())
```

시간 복잡도는 문자열 `s`의 길이를 K라고 했을 때, O(N * KlogK) 입니다.

# 배운 점
1. 문자열 유형 문제는 아이디어를 잘 떠올리느냐가 관건인 것 같습니다.
2. 문자열을 정렬하지 않고, 특정 문자열 `s`의 문자 빈도 수로도 문제를 풀 수 있을 것 같았습니다.  
그래서 찾아보니 다음과 같은 방법으로도 풀 수 있었습니다.

```python
from collections import defaultdict

class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        answer = defaultdict(list)

        for s in strs:
            cnt = [0] * 26

            for ch in s:
                idx = ord(ch) - ord('a')
                cnt[idx] += 1

            answer[tuple(cnt)].append(s)

        return list(answer.values())
```

문자의 개수를 저장하는 배열을 만들고, 그 배열을 Key로 사용하는 방법입니다.  
다만 Python에서 딕셔너리의 Key에는 리스트 타입이 될 수 없기에(Mutable) 튜플로 변환해서 Key로 저장해주는 방식입니다.

시간 복잡도는 O(NK) 입니다.
