import java.util.*;

class Solution {
    
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    
    public int[] solution(String[] operations) {
        
        // set [0, 0] as default when all queues are empty
        int[] answer = {0, 0};
        
        for(String oper: operations){
            
            String[] command = oper.split(" ");
            
            // insert number to two queues
            if(command[0].equals("I")){
                minHeap.offer(Integer.parseInt(command[1]));
                maxHeap.offer(Integer.parseInt(command[1]));
            }
            
            if(command[0].equals("D")){
                if(!minHeap.isEmpty() && !maxHeap.isEmpty()){
                    // remove maximum number
                    if(command[1].equals("1")){
                        int max = maxHeap.poll();
                        minHeap.remove(max);
                    // remove minimum number
                    }else{
                        int min = minHeap.poll();
                        maxHeap.remove(min);
                    }
                }
            }
            
        }
        
        // when queues are not empty, update answer with max and min number
        if(!minHeap.isEmpty() && !maxHeap.isEmpty()){
            answer[0] = maxHeap.peek();
            answer[1] = minHeap.peek();
        }
        
        return answer;
    }
}
