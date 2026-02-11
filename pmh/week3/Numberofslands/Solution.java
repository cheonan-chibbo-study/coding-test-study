package week3.Numberofslands;
class Solution {
    /*

     */
    public int numIslands(char[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        int icount =0;
        for(int i =0 ;i<grid.length;i++){
            for(int j =0; j<grid[i].length;j++){
                if(grid[i][j] == '1'){
                    icount++;
                    dfs(i,j,grid);
                }

            }
        }
        return icount;
    }

    public void dfs(int r,int c,char[][] grid){
        if(r<0 || r>=grid.length || c<0 || c>=grid[0].length ||
                grid[r][c] == '0'){
            return;
        }
        grid[r][c] ='0';
        dfs(r+1,c,grid);
        dfs(r-1,c,grid);
        dfs(r,c+1,grid);
        dfs(r,c-1,grid);


    }




}