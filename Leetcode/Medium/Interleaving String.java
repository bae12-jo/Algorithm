// Top-down

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        
        // edge case
        if(s1.length()+s2.length()!=s3.length()) return false;
        
        int[][] memo = new int[s1.length()][s2.length()];
        for(int[] m: memo) Arrays.fill(m, -1);
        
        return helper(s1, 0, s2, 0, s3, 0, memo);
    }
    
    private boolean helper(String s1, int idx1, String s2, int idx2, String s3, int idx3, int[][] memo){
        
        // end case
        if(s1.length()==idx1) return s2.substring(idx2).equals(s3.substring(idx3));
        if(s2.length()==idx2) return s1.substring(idx1).equals(s3.substring(idx3));
        
        // memoization
        if(memo[idx1][idx2]>=0) return memo[idx1][idx2]==1?true:false;
        
        boolean ans = false;
        if(s3.charAt(idx3)==s1.charAt(idx1) && helper(s1, idx1+1, s2, idx2, s3, idx3+1, memo) || s3.charAt(idx3)==s2.charAt(idx2) && helper(s1, idx1, s2, idx2+1, s3, idx3+1, memo)) ans = true;
        
        memo[idx1][idx2] = ans ? 1 : 0;
        
        return ans;
    }
}

// Top-down

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        Boolean[][] dp = new Boolean[s1.length()+1][s2.length()+1];
        boolean x = concat_mem(dp, s1, s2, s3, s1.length(), s2.length(), s3.length());
        return x;
    }
    
    public boolean concat_mem(Boolean[][] dp, String s1, String s2, String s3, int n, int m, int l){
        if(l==0 && (n!=0 || m!=0)) return false;
        if(l==0) return true;
        if((n==0 && m==0) && l!=0) return false;
        if(dp[n][m]!=null){
            return dp[n][m];
        }
        boolean a1 = false;
        boolean a2 = false;
        if(n>0 && m>0 && s1.charAt(n-1)==s2.charAt(m-1) && s1.charAt(n-1)==s3.charAt(l-1)){
            a1 = concat_mem(dp, s1, s2, s3, n-1, m, l-1);
            a2 = concat_mem(dp, s1, s2, s3, n, m-1, l-1);
        }else if(n>0 && s1.charAt(n-1)==s3.charAt(l-1)){
            a1 = concat_mem(dp, s1, s2, s3, n-1, m, l-1);
        }else if(m>0 && s2.charAt(m-1)==s3.charAt(l-1)){
            a2 = concat_mem(dp, s1, s2, s3, n, m-1, l-1);
        }
        dp[n][m] = a1 || a2;
        return dp[n][m];
    }
}

// Bottom-up

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        
        // edge case
        if(s1.length()+s2.length()!=s3.length()) return false;
        
        // init
        boolean[] memo = new boolean[s2.length()+1];
        
        for(int i=0; i<=s1.length(); i++){
            for(int j=0; j<=s2.length(); j++){
                if(i==0 && j==0){ // base case
                    memo[j] = true;
                }else if(i==0){ // s3 contains only s2
                    memo[j] = memo[j-1] && s2.charAt(j-1)==s3.charAt(i+j-1);
                }else if(j==0){ // s3 contains only s1
                    memo[j] = memo[j] && s1.charAt(i-1)==s3.charAt(i+j-1);
                }else{
                    memo[j] = memo[j-1] && s2.charAt(j-1)==s3.charAt(i+j-1) || memo[j] && s1.charAt(i-1)==s3.charAt(i+j-1);
                }
            }
        }
        
        return memo[s2.length()];
        
    }
}
