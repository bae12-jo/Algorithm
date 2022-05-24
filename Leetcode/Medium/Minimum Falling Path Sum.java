// 1d array + 1d array

class Solution {
    public int minFallingPathSum(int[][] matrix) {
        
        int n = matrix.length;    
        int[] sum = matrix[0];
        int now = 0;
        
        for(int i=1; i<n; i++){
            int[] tmpSum = new int[n];
            for(int j=0; j<n; j++){
                now = matrix[i][j];
                if(j==0) tmpSum[j] = Math.min(sum[j]+now, sum[j+1]+now);
                else if(j==n-1) tmpSum[j] = Math.min(sum[j]+now, sum[j-1]+now);
                else tmpSum[j] = Math.min(Math.min(sum[j]+now, sum[j+1]+now), sum[j-1]+now);
            }
            sum = tmpSum;
        }
        
        return Arrays.stream(sum).min().getAsInt();
    }
}

// 1d array + 2 variables

class Solution {
    public int minFallingPathSum(int[][] matrix) {
        
        int n = matrix.length;    
        int[] sum = matrix[0];
        int prev = 0, tmp = 0;
        
        for(int i=1; i<n; i++){
            prev = sum[0];
            for(int j=0; j<n; j++){
                tmp = sum[j];
                if(j==0) sum[j] = Math.min(sum[j], sum[j+1])+matrix[i][j];
                else if(j==n-1) sum[j] = Math.min(sum[j], prev)+matrix[i][j];
                else sum[j] = Math.min(Math.min(sum[j], sum[j+1]), prev)+matrix[i][j];
                prev = tmp;
            }
        }
        
        int minSum = Integer.MAX_VALUE;
        for(int i=0; i<n; i++) minSum = Math.min(minSum, sum[i]);
        return minSum;
    }
}

// no extra memory

class Solution {
    public int minFallingPathSum(int[][] matrix) {
        
        int n = matrix.length;
        
        for(int i=1; i<n; i++){
            for(int j=0; j<n; j++){
               matrix[i][j] += Math.min(matrix[i-1][j], Math.min(matrix[i-1][Math.max(0, j-1)], matrix[i-1][Math.min(n-1, j+1)]));
            }
        }
        
        return Arrays.stream(matrix[n-1]).min().getAsInt();
    }
}
