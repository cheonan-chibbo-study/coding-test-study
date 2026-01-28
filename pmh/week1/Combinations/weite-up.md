# 문제링크
https://leetcode.com/problems/combinations/description/

# 접근방법
1. n 과 k 를 통해 1~n 까지수로 가능한 2개의 조합을 만들라는  문제이므로 완전 탐색을 사용해야한다고 생각함
2. 완전 탐색을 위해 재귀함수 를 작성해서 구현함 대신 [1,2], [2,1] 는 같은 조합으로 판단한다 했으므로
[2, ] 앞의수보다 작은수가 나와야 되므로 재귀함수쓸매다 i+1 을해 전보다 작은수가 list 에 추가되지않게한다

```java
   for (int i = start; i <= n; i++) {
            if (visited[i])
                continue;

            visited[i] = true;
            current.add(i);
            backtrace(i + 1, current, visited, result, n, k);

            visited[i] =false;
            current.remove(current.size() - 1);
        }
```
3. k 값에 따라 만들어지는 조합의 모습과 개수가 달라지므로
   현재 추가 되고있는 리스트의 사이즈가 k 값이되면 리턴해서 출력해준다
```java
   if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }
```