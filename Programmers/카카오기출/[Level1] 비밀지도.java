class Solution {
    
    public String[] solution(int n, int[] arr1, int[] arr2) {
        
        int[] map = new int[n];
        String[] answer = new String[n];
        for(int i=0; i<n; ++i){
            String result = Integer.toBinaryString(arr1[i] | arr2[i]);
            while(result.length()!=n){
                result = '0' + result;
            }
            StringBuilder sb = new StringBuilder();
            for(int j=0; j<n; ++j){
                if(result.charAt(j)=='0') sb.append(" ");
                else sb.append("#");
            }
            answer[i] = sb.toString();
        }        
        return answer;
    }
}
