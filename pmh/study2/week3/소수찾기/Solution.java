package study2.week3.소수찾기;
import java.util.*;

class Solution {
    private HashSet<Integer> made = new HashSet<>();
    private boolean[] used;
    private char[] arr;

    public int solution(String numbers) {
        arr = numbers.toCharArray();
        used = new boolean[arr.length];

        // 모든 길이(1~n) 조합 만들기
        dfs(0, 0);

        int count = 0;
        for (int x : made) {
            if (isPrime(x)) count++;
        }
        return count;
    }

    // current: 지금까지 만든 숫자 값
    // depth: 지금까지 사용한 자리수
    private void dfs(int current, int depth) {
        if (depth > 0) {          // 길이 1 이상일 때만 set에 추가
            made.add(current);
        }
        if (depth == arr.length) return;

        for (int i = 0; i < arr.length; i++) {
            if (used[i]) continue;

            used[i] = true;
            int next = current * 10 + (arr[i] - '0');
            dfs(next, depth + 1);
            used[i] = false;
        }
    }

    private boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        int r = (int) Math.sqrt(n);
        for (int i = 3; i <= r; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
}