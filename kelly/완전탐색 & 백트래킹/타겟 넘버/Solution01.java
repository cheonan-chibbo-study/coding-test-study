import java.util.*;

class Solution {

    int[] numbers;
    int target;
    int answer;

    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
        this.answer = 0;

        // 메인 로직
        recursive(0, 0);
        return answer;
    }

    private void recursive(int totalSum, int seq) {
        if (seq == numbers.length) {
            if (totalSum == target) {
                answer++;
            }

            return;
        }

        for (int i : List.of(-1, 1)) {
            totalSum += (numbers[seq] * i);
            recursive(totalSum, seq + 1);
            totalSum -= (numbers[seq] * i);
        }
    }
}