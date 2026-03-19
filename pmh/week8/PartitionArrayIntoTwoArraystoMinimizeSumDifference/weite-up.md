# 문제링크
https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/
# 30분내 어디까지 풀었는가

# 왜 못풀었는가 - 
### 풀이가 안떠올랐는지 / 어떻게 풀지는 알겠는데 구현을 못하겠다.
풀이가 안떠올라서 검색해서 풀었다.

# 접근방법
2n개의 숫자 중에서 정확히 n개를 골라
그 합을 전체 합의 절반에 최대한 가깝게 만들기.  

고른 쪽 합 = pick  
나머지 쪽 합 = total - pick  
두 배열 차이 = |pick - (total - pick)| = |total - 2 * pick|
pick 을 total/2 근처로 만든다

# 배운점 
```java
int n = nums.length / 2;
int[] leftArr = Arrays.copyOfRange(nums, 0, n);
int[] rightArr = Arrays.copyOfRange(nums, n, nums.length);
```
새로운 함수를 알게됬다

비트마스크 : 
- 배열의 길이를 2진수로 바꾼다 예 arr = {3,7,8} -> len =3 이니 000~111
- 배열 길이 3 은 2^3 개 총 8 개의 부분집합이 가능해진다
- 비트계산을 통해 부분집합을 골랐을때 가능한 모든합을 구한다
- 만약 101 이라면 arr[0] , 과 arr[1] 를 고르게 된거고 총 2개를 구한값을 더한다
- 이렇게 총 부분집합의 합을 구할수있다
```java
private void makeSums(int[] arr, List<Integer>[] sumsByCount) {
    int len = arr.length;
    int totalMask = 1 << len;

    for (int mask = 0; mask < totalMask; mask++) {
        int sum = 0;
        int count = 0;

        for (int i = 0; i < len; i++) {
            if ((mask & (1 << i)) != 0) {
                sum += arr[i];
                count++;
            }
        }

        sumsByCount[count].add(sum);
    }
}
```
