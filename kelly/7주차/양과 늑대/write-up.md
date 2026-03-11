## 👻 재시도 기록

### 재시도 1회

`25분 8초`만에 혼자서 문제를 푸는데 성공했다.

핵심 풀이법은 알고 있어서 풀이 설계는 쉬웠지만 코드 작성이 빠르게 되지 않아 애를 먹었다.

그리고 중간중간 로직 실수가 있어 디버깅 과정이 좀 필요했다.

그래도 다행히 제한 시간내 혼자서 문제를 푸는데 성공했다.

작성한 코드는 다음과 같다. 기존에 작성한 코드와 크게 다른 부분은 없다.

```python
def solution(info, edges):
    # 메서드
    def search(s, w, cur_v):
        nonlocal answer
        
        if info[cur_v] == 0:
            s += 1
        else:
            w += 1

        if w >= s:
            return
        
        answer = max(answer, s)
        for start, end in edges:
            if start in visited and (start, end) not in candi:
                visited.append(end)
                candi.add((start, end))
                search(s, w, end)
                
                visited.pop()
                candi.remove((start, end))
            
    # 메인 로직
    visited = [0]
    candi = set()
    answer = 0
    
    search(0, 0, 0)
    
    return answer
```