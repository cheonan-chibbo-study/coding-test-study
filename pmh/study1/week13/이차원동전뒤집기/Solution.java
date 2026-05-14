package study1.week13.이차원동전뒤집기;

class Solution {
    public int solution(int[][] beginning, int[][] target) {
        int n = beginning.length;
        int m = beginning[0].length;

        int answer = Integer.MAX_VALUE;

        // 행 뒤집기 조합: 0 ~ 2^n - 1
        for (int rowMask = 0; rowMask < (1 << n); rowMask++) {
            boolean[] rowFlip = new boolean[n];
            boolean[] colFlip = new boolean[m];

            int count = 0;

            // 1. rowMask를 보고 어떤 행을 뒤집을지 결정
            for (int i = 0; i < n; i++) {
                if ((rowMask & (1 << i)) != 0) {
                    rowFlip[i] = true;
                    count++;
                }
            }

            // 2. 0번째 행 기준으로 열 뒤집기 결정
            for (int j = 0; j < m; j++) {
                int value = beginning[0][j];

                // 0번째 행이 뒤집히는 경우
                if (rowFlip[0]) {
                    value = 1 - value;
                }

                // target과 다르면 이 열은 반드시 뒤집어야 함
                if (value != target[0][j]) {
                    colFlip[j] = true;
                    count++;
                }
            }

            // 3. 전체 칸 검사
            boolean possible = true;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int value = beginning[i][j];

                    // 행 뒤집기 적용
                    if (rowFlip[i]) {
                        value = 1 - value;
                    }

                    // 열 뒤집기 적용
                    if (colFlip[j]) {
                        value = 1 - value;
                    }

                    // target과 다르면 실패
                    if (value != target[i][j]) {
                        possible = false;
                        break;
                    }
                }

                if (!possible) break;
            }

            // 4. 가능하면 최소값 갱신
            if (possible) {
                answer = Math.min(answer, count);
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}