import java.util.*;

class Solution {
	public int solution(int m, int n, String[] board) {
		int answer = 0;
		char[][] map = new char[m][n];
		
        // convert string to char array (for convienece)
		for(int i = 0 ; i < m ; ++i) {
			map[i] = board[i].toCharArray();
		}
		
		while(true) {
			int cnt = findMatrixtoRemove(m, n, map);
			if(cnt == 0) break;
			answer += cnt;
			
			replaceBlock(m, n, map);
		}
		
		return answer;
	}
	
  // replace block to empty space
	private void replaceBlock(int m, int n, char[][] map) {
		for(int c = 0 ; c < n ; ++c) {
			for(int r = m - 1 ; r >= 0 ; --r) { // 아래부터 올라오는 것 주의
				if(map[r][c] == '.') {
					for(int nr = r - 1 ; nr >= 0 ; --nr) {
						if(map[nr][c] != '.') {
							map[r][c] = map[nr][c];
							map[nr][c] = '.';
							break;
						}
					}
				}
			}
		}
	}

    // find blocks to remove
	private int findMatrixtoRemove(int m, int n, char[][] map) {
		int cnt = 0;
		boolean[][] isMatrix  = new boolean[m][n];
		
		for(int i = 0 ; i < m - 1 ; ++i) { // 인덱스에 -1 해주는 것 주의
			for(int j = 0 ; j < n - 1 ; ++j) {
				if(map[i][j] == '.') continue;
				checkFourBlock(map, isMatrix , i, j);
                
			}
		}
		
		for(int i = 0 ; i < m ; ++i) {
			for(int j = 0 ; j < n ; ++j) {
				if(isMatrix [i][j]) {
					cnt++;
					map[i][j] = '.';
				}
			}
		}
		
		return cnt;
	}

    // check if four blocks are same or not
	private void checkFourBlock(char[][] map, boolean[][] isMatrix , int i, int j) {
		char block = map[i][j];
		
		for(int r = i ; r < i + 2 ; ++r) {
			for(int c = j ; c < j + 2 ; ++c) {
				if(map[r][c] != block) return;
                // else isMatrix[i][j] = true; -- 아직 매트릭스 완성되지 않았으므로 체크하면 안 됨
			}
		}
		
		for(int r = i ; r < i + 2 ; ++r) {
			for(int c = j ; c < j + 2 ; ++c) {
				isMatrix [r][c] = true;
			}
		}
	}
}
