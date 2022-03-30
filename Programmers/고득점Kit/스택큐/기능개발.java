import java.util.*;

class Solution {
    public List<Integer> solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        
       // If there is no function to do, return 0
        if(progresses.length<=0) {
            answer.add(0);
            return answer;
        }
        
      
      // Get how long day need to done each process
        int[] deployDay = new int[progresses.length];
        
        for(int i=0; i<progresses.length; ++i){
            deployDay[i] = ((100-progresses[i])+(100-progresses[i]-1))/speeds[i];
        }
        
      // Count the number of process per each deploy day
        int count = 1;
        int deploy = deployDay[0];
        for(int i=1; i<deployDay.length; ++i){
            if(deployDay[i]<=deploy){
                count++;
            }else{
                answer.add(count);
                count=1;
                deploy = deployDay[i];
            }
            if(i==deployDay.length-1) answer.add(count);
        }
        
        return answer;
    }
}
