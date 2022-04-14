class Solution {
    public int solution(String dartResult) {
        
        int[] score = new int[3];
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        
        for(int i=0; i<dartResult.length(); ++i){
            switch(dartResult.charAt(i)){
                case 'S':
                    score[idx] = (int)Math.pow(Integer.parseInt(sb.toString()), 1);
                    idx++;
                    sb = new StringBuilder();
                    break;
                case 'D':
                    score[idx] = (int)Math.pow(Integer.parseInt(sb.toString()), 2);
                    idx++;
                    sb = new StringBuilder();
                    break;
                case 'T':
                    score[idx] = (int)Math.pow(Integer.parseInt(sb.toString()), 3);
                    idx++;
                    sb = new StringBuilder();
                    break;
                case '*':
                    score[idx-1] *= 2;
                    if(idx>1) score[idx-2] *= 2;
                    break;
                case '#':
                    score[idx-1] = -(score[idx-1]);
                    break;
                default:
                    sb.append(dartResult.charAt(i));
                    break;
            }
        }
                
        
        int answer = 0;
        for(int i=0; i<3; ++i){
            answer += score[i];
        }
        return answer;
    }
}
