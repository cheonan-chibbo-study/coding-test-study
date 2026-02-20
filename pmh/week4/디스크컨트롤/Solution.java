package week4.디스크컨트롤;

import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        //요청 시각 기준으로 원본 배열 정렬
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        // 우선순위 큐 설정 (소요 시간 짧은 순 -> 요청 시각 빠른 순 -> 작업 번호는 인덱스 활용)
        // 문제에서 작업 번호는 배열의 인덱스로 간주
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[1] != b[1]) return a[1] - b[1]; // 소요 시간 짧은 순
            if (a[0] != b[0]) return a[0] - b[0]; // 요청 시각 빠른 순
            return 0; // 작업 번호는 기본적으로 들어온 순서대로 처리
        });

        int totalResponseTime = 0; // 모든 작업의 반환 시간 합
        int currentTime = 0;      // 현재 시각
        int jobsIdx = 0;          // jobs 배열 인덱스
        int completedJobs = 0;    // 완료된 작업 수

        while (completedJobs < jobs.length) {

            // 현재 시각까지 들어온 모든 요청을 대기열(PQ)에 추가
            while (jobsIdx < jobs.length && jobs[jobsIdx][0] <= currentTime) {
                pq.add(jobs[jobsIdx]);
                jobsIdx++;
            }

            if (pq.isEmpty()) {
                // 처리할 작업이 없다면 다음 작업의 요청 시각으로 이동
                currentTime = jobs[jobsIdx][0];
            } else {
                // 우선순위가 높은 작업을 꺼내서 처리
                int[] job = pq.poll();
                totalResponseTime += (currentTime + job[1] - job[0]);
                currentTime += job[1];
                completedJobs++;
            }
        }

        // 평균 시간 계산
        return totalResponseTime / jobs.length;
    }
}