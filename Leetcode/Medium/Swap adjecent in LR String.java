class Solution {
    public boolean canTransform(String start, String end) {

        // 투 포인터를 이용해서 O(n) 안에 비교를 하고 싶다.
        // XL -> LX
        // RX -> XR
        
        // RLRRL
        // RLRRL
        // X가 없어진다면 두 문자열을 같아진다.
        
        // L은 원래 위치보다 오른쪽으로 갈 수 없다.
        // R은 원래 위치보다 왼쪽으로 갈 수 없다.
        
        int s = 0, e = 0; // init two pointer as 0
        
        while(s<start.length() || e<end.length()){
            
            // find first element except X in start and end each
            while(s<start.length() && start.charAt(s)=='X') s++;
            while(e<end.length() && end.charAt(e)=='X') e++;
            
            // if all chars is X in start or end, if two string consists of only X and has same length, return true
            if(s==start.length() || e==end.length()) return s==start.length() && e==end.length();
            
            // find first char on each string, which is not X
            char sChar = start.charAt(s);
            char eChar = end.charAt(e);
            
            // if it's not same, it can't move
            if(sChar != eChar) return false;
            
            // if L is on right side
            if(sChar == 'L' && s<e) return false;
            
            // if R is on left side
            if(sChar=='R' && s>e) return false;
            
            s++;
            e++;
            
        }        
        
        return true;
        
    }
}
