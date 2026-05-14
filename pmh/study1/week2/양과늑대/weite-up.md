# 문제링크
https://school.programmers.co.kr/learn/courses/30/lessons/92343

# 접근방법
처음에는 edges[i][1] 이런 방식으로 간선을 통해서만 트리를 탐색할려고 했다
하지만 이렇게하니 인포에 있는 양의 수보다 양이 많아지는 문제점을발견했다.

그래서 양이나 늑대를 데려왔다면 현재 info 에 2를 너어서 데려왔다는 사실을 알리고 만약 늑대가 양이랑 똑같아지거나 많아지면 다시 현 info 값에 늑대를 다시넣고
리턴하는 방향으로 풀려고하였다. 그러나 풀리지 않고 30분을 초과하게 됬다. 간선을 통해서 푸는 문제 접근 방식 잘못된것같아서 결국 찾아봐서 풀어보게됬다

List<Integer>[] tree 로 리스트 배열 을 만들어서 부몬 -> 자식 노드 방향을 저장한다. 
- edges 에서 [0,1] [0,2] 이렇게 따로 되어있던게
- tree[0] = {1,2} 이런식으로 저장되게됨 탐색에 용이하게변함

```java
    if(w>=s) return;

        maxSheep = Math.max(maxSheep,s);

        List<Integer>  list = new ArrayList<>(nextNodes);
        list.remove(Integer.valueOf(index)); // 현재 노드 제외

        if(tree[index] !=null ){
            for(int child : tree[index]){
                list.add(child);//현재 노드의 자식들 추가
            
```
0 번(양) -> 1번(늑대) , 8번(양) 가정할시
1. 1단계(루트 노드 0번 방문)
   - index =0; w=1,s=1;
   - maxsheep =1
   - nextNodes: 루트노드의의 자식인 [1,8] 담김
2. 2-a 1번도느( 늑대) 먼저 방문한경우
   - index=1, w=1, s=1 
   - 조건 체크: w(1) >= s(1) 이므로 참입니다. 
   - return: 늑대가 양을 잡아먹으므로 이 길은 막힙니다. (백트래킹)
3. 2-B: 8번 노드(양)를 먼저 방문할 경우 
   - index=8, w=0, s=2 (양 2마리)
   - maxSheep = 2 (갱신)
   - nextNodes 업데이트:
     - 원래 후보 [1, 8]에서 8을 빼고, 8의 자식(만약 있다면, 예: 7번, 9번)을 넣습니다. 
     - 새로운 후보: [1, 7, 9]
이제 다시 새로운 후보 [1,7,9] 중 하나를 골라 탐색을 이어간다 여기서 중요한점은 아까 못간던 1번(늑대) 를 다시 탐색할수있고 이제는 갈수있게
된다는점이다.

if(w>=s) return; 여기서 이미 리턴해서 
```java
 List<Integer>  list = new ArrayList<>(nextNodes);
        list.remove(Integer.valueOf(index)); // 현재 노드 제외
```
이 실행되지않아 노드에서 삭제되지않아서 다시 탐색이 가능하다

# 배운점

list 배열을 쓸생각을 아예 못했는데 이번 문제를 통해 알게되었고 잘 기억하게 될것같다.