// enclaves 여부를 판단해서 해당하는 것만 누적합

class Solution {
    
    static int[][] D = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int count = 0;
    static boolean isEnclave = true;
    
    public void DFS(int[][] grid, int x, int y){
        
        if(x<0 || x>=grid.length || y<0 || y>=grid[0].length){
            isEnclave = false;
            count = 0;
            return;
        }
        
        if(grid[x][y]==0) return;
        
        while(grid[x][y]==1){
            count+=1;
            grid[x][y] = 0;
            for(int i=0; i<4; ++i){
                int nx = x + D[i][0];
                int ny = y + D[i][1];
                DFS(grid, nx, ny);
            }
        }      
        
        
    }
    
    public int numEnclaves(int[][] grid) {
        
        int enclaves = 0;
        
        for(int i=0; i<grid.length; ++i){
            for(int j=0; j<grid[0].length; ++j){
                if(grid[i][j]==1){
                    DFS(grid, i, j);
                    if(isEnclave){
                        enclaves += count;
                        count = 0;
                    }else{
                        isEnclave = true;
                        count = 0;
                    }
                }
            }
        }
        
        return enclaves;
        
    }
}

// enclaves가 아닌 건 전부 바다로 마킹한 후, 육지의 개수를 세는 방법
// 지역번수로 direction을 정해두고 for문을 돌리는 것이 직접 상하좌우 4번 호출하는 것보다 static으로 선언하는 것보다 느림
// 이 기법은 땅이 1, 바다가 0이라는 설정이 전제되었을 때 가능함

class Solution {
    public int numEnclaves(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < m; j++){
                if(i == 0 || i == n - 1 || j == 0 || j == m - 1){
                    if(grid[i][j] == 1){
                        dfs(i, j, dir, grid);  
                    }
                }
            }
        }
        
        int count = 0;
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1){
                    count++;  
                }
            }
        }
        return count;
    }
    
     public static void dfs(int i, int j , int[][] dir, int[][] grid){
        grid[i][j] = 0;
        
        for(int d = 0; d < dir.length; d++){
            int x = i + dir[d][0];
            int y = j + dir[d][1];
            
            if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1){
                dfs(x, y, dir, grid);
            }
        }
    }
}

// dfs 를 돌면서 water 로 채운 후 남은 것만 세는 테크닉 (2) -- 가장 빠름
// static 으로 direction을 정해두고 for문을 돌리는 것이 직접 상하좌우 4번 호출하는 것보다 느림
// 이 기법은 땅이 1, 바다가 0이라는 설정이 전제되었을 때 가능함

class Solution {
    private int rows, cols;    
    private final int land = 1, water = 0;
    
    public int numEnclaves(int[][] grid) {
        this.rows = grid.length;
        this.cols = grid[0].length;
        
        // Flip islands along edges
        for (int i = 0; i < rows; i++) {
            dfs(i, 0, grid);
            dfs(i, cols - 1, grid);
        }
        
        for (int j = 0; j < cols; j++) {
            dfs(0, j, grid);
            dfs(rows - 1, j, grid);
        }
        
        // Sum all remaining island sizes surrounded by water
        int cells = 0;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                cells += grid[i][j];
        return cells;
    }
    
    private void dfs(int i, int j, int[][] grid) {
        // base case: out of bounds or hit water
        boolean outOfBounds = (i < 0 || i == rows || j < 0 || j == cols);
        if (outOfBounds || grid[i][j] == water)
            return;
        
        // sink to water and continue dfs
        grid[i][j] = water;
        
        dfs(i - 1, j, grid); // go up
        dfs(i + 1, j, grid); // go down
        dfs(i, j - 1, grid); // go left
        dfs(i, j + 1, grid); // go right
    }
}

