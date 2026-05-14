# 문제링크
https://school.programmers.co.kr/learn/courses/30/lessons/92344
# 30분내 어디까지 풀었는가
30분내 풀기를 완료했으나 효율성테스트에서 실패
```java
class Solution {
    /*
    NXM 크기 행렬 모양 게임맵
    
    */
    public int solution(int[][] board, int[][] skill) {
        int r = board.length;
        int c = board[0].length;

        for(int[] a : skill){
            int type =a[0];
            int sr =a[1]; int sc = a[2];
            int er =a[3]; int ec = a[4];
            int degree = a[5];
            build(type,sr,sc,er,ec,degree,board);
        }
        int count=0;
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(board[i][j] >0){
                    count++;
                }
            }
        }

        return count;
    }
    public void build(int type,int sr,int sc,int er,int ec,int degree,int [][] board){
        for(int i=sr;i<=er;i++) {
            for(int j=sc;j<=ec;j++){
                if(type==1){
                    board[i][j] -=degree;
                }else{
                    board[i][j] +=degree;
                }
            }
        }

    }
}
```
이 코드의 시간 복잡도
- 보드 크기를 R x C, 
- 스킬 개수를 K라고 하면 
- 스킬 하나 처리: 최악 O(R*C)
- 스킬 K개 처리: O(K*R*C)
  예를 들어

R = 1000
C = 1000
K = 250000

라고 치면

1000 * 1000 * 250000
= 250,000,000,000

즉 2500억 번 수준이라 사실상 불가능하다.
# 왜 못풀었는가 - 
### 풀이가 안떠올랐는지 / 어떻게 풀지는 알겠는데 구현을 못하겠다.


# 접근방법
- 완전 탐색방이아닌 -> 2차 배열 누적합 방식을사용한다
- diff[sr][sc] += 5;  
  diff[sr][ec + 1] -= 5;  
  diff[er + 1][sc] -= 5;  
  diff[er + 1][ec + 1] += 5;  
- 끝나는 위치 ec 에서 끊는 게 아니라
  그 다음 위치 ec+1 에서 끊어야
  ec 까지 영향이 유지된다
- 이렇게 구한후 board + diff >0 이상이면 건물 갯수를 센다
# 배운점 
2차 누적합 에 대해 알게됨