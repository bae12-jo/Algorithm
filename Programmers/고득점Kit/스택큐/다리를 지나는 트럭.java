import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int limit = 0, index = 0;
        Queue<Integer> bridge = new LinkedList<>();
        
        while(true){
            // if last truck is on bridge, exit loop
            if(index==truck_weights.length) break;
            
            // if queue is full, remove first truck
            if(bridge.size()==bridge_length){
                limit-=bridge.poll();   
            // if total weight of trucks is not more than limit, add truck to bridge
            }else if(limit+truck_weights[index]<=weight){
                limit+=truck_weights[index];
                bridge.offer(truck_weights[index]);
                index++;
                answer++;
            // if total weight of trucks is over limit, add 0 to bridge
            }else{
                bridge.offer(0);
                answer++;
            }
            
        }
        
        // return minutes including extra time for last truck to pass the bridge
        return answer+bridge_length;
    }
}
