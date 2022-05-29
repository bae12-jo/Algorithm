class DetectSquares {
    
    private List<int[]> points;
    private int[][] count;

    public DetectSquares() {
        
        points = new ArrayList<>();
        count = new int[1001][1001];
        
    }
    
    public void add(int[] point) {
        
        points.add(point);
        count[point[0]][point[1]]++;
        
    }
    
    public int count(int[] point) {
        int result = 0;
        int nx = point[0];
        int ny = point[1];
        for(int[] p: points){
            int x = p[0];
            int y = p[1];
            if(Math.abs(x-nx)==0 || Math.abs(x-nx)!=Math.abs(y-ny)) continue;
            result += count[x][ny]*count[nx][y];
        }
        return result;
    }
}

/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares obj = new DetectSquares();
 * obj.add(point);
 * int param_2 = obj.count(point);
 */
