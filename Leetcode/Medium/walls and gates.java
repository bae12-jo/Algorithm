class Solution {
    
    private int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int WALL = -1, EMPTY = Integer.MAX_VALUE, GATE = 0;
    private int N, M;
    private int[][] rooms;
    
    public void wallsAndGates(int[][] rooms) { // MAX -> empty, -1 -> wall
        
        // fill empty space from each gates
        // update min value
        // end case -> meet wall or out of rooms or meet another gate or smallest valid value
        
        // edge case
        if(rooms.length==0) return;
        N = rooms.length;
        M = rooms[0].length;        
        
        Queue<int[]> queue = new LinkedList<>();        
        // gather gates
        for(int i=0; i<N; i++){ // O(MN)
            for(int j=0; j<M; j++){
                if(rooms[i][j]==GATE) queue.add(new int[]{i, j});
            }
        }
        
        while(!queue.isEmpty()){ // O(MN)
            int[] now = queue.poll();            
            for(int i=0; i<4; i++){
                int nx = now[0]+dir[i][0];
                int ny = now[1]+dir[i][1];
                if(nx<0 || nx>=N || ny<0 || ny>=M || rooms[nx][ny]!=EMPTY) continue;
                rooms[nx][ny] = rooms[now[0]][now[1]]+1;    
                queue.add(new int[]{nx, ny});
            }
        }  
        
        
    }
}
