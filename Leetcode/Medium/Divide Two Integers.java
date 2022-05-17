class Solution {
    
    private final int HALF_MIN = Integer.MIN_VALUE/2;
    
    public int divide(int dividend, int divisor) {
        
        // edge case
        if(dividend==Integer.MIN_VALUE && divisor==-1) return Integer.MAX_VALUE;
        
        // handling signs : 2 positive, 1 negative, 0 positive
        // make dividend and divisor negative
        int negatives = 2;
        if (dividend > 0) {
            negatives--;
            dividend = -dividend;
        }
        if (divisor > 0) {
            negatives--;
            divisor = -divisor;
        }
        
        int value = divisor;
        int step = -1;
        
        while(value+value>=dividend && value>=HALF_MIN){ // in a range of dividend and Integer
            value+=value; // go smaller
            step+=step; // go smaller
        }
        
        int quotient = 0;
        while(dividend<=divisor){
            if(dividend<=value){
                quotient += step;
                dividend -= value;
            }    
            value>>=1;
            step>>=1;
        }
        
        return negatives!=1 ? -quotient : quotient;     
        
        
    }
}
