# 문제 링크
https://leetcode.com/problems/combinations/description/

# 접근 방법
1. 백준에서 N과 M을 많이 풀어봐서 자동적으로 재귀 함수를 작성했다.
2. 재귀 작성 순서  
2.1. 진행부  
직전에 할당한 값보다 커야하기에 for문에서 시작 값을 따로 인자로 받아서 사용했다.  
이번에도 중복 사용 방지를 위해 각 인덱스 별 사용 여부를 기록하는 `used` 배열을 선언했다. 

```python
for num in range(start, n+1):
    if used[num]:
        continue
    used[num] = True
    result.append(num)
    recurse(num+1, result)
    result.pop()
    used[num] = False
```
2.2. 종료부  
과정을 기록하는 `result`에 `k`개의 숫자가 포함되면 결과를 기록하기 위해 이를 종료 조건으로 설정했다.  
오염 방지를 위해 리스트 슬라이싱으로 `result`를 복사해서 저장한다. 

```python
if len(result) == k:
    answer.append(result[:])
    return
```

# 배운 점
1. 백준의 N과 M을 많이 풀어본 게 큰 도움이 됐다.
