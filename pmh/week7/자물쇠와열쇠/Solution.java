package week7.자물쇠와열쇠;

class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int n = lock.length;
        int m = key.length;

        //key 회전 하기
        for(int rotate =0;rotate<4;rotate++){
            key = rotateKey(key);

            for(int x =0;x<n*2;x++){
                for(int y =0 ; y<n*2;y++){

                    //확장된 자물쇠 생성 및 자물쇠 복사
                    int[][] newLock = new int[n *3][n*3];
                    for(int i =0;i<n;i++){
                        for(int j =0;j<n;j++){
                            newLock[i+n][j+n] = lock[i][j];
                        }
                    }

                    addKey(newLock,key,x,y);
                    if(check(newLock,n)){
                        return true;
                    }

                }
            }
        }
        return false;
    }
    public int[][] rotateKey(int[][] key){
        int m = key.length;
        int[][]rotated = new int[m][m];
        for(int i =0;i<m;i++){
            for(int j =0;j<m;j++){

                rotated[j][m-1-i] = key[i][j];
            }
        }
        return rotated;

    }
    public int[][] addKey(int[][]newLock,int[][]key,int x,int y){
        int m = key.length;

        for(int i =0;i<m;i++){
            for(int j =0;j<m;j++){

                newLock[x+i][y+j] += key[i][j];
            }
        }
        return newLock;

    }
    public boolean check(int[][]newLock,int n){

        for(int i =0;i<n;i++){
            for(int j =0;j<n;j++){

                if(newLock[i+n][j+n]  != 1)return false;
            }
        }
        return true;

    }
}