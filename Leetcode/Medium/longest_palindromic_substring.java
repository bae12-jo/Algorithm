class Solution {
    
    public String getPalindrome(String s, int left, int right){
        while(left>=0 && right < s.length() && s.charAt(left)==s.charAt(right)){
            left--;
            right++;
        }   
        return s.substring(left+1, right);
    }
    
    public String longestPalindrome(String s) {
        
        String longestPalindrome = "";
        
        if(s.length()<2) return s;
        
        for(int i=0; i<s.length(); ++i){
            String s1 = getPalindrome(s, i, i); // odd string
            String s2 = getPalindrome(s, i, i+1); // even string
            
            if(s1.length()>longestPalindrome.length()) longestPalindrome = s1;
            if(s2.length()>longestPalindrome.length()) longestPalindrome = s2;
        }       
        
        return longestPalindrome;
    }
}
