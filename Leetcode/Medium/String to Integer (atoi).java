class Solution {
    public int myAtoi(String s) {
        
        // edge case
        if(s.length()==0) return 0;
        
        int idx = 0, res = 0;
        boolean isNegative = false;
        
        // trim for whitespace before valid value
        while(s.charAt(idx)==' ' && idx+1<s.length()) idx++;
        
        // check sign and update idx only when next char is number
        if(idx+1<s.length() && s.charAt(idx+1)-'0'>=0 && s.charAt(idx+1)-'0'<=9){
            if(s.charAt(idx)=='-'){
                isNegative = true;
                idx++;
            }else if(s.charAt(idx)=='+') idx++;
        }
        
        while(idx<s.length()){
            
            if(s.charAt(idx)-'0'<0 || s.charAt(idx)-'0'>9) break; // it's not a number
            
            int prev = res;
            res*=10;
            if(prev != res/10 || res+s.charAt(idx)-'0'<0) return isNegative==true ? Integer.MIN_VALUE : Integer.MAX_VALUE; // it's overflow
            res+=s.charAt(idx)-'0';            
            
            idx++;
        }
        
        return isNegative==true ? -res : res;
        
    }
}
