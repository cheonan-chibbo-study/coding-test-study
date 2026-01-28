# 문제 링크
https://leetcode.com/problems/permutations/

# 접근 방법
1. 문제에서 원하는 값을 구하기 위해서는 `nums` 리스트를 순회해야겠다고 판단했습니다. 그런데 `nums` 리스트의 길이가 일정하지 않으므로 재귀 함수를 작성해야겠다고 생각했습니다.

2. 재귀 함수는 다음과 같은 순서로 작성했습니다.  
  2.1. 진행부  
`nums` 리스트를 첫 번째 인덱스부터 끝까지 순회합니다.  
이때 중복 사용을 방지하기 위해 인덱스 별로 사용 여부를 기록하는 `used` 배열을 선언해서 이용했습니다.
```python
for i in range(len(nums)):
    if not used[i]:
        used[i] = True
        result.append(nums[i])
        recurse(result)
        result.pop()
        used[i] = False
```

2.2. 종료부  
순열을 기록하는 `result` 배열의 크기가 `nums`의 길이랑 똑같으면 모든 순열이 완성되었다고 간주했다.  
그래서 이를 종료 조건으로 두었고, `answer` 배열에 완성된 순열을 저장하고 메서드를 종료한다.
```python
if len(result) == len(nums):
    answer.append(result[:])
    return
```

# 배운 점
1. 문제 접근 방법과 풀이 방법을 의식적으로 설명하려고 하니 어색하고 어디서부터 언급해야 할 지 낯설었다.
2. LeetCode에서 Python으로 문제 푸는 게 처음이었는데 Python3로 풀려고 하니 클래스랑 메서드에 대해 학습해야 했다.
3. 처음에 `answer.append(result)`로 구현해서 값이 이상하게 출력됐다. 출력으로 디버깅해보니 `result` 리스트 객체가 그대로 전달되었기 때문이다. 리스트 슬라이싱으로 리스트 복사해서 전달해야 한다.
