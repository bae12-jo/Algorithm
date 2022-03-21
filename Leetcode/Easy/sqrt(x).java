class Solution {
    public int mySqrt(int x) {
        
        if (x<2) return x;
        
        int mid, left = 2, right = x >> 1;
        long power;
        while(left<=right){
            mid = left + ((right-left) >> 1);
            power = (long) mid*mid;
            if(power > x) right = mid-1;
            else if(power < x) left = mid+1;
            else return mid;
        }
        
        return right;
        
    }
}
