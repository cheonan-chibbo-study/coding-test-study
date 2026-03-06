# 문제 링크
https://leetcode.com/problems/longest-increasing-subsequence/

# 접근 방법
각 숫자를 뽑거나 뽑지 않았을 때 만들어진 수열이 증가하는 수열인지 체크하고, 그때의 길이를 저장하는 방식을 떠올렸니다.  
이 방식은 시간 복잡도가 O(2^(10^4)) 이라서 탑다운 DP로 최적화를 진행했습니다.

하지만 이번에도 Python으로는 TLE가 발생했습니다.

그래서 Java 코드로 작성하니까 통과했습니다.

Python으로 작성하고 Java 코드로 옮기는 데까지 약 45분 정도 걸린 것 같습니다.

# 배운 점
1. 처음에 Python으로 제출했을 때 `last_index`를 `None`으로 초기화해줬는데, 이거 때문에 시간초과가 발생하나 싶어서 맨 앞에 `0` 을 추가한 방식으로 고쳤습니다.
의미없는 값으로 시작할 때 인자는 `None` 대신 `-1` 같이 될 수 없는 값을 넣는 것이 좋겠습니다.

```python
# last_index가 -1부터 시작한다고 가정할 때
memo = [[-1] * (n + 1) for _ in range(n)]

def recurse(i, last_idx):
    if i == n: return 0
    
    # 접근할 때만 +1을 해서 0번 인덱스를 '선택 안 함' 상태로 사용
    if memo[i][last_idx + 1] != -1:
        return memo[i][last_idx + 1]

    # ... 계산 로직 ...
    
    memo[i][last_idx + 1] = result
    return result
```

2. 메모이제이션 배열의 초기값으로는 **계산 결과로 절대 나올 수 없는 값**을 할당해야 합니다.
3. 재귀 함수에서 인자 값이 -1 부터 시작해야 한다면, 메모리 배열에 접근할 때만 +1을 해주는 방식이 가장 깔끔합니다.
