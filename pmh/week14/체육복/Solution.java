package week14.체육복;
import java.util.*;

class Solution {
    /*
    학생들 번호는 체격순
    바로 앞뒤 번호 학생에게만 체육복을 빌려줄 수 있음
    */
    public int solution(int n, int[] lost, int[] reserve) {
        int[] arr = new int[n + 1];

        // 모든 학생은 기본적으로 체육복 1개
        for (int i = 1; i <= n; i++) {
            arr[i] = 1;
        }

        // 잃어버린 학생은 -1
        for (int i = 0; i < lost.length; i++) {
            arr[lost[i]] -= 1;
        }

        // 여벌 있는 학생은 +1
        for (int i = 0; i < reserve.length; i++) {
            arr[reserve[i]] += 1;
        }

        // 작은 번호부터 처리
        Arrays.sort(lost);

        for (int i = 0; i < lost.length; i++) {
            int student = lost[i];

            // 이미 체육복이 있으면 빌릴 필요 없음
            if (arr[student] == 0) {

                // 왼쪽 학생에게 먼저 빌리기
                if (student > 1 && arr[student - 1] > 1) {
                    arr[student] += 1;
                    arr[student - 1] -= 1;
                }

                // 왼쪽에서 못 빌리면 오른쪽 학생에게 빌리기
                else if (student < n && arr[student + 1] > 1) {
                    arr[student] += 1;
                    arr[student + 1] -= 1;
                }
            }
        }

        int answer = 0;
        //못빌려준 애들이 있을수있으니 sum() 이 아니라 1이상인애들 수세기
        for (int i = 1; i <= n; i++) {
            if (arr[i] > 0) {
                answer++;
            }
        }

        return answer;
    }
}