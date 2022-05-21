// 2D array

class Solution {
    public int maximalSquare(char[][] matrix) {
        
        // extra space를 포함한 배열 만들기
        // 베이스케이스는 0이 될 것 right bottom을 채울 거니까 0 인덱스가 base case가 되어야함
        
        // 2중 for문을 돌면서 탐색 하되 (시작 위치는? base case를 제외한 위치, 즉 1)
        // 그자리가 1이라면 1더하기 + row-1, col-1, row-1, col-1 중 미니멈을 구해서 더하기
        // 캐시는 어디에서? -> dp배열을 채우는 방향을 잘 볼 것 rigth bottom을 채울거라면 left top -> right bottom 으로 확장해야 채워짐
        
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] cache = new int[N+1][M+1];
        int maxLen = 0;
        
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                if(matrix[i-1][j-1]=='1') cache[i][j] += Math.min(Math.min(cache[i-1][j], cache[i][j-1]), cache[i-1][j-1])+1;
                maxLen = Math.max(maxLen, cache[i][j]);
            }
        }
        
        return maxLen*maxLen; // 면적을 구하는 것이라는 거 잊지 말기
    }
}


// 1D array

class Solution {
    public int maximalSquare(char[][] matrix) {
        
        // extra space를 포함한 배열 만들기
        // 베이스케이스는 0이 될 것 right bottom을 채울 거니까 0 인덱스가 base case가 되어야함
        
        // 2중 for문을 돌면서 탐색 하되 (시작 위치는? base case를 제외한 위치, 즉 1)
        // 그자리가 1이라면 1더하기 + row-1, col-1, row-1, col-1 중 미니멈을 구해서 더하기
        // 캐시는 어디에서? -> dp배열을 채우는 방향을 잘 볼 것 rigth bottom을 채울거라면 left top -> right bottom 으로 확장해야 채워짐
        
        int N = matrix.length;
        int M = matrix[0].length;
        int[] cache = new int[M+1];
        int maxLen = 0, prev = 0;
        
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                int tmp = cache[j]; // matrix에서는 j-1의 위치
                if(matrix[i-1][j-1]=='1'){
                    // cache[j-1]은 직전에 업데이트되었고, cache[j]는 한 row 전에 업데이트 된 것, prev는 left top의 값을 지님
                    cache[j] = Math.min(Math.min(cache[j], cache[j-1]), prev)+1;
                    maxLen = Math.max(maxLen, cache[j]);
                }
                else cache[j] = 0;
                prev = tmp;
            }
        }
        
        return maxLen*maxLen; // 면적을 구하는 것이라는 거 잊지 말기
    }
}
