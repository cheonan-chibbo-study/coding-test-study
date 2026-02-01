package week1.PermutationSequence;

import java.util.ArrayList;
import java.util.List;

class Solution2 {
    public String getPermutation(int n, int k) {
        List<Integer> numbers = new ArrayList<>();
        int[] factorial = new int[n+1];
        StringBuilder sb = new StringBuilder();

        // 1. 팩토리얼 값 미리 계산 및 숫자 리스트 초기화
        int sum = 1;
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            sum *= i;
            factorial[i] = sum;
            numbers.add(i);
        }

        // k를 0-index로 맞춤
        k--;

        // 2. 수학적 계산으로 숫자 하나씩 확정
        for (int i = 1; i <= n; i++) {
            int index = k / factorial[n - i];
            sb.append(numbers.get(index));
            numbers.remove(index); // 사용한 숫자는 제거
            k %= factorial[n - i];
        }

        return sb.toString();
    }
}