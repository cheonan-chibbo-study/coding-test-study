package week1.자물쇠와열쇠;
class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int n = lock.length;
        int m = key.length;



        for (int rotate = 0; rotate < 4; rotate++) {
            key = rotateKey(key);

            // 열쇠를 놓을 수 있는 모든 시작 지점 (0,0) ~ (n*2, n*2)
            for (int x = 0; x < n * 2; x++) {
                for (int y = 0; y < n * 2; y++) {

                    // 확장된 자물쇠 생성
                    int[][] newLock = new int[n * 3][n * 3];
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            newLock[i + n][j + n] = lock[i][j];
                        }
                    }

                    addKey(newLock,key,x,y);

                    if (check(newLock, n)) {
                        return true;
                    }




                }
            }
        }


        return false;
    }

    //열쇠를 시계 방향으로 90도 회전
    private int[][] rotateKey(int[][] key) {
        int m = key.length;
        int[][] rotated = new int[m][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                rotated[j][m - 1 - i] = key[i][j];
            }
        }
        return rotated;
    }

    //확장된 자물쇠에 열쇠의 값을 더해주는 함수
    private void addKey(int[][] newLock, int[][] key, int x, int y) {
        int m = key.length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                newLock[x + i][y + j] += key[i][j];
            }
        }
    }

    //중앙의 자물쇠가 모두 1인지 확인하는 함수
    private boolean check(int[][] newLock, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (newLock[i + n][j + n] != 1) {
                    return  false;
                }
            }
        }
        return true;
    }
}