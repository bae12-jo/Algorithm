class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int area = brown+yellow;
        
        int row = 0, col = 0;
        for(int i=1; i<=area; ++i){
            
            // check i is divisor of area
            if(area%i==0){
                row = i;
                col = area/i;
            }else{
                continue;
            }
            
            // row shouldn't be less than col
            if(row>col) continue;
            
            // if yellow can be made by divisors minus 2
            if((row-2)*(col-2)==yellow){
                answer[0] = col;
                answer[1] = row;
            }
        }
        
        return answer;
    }
}
