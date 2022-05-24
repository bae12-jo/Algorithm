class Solution {
    public int numDecodings(String s) {
        
        // edge case
        if(s.charAt(0)-'0'==0) return 0;
        
        // base case - whatever prev is, count 1
        int oneDigit = 1;
        int twoDigit = 1;
        
        int n = s.length();
        
        for(int i=1; i<s.length(); i++){
            
            int curr = 0;
            if(s.charAt(i)!='0') curr = oneDigit; // skip 0
            
            int append = Integer.parseInt(s.substring(i-1, i+1));
            if(append>=10 && append<=26) curr += twoDigit; // check two digit can make a char
            
            twoDigit = oneDigit;
            oneDigit = curr; // update with curr
        }
        
        return oneDigit;
    }
}
