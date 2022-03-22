class Solution {
    
    public boolean isPalindrome(String s) {
        
        int first = 0;
        int second = s.length()-1;
        
        while(first<second){
            while(first<second && !(Character.isLetterOrDigit(s.charAt(first)))) first++;
            while(first<second && !(Character.isLetterOrDigit(s.charAt(second)))) second--;
            
            if(Character.toLowerCase(s.charAt(first))!=Character.toLowerCase(s.charAt(second))) return false;
            
            first++;
            second--;
            
        }
        
        return true;
        
    }
}
