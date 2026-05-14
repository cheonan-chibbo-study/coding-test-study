# 문제링크
https://school.programmers.co.kr/learn/courses/30/lessons/118669?language=java
# 30분내 어디까지 풀었는가
문제 이해하는데 12 분 정도를 소요했다  
그후 그래프를 구현 하는방법을 까먹어서 구현을 시도하다가 시간이 초과했다


# 접근방법
1. 무방향 그래프 라고 했으니 그래프를 구현한다
2. 산 봉우리 를 표시해줄 isSummit 과 거리를 표시해줄 dist 변수를 선언한다.
```java
boolean[] isSummit = new boolean[n + 1];
        for (int s : summits) isSummit[s] = true;
        Arrays.sort(summits);

        int INF = Integer.MAX_VALUE;
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
```
3. 최소의 intensity 가 필요하므로 우선순위큐를 선언해 intensity 가 낮은 녀석부터 poll 되도록한다
4. 큐에 출발 지점을 추가한후 경로를 탐색한다
```java

            if (curInt != dist[v]) continue; 
            if (isSummit[v]) continue;       
```
5. 위의 코드를 통해 거리가 더 큰값은 스킵하고 정상에 도착하면 다음 큐 를 탐색한다
# 배운점 
- 복습을 확실히 해야겠다고 생각했다 확실히 공부했다고 생각했는데 하루 지나니깐 까먹게 되서 복습의 중요성을 깨닫고있다..