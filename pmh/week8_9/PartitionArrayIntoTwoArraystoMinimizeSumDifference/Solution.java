package week8_9.PartitionArrayIntoTwoArraystoMinimizeSumDifference;

import java.util.*;

class Solution {

    public int minimumDifference(int[] nums) {
        // nums 길이는 2n 이다.
        // 문제는 이 중 정확히 n개를 골라서
        // 두 그룹의 합 차이를 최소로 만드는 것.
        int n = nums.length / 2;

        // 배열을 반으로 나눈다.
        // 왼쪽 n개, 오른쪽 n개
        int[] leftArr = Arrays.copyOfRange(nums, 0, n);
        int[] rightArr = Arrays.copyOfRange(nums, n, nums.length);

        // 전체 합
        // 차이 계산식:
        // 선택한 n개의 합을 picked 라고 하면
        // 나머지 합은 total - picked
        // 차이는 |picked - (total - picked)| = |total - 2*picked|
        long total = 0;
        for (int num : nums) {
            total += num;
        }

        // left[k]  = 왼쪽 배열에서 정확히 k개 골랐을 때 가능한 모든 합
        // right[k] = 오른쪽 배열에서 정확히 k개 골랐을 때 가능한 모든 합
        //
        // 왜 k개별로 나눠 저장하냐?
        // 최종적으로 정확히 n개를 골라야 하므로
        // 왼쪽에서 k개 골랐으면 오른쪽에서는 반드시 n-k개를 골라야 하기 때문
        List<Integer>[] left = new ArrayList[n + 1];
        List<Integer>[] right = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            left[i] = new ArrayList<>();
            right[i] = new ArrayList<>();
        }

        // 왼쪽 / 오른쪽 각각에 대해
        // 가능한 모든 부분집합 합을 "선택 개수별"로 저장
        makeSums(leftArr, left);
        makeSums(rightArr, right);

        // 오른쪽 리스트들은 나중에 이분 탐색할 것이므로 정렬
        for (int i = 0; i <= n; i++) {
            Collections.sort(right[i]);
        }

        // 최소 차이 저장
        long answer = Long.MAX_VALUE;

        // 왼쪽에서 k개, 오른쪽에서 n-k개를 선택하는 모든 경우를 확인
        for (int k = 0; k <= n; k++) {
            List<Integer> leftList = left[k];
            List<Integer> rightList = right[n - k];

            // 왼쪽에서 만든 합 하나를 a 라고 하자
            for (int a : leftList) {

                // 최종 선택 합은 a + b
                // 우리는 a + b 가 total/2 에 최대한 가까워지길 원한다.
                //
                // 따라서 오른쪽에서 찾고 싶은 값 b 는 대략
                // b ≈ total/2 - a
                long target = total / 2 - a;

                // rightList 에서 target 이상이 처음 나오는 위치
                int idx = lowerBound(rightList, target);

                // 1) idx 위치의 값 확인
                //    target 이상인 첫 값이므로 유력한 후보
                if (idx < rightList.size()) {
                    long picked = (long) a + rightList.get(idx);
                    answer = Math.min(answer, Math.abs(total - 2 * picked));
                }

                // 2) idx 바로 이전 값도 확인
                //    target 보다 작은 값들 중 가장 큰 값이라서
                //    실제로는 이 값이 더 가까울 수 있음
                if (idx > 0) {
                    long picked = (long) a + rightList.get(idx - 1);
                    answer = Math.min(answer, Math.abs(total - 2 * picked));
                }
            }
        }

        return (int) answer;
    }

    private void makeSums(int[] arr, List<Integer>[] sumsByCount) {
        int len = arr.length;

        // 부분집합은 비트마스크로 생성
        // 예: len = 3 이면 mask 는 000 ~ 111
        // 총 2^len 개
        int totalMask = 1 << len;

        for (int mask = 0; mask < totalMask; mask++) {
            int sum = 0;    // 현재 부분집합의 합
            int count = 0;  // 현재 부분집합에서 고른 원소 개수

            // mask 의 각 비트를 확인하면서
            // i번째 원소를 골랐는지 확인
            for (int i = 0; i < len; i++) {
                if ((mask & (1 << i)) != 0) {
                    // i번째 비트가 1이면 arr[i]를 선택한 것
                    sum += arr[i];
                    count++;
                }
            }

            // "count개 골랐을 때 가능한 합" 리스트에 sum 추가
            sumsByCount[count].add(sum);
        }
    }

    private int lowerBound(List<Integer> list, long target) {
        // lowerBound:
        // 정렬된 list 에서 target 이상이 처음 나오는 인덱스 반환
        //
        // 예:
        // list = [1, 4, 6, 9], target = 5
        // 결과 = 2 (list[2] == 6)

        int lo = 0;
        int hi = list.size();

        while (lo < hi) {
            int mid = (lo + hi) / 2;

            if (list.get(mid) < target) {
                // mid 값이 target 보다 작으면
                // 정답은 오른쪽에 있음
                lo = mid + 1;
            } else {
                // mid 값이 target 이상이면
                // mid 도 후보이므로 hi 를 mid 로 줄임
                hi = mid;
            }
        }

        return lo;
    }
}