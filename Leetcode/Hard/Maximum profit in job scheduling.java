class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        
        if(profit.length==1) return profit[0];
        
        PriorityQueue<int[]> startHeap = new PriorityQueue<>((a, b)->Integer.compare(a[0], b[0])); // 시작시간 순으로 오름차 정렬
        PriorityQueue<int[]> endHeap = new PriorityQueue<>((a, b)->Integer.compare(a[0], b[0])); // 끝나는 시간 순으로 오름차 정렬
        
        for(int i=0; i<profit.length; i++){
            int[] now = new int[3];
            now[0] = startTime[i];
            now[1] = endTime[i];
            now[2] = profit[i];
            startHeap.offer(now);
        }
        
        int maxProfit = 0;
        
        while(!startHeap.isEmpty()){
            int[] job = startHeap.poll();
            
            while(!endHeap.isEmpty() && job[0]>=endHeap.peek()[0]){ // get max profit before start new job
                maxProfit = Math.max(maxProfit, endHeap.poll()[1]); // update max profit of removed job
            }
            
            int[] combine = new int[2];
            combine[0] = job[1]; // end time
            combine[1] = job[2]+maxProfit; // update profit
            
            endHeap.offer(combine);
        }
        
        
        while(!endHeap.isEmpty()){
            maxProfit = Math.max(maxProfit, endHeap.poll()[1]);
        }
        
        return maxProfit;
        
    }
}
