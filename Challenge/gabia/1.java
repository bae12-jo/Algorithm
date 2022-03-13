// 32.3/40 (wrong answer)

class Solution {
    public int solution(int mod1, int mod2, int max_range) {
        int answer = 0;

        if(mod1==mod2) return 0;
        if(max_range==1){
            if(mod2==1) return 0;
            else return 1;
        } 

        if(mod1>mod2 && mod1%mod2==0){
            answer = (max_range/mod1) - (max_range/mod2);
        }else if(mod1<mod2 && mod2%mod1==0){
            answer = (max_range/mod1) - (max_range/mod2);
        }else{
            answer = (max_range/mod1) - (max_range/(mod1*mod2));
        }

        if(answer<0) answer=0;

        return answer;
    }
}
