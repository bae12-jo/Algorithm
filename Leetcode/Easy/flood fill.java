class Solution {
    
    static int[][] D = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // up, right, down, left
    static int nowColor;
    
    public static void dfs(int[][] image, int x, int y, int newColor){
        for(int i=0; i<4; ++i){
            int nx = x + D[i][0];
            int ny = y + D[i][1];
            if(nx<0 || ny<0 || nx>=image.length || ny>=image[0].length) continue;
            if(image[nx][ny]==nowColor){
                image[nx][ny] = newColor;
                dfs(image, nx, ny, newColor);
            }
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        
        nowColor = image[sr][sc];
        
        if(newColor==nowColor) return image;
        
        image[sr][sc] = newColor;
        
        dfs(image, sr, sc, newColor);
        
        return image;

    }
}
