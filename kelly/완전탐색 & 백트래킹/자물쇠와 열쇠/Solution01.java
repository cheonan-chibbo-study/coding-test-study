class Solution {

    int[][] key;
    int[][] lock;
    int k_size;
    int l_size;

    public boolean solution(int[][] key, int[][] lock) {
        this.key = key;
        this.lock = lock;
        this.k_size = key.length;
        this.l_size = lock.length;

        // 메인 로직
        for (int rowOffset = k_size; rowOffset >= -l_size; rowOffset--) {
            for (int colOffset = k_size; colOffset >= -l_size; colOffset--) {
                if (check(rowOffset, colOffset)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean check(int rowOffset, int colOffset) {
        int[][] rotatedKey = new int[k_size][k_size];
        for (int i = 0; i < k_size; i++) {
            System.arraycopy(key[i], 0, rotatedKey[i], 0, key[i].length);
        }

        boolean isContinue;
        for (int d = 0; d < 4; d++) {
            // 키 회전
            if (d != 0) {
                rotatedKey = rotate(rotatedKey);
            }
            isContinue = true;

            for (int row = 0; row < l_size; row++) {
                for (int col = 0; col < l_size; col++) {
                    int keyRow = row + rowOffset;
                    int keyCol = col + colOffset;

                    // 자물쇠 홈 부분일 때
                    if (lock[row][col] == 0) {
                        if (!isSafe(keyRow, keyCol) || rotatedKey[keyRow][keyCol] != 1) {
                            isContinue = false;
                            break;
                        }
                    } else {  // 자물쇠 돌기 부분일 때
                        if (isSafe(keyRow, keyCol) && rotatedKey[keyRow][keyCol] == 1) {
                            isContinue = false;
                            break;
                        }
                    }
                }

                if (!isContinue) {
                    break;
                }
            }

            if (isContinue) {
                return true;
            }
        }

        return false;
    }

    private int[][] rotate(int[][] original) {
        int[][] rotated = new int[k_size][k_size];

        for (int row = 0; row < k_size; row++) {
            for (int col = 0; col < k_size; col++) {
                rotated[col][k_size - row - 1] = original[row][col];
            }
        }

        return rotated;
    }

    private boolean isSafe(int r, int c) {
        return r >= 0 && r < k_size && c >= 0 && c < k_size;
    }
}