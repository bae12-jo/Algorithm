import java.util.*;

class Solution {
    
    static class Work implements Comparable<Work>{
        int arrivalTime;
        int workTime;
        
        Work(int a, int w){
            this.arrivalTime = a;
            this.workTime = w;
        }
        
        public int compareTo(Work w){
            if(this.workTime == w.workTime) return this.arrivalTime - w.arrivalTime;
            return this.workTime - w.workTime;
        }
        
    }
    
    public int solution(int[][] jobs) {
        
        int answer = 0;
        int currentTime = 0; //
        int doneCount = 0; // the number of work done 
        int index = 0; // find ouf a series of work after certain time
        
        // Sort jobs in order of arriveTime
        Arrays.sort(jobs, (j1, j2) -> j1[0] - j2[0]);
        
        // PQ in order of workTime
        PriorityQueue<Work> queue = new PriorityQueue<Work>();
        
        while(doneCount<jobs.length){
            
            // get works arrived before currentTime
            while(index<jobs.length && jobs[index][0]<=currentTime){
                queue.offer(new Work(jobs[index][0], jobs[index][1]));
                index++;
            }
            
            // Synchronize the current time with the work with the earliest arrival time
            if(queue.isEmpty()) {
                currentTime = jobs[index][0];
            // Update total work time and current time
            }else{            
                Work work = queue.poll();
                answer += work.workTime + currentTime - work.arrivalTime;
                currentTime += work.workTime;
                doneCount++;            
            }
            
        }

        
        return answer/jobs.length;
    }
}
