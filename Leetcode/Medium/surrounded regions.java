import java.util.*;

class Solution {
    
	int N, M;
	boolean[][] visited;
	int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private boolean inMap(int x, int y){
        return x>=0 && x<N && y>=0 && y<M;
    }	

    private void dfs(char[][] board, int r, int c){
        
        visited[r][c]=true;
        board[r][c]='*';
        for(int i=0; i<4; i++){
            int nx = r+dir[i][0];
            int ny = c+dir[i][1];
            if(inMap(nx, ny) && !visited[nx][ny] && board[nx][ny]=='O') dfs(board, nx, ny);
        }
        
    }

    public void solve(char[][] board) {
        
        if(board.length==0) return;
        
        N = board.length;
		M = board[0].length;
        
        visited = new boolean[N][M];
        
        for(int i=0; i<N; i++){ // search only 0 col, M col
            if(board[i][0]=='O' && !visited[i][0]) dfs(board, i, 0);
            if(board[i][M-1]=='O' && !visited[i][M-1]) dfs(board, i, M-1);
        }
        
        for(int j=0; j<M; j++){ // search only 0 row, N row
            if(board[0][j]=='O' && !visited[0][j]) dfs(board, 0, j);
            if(board[N-1][j]=='O' && !visited[N-1][j]) dfs(board, N-1, j);
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                board[i][j] = board[i][j] == '*' ? 'O' : 'X';
            }	
        }
        
    }
}
