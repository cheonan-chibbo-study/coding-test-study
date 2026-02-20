## 👀 제한 시간 안에 어디까지 해냈는가?

제한 시간 `30분` + 추가 시간 `22분`을 사용했지만 문제를 풀지 못했다.

처음에 문제 조건을 잘못 이용해서 시간을 날렸고, 겨우 문제를 다시 이해하고 코드를 작성했지만 최종 채점 케이스에서 3개를 만족하지 못했다. 결국 추가 시간을 사용해 나름 고민하여 틀린 케이스 1개를 통과하는데 성공했지만 나머지 2개 케이스를 통과하지 못해 GPT에게 의견을 물어봤고 GPT 답변은 하나도 도움이 되지 않았지만 힌트를 하나 얻었기에 이를 적용해서 최종 정답 처리를 받는 코드를 작성했다.

아래는 GPT에게 물어보기전 혼자 작성한 코드이다.

```python
from heapq import heapify, heappush, heappop
from collections import deque

def solution(jobs):
    # 전역 데이터
    new_jobs = []
    for id in range(len(jobs)):
        new_jobs.append((jobs[id][1], jobs[id][0], id))
    new_jobs.sort(key = lambda x: x[1])
    new_jobs = deque(new_jobs)
    
    # 메서드
    
    # 메인 로직
    total_times = [0] * len(jobs)
    
    first_job = new_jobs.popleft()
    pq = [first_job]
    time = 0
    
    while pq:
        need_time, req_time, cur_id = heappop(pq)
        if time < req_time:
            time = req_time
        
        start_time = time
        time += need_time
        total_times[cur_id] = time - req_time
        
        while new_jobs and start_time <= new_jobs[0][1] <= time:
            heappush(pq, new_jobs.popleft())
        
        if new_jobs and not pq:
            heappush(pq, new_jobs.popleft())
            

    answer = 0
    for v in total_times:
        answer += v
    
    return answer // len(jobs)
```

---

## 🧑‍🔬 문제 분석

디스크가 수행할 작업 목록이 주어지고, 이 작업 목록의 각 요소는 (작업 요청 시간, 작업에 필요한 총 시간) 정보로 구성되어 있을 때, 디스크가 아무런 작업도 하고 있지 않다면 먼저 요청된 작업을 수행하거나, 혹은 작업중 다른 작업이 요청되었을 때 문제에 주어진 작업간 우선순위를 적용한 순서로 작업을 진행해서 작업 하나를 처리하는데 든 평균 시간을 구해 반환하는 문제이다.

- 문제 조건이 제법 까다로워서 자세한 조건은 문제를 참고하자.

문제에 주어진 제약 조건은 다음과 같다.

### 제한 사항

- 1 ≤ `jobs`의 길이 ≤ 500
- `jobs[i]`는 `i`번 작업에 대한 정보이고 [s, l] 형태입니다.
    - s는 작업이 요청되는 시점이며 0 ≤ s ≤ 1,000입니다.
    - l은 작업의 소요시간이며 1 ≤ l ≤ 1,000입니다.

---

## 🤔 풀이 고민

처음 문제를 봤을 때 우선 순위 큐 + 최소 힙을 떠올렸다. 주어지는 작업 목록의 최대 개수가 500으로 작은편이기 때문에 처음 정렬에 필요한 nlogn의 연산이 수행되더라도 문제를 충분히 해결할 수 있다는 결론을 내렸다.

문제를 푸는데는 queue도 사용하였다.

### 결론

- 정렬 + 우선 순위 큐(최소 힙)를 활용해 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 틀린 부분 정정

아이디어를 정정해 추가 시간까지 사용해서 코드를 혼자 작성했지만 최종 채점 단계에서 케이스 2개를 만족하지 못했다. 아래는 내가 작성한 코드이고 틀린 부분을 잡아보겠다.

```python
from heapq import heapify, heappush, heappop
from collections import deque

def solution(jobs):
    # 전역 데이터
    new_jobs = []
    for id in range(len(jobs)):
        new_jobs.append((jobs[id][1], jobs[id][0], id))
    new_jobs.sort(key = lambda x: x[1])
    new_jobs = deque(new_jobs)
    
    # 메서드
    
    # 메인 로직
    total_times = [0] * len(jobs)
    
    first_job = new_jobs.popleft()
    pq = [first_job]
    time = 0
    
    while pq:
        need_time, req_time, cur_id = heappop(pq)
        if time < req_time:
            time = req_time
        
        start_time = time
        time += need_time
        total_times[cur_id] = time - req_time
        
        while new_jobs and start_time <= new_jobs[0][1] <= time:
            heappush(pq, new_jobs.popleft())
        
        if new_jobs and not pq:
            heappush(pq, new_jobs.popleft())
            

    answer = 0
    for v in total_times:
        answer += v
    
    return answer // len(jobs)
```

- `new_jobs.append((jobs[id][1], jobs[id][0], id))` 이 코드가 잘못되었다. 이 코드는 처음에 작업 요청 시간을 기준으로 리스트를 정렬하고, 처음 수행하는 작업으로 정렬된 리스트의 첫 번째 요소를 가져오거나 반복문 중간에 우선순위 큐가 비면 다음 수행할 작업을 찾는 용도로 사용되었다.
- 문제는 저 코드는 작업 시작 시간을 기준으로만 정렬하고 우선 순위 조건에서 가장 큰 영향을 주는 총 필요 작업 시간을 정렬 조건에 반영하지 않는게 문제였다. 따라서 같은 작업 요청 시간을 가졌지만 작업에 필요한 시간이 다른 요소가 여러개 있을 때 문제에 주어진 우선 순위 조건을 위반하고 단순히 요청 시간이 같다는 이유로 작업에 필요한 시간이 더 많은 요소가 먼저 실행될 수 있다. 아마 이 부분이 나머지 2개의 케이스를 오답으로 만들었을 것이다.
- 따라서 이 코드를 다음과 같이 정정해야한다.
    - new_jobs.sort(key = lambda x: (x[1], x[0], x[2]))

잘못된 코드를 아래와 같이 정정하고 재채점을 진행하니 최종 정답 처리를 받을 수 있었다.

```python
from heapq import heapify, heappush, heappop
from collections import deque

def solution(jobs):
    # 전역 데이터
    new_jobs = []
    for id in range(len(jobs)):
        new_jobs.append((jobs[id][1], jobs[id][0], id))
    new_jobs.sort(key = lambda x: (x[1], x[0], x[2]))
    new_jobs = deque(new_jobs)
    
    # 메서드
    
    # 메인 로직
    total_times = [0] * len(jobs)
    
    first_job = new_jobs.popleft()
    pq = [first_job]
    time = 0
    
    while pq:
        need_time, req_time, cur_id = heappop(pq)
        if time < req_time:
            time = req_time
        
        start_time = time
        time += need_time
        total_times[cur_id] = time - req_time
        
        while new_jobs and start_time <= new_jobs[0][1] <= time:
            heappush(pq, new_jobs.popleft())
        
        if new_jobs and not pq:
            heappush(pq, new_jobs.popleft())
            

    answer = 0
    for v in total_times:
        answer += v
    
    return answer // len(jobs)
```

### 최종 코드 개선

최종 코드를 다음과 같이 다듬었다.

```python
from heapq import heapify, heappush, heappop
from collections import deque

def solution(jobs):
    # 전역 데이터
    jobs_count = len(jobs)
    new_jobs = []
    for id in range(jobs_count):
        new_jobs.append((jobs[id][1], jobs[id][0], id))
    new_jobs.sort(key = lambda x: (x[1], x[0], x[2]))
    new_jobs = deque(new_jobs)
    
    # 메인 로직
    total_times = [0] * jobs_count
    
    first_job = new_jobs.popleft()
    pq = [first_job]
    time = 0
    
    while pq:
        need_time, req_time, cur_id = heappop(pq)
        if time < req_time:
            time = req_time
        
        time += need_time
        total_times[cur_id] = time - req_time
        
        while new_jobs and new_jobs[0][1] <= time:
            heappush(pq, new_jobs.popleft())
        
        if new_jobs and not pq:
            heappush(pq, new_jobs.popleft())

    return sum(total_times) // jobs_count
```

- 반복문 중간에 현재 작업 시작 시간 ~ 작업 종료 시간 사이의 요청 작업을 찾는 과정에서 if 조건을 다음과 같이 수정할 수 있다.
    - `start_time <= new_jobs[0][1] <= time:` → `new_jobs[0][1] <= time:`
    - 어차피 new_jobs는 작업 요청 시간을 기준으로 오름차순 정렬이 되어 있어서 작업의 시작 시간은 조건에 포함시킬 필요가 없다.
- 총 작업 개수를 일일이 len()하던 코드를 전역 데이터에 추가하고 재활용했다.
- 마지막 평균을 구하는 로직을 sum()을 활용해서 간략화 시켰다.

---

## 🥰 배운점 & 느낀점

- 비록 제한 시간 내 풀지는 못했지만 마지막까지 내 고민으로 문제를 찾아 해결했기 때문에 많이 뿌듯했다.
- 하지만 결국 틀린건 틀린거라 매우 아쉬웠다. 앞으로 이런 세부 케이스를 잘 신경쓰도록 노력해야겠다.