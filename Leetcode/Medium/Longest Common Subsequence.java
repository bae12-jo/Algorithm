class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        
        // make text1 shorter one always
        if(text2.length() < text1.length()){
            String tmp = text1;
            text1 = text2;
            text2 = tmp;
        }
        
        // define cache with 1D array
        int[] cache = new int[text1.length()+1]; // extra space for base case
        int[] tmp = new int[text1.length()+1];
        
        for(int i=text2.length()-1; i>=0; i--){
            for(int j=text1.length()-1; j>=0; j--){
                if(text2.charAt(i)==text1.charAt(j)){
                    tmp[j] = 1 + cache[j+1];
                }else{
                    tmp[j] = Math.max(cache[j], tmp[j+1]);
                }
            }
            int[] previous = cache;
            cache = tmp;
            tmp = previous;
            
        }
        
        return cache[0];
    }
}
