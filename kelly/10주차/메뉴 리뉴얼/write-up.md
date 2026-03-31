## 👻 재시도 기록

### 재시도 1회 (제한 시간 내 혼자 푸는거 성공)

`16분 12초`만에 문제를 푸는데 성공했다. 중간에 살짝 버벅임이 있기는 했지만 그래도 잘 해결해서 혼자 문제를 풀 수 있었다.

작성한 코드는 다음과 같다.

```sql
from itertools import combinations
from collections import Counter

def solution(orders, course):
    # 전역 데이터
    new_orders = []
    for order in orders:
        new_orders.append(sorted(list(order)))
    
    # 메인 로직
    answer = []
    for size in course:
        candi_list = []
        
        for order in new_orders:
            combi_list = combinations(order, size)
            for candi in combi_list:
                candi_list.append(''.join(candi))
        
        if not candi_list:
            continue

        count = Counter(candi_list)
        max_count = max(count.values())
        if max_count < 2:
            continue
        
        for k, v in count.items():
            if v == max_count:
                answer.append(k)
    
    return sorted(answer)
```

코드 자체는 위 풀이에 기록한 코드가 훨씬 좋은거 같다.

- 참고로 빈 리스트에 대해 `Counter()`를 수행하면 빈 count 객체가 반환된다.
- 위 빈 count에 대해 `max()`를 수행하면 에러가 발생한다.