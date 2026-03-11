# 문제링크

# 30분내 어디까지 풀었는가
다음 노드를 불러노느거에서 헤메서 30분을 초과하긴했으나 풀이는 완료
# 접근방법

# 배운점 
```java
      if(tree[index] !=null ){
            for(int child : tree[index]){
                list.add(child);//현재 노드의 자식들 추가
            }
        }
```
```java
  next.addAll(g[node]); // 다음 노드 추가
```
리스트 값들으 리스트로 넘겨주는게아니라 각각 하나의 값으로 넘겨주는법을알게됨