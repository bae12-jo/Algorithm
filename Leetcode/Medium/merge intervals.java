class Solution {
    public int[][] merge(int[][] intervals) {
        
        LinkedList<int[]> res = new LinkedList<int[]>();
        
        // 1. sort array based on first element
        Arrays.sort(intervals, (a, b)->Integer.compare(a[0], b[0]));
        
        // 2. define prev to store last overlapping intervals
        int prev = 0, start = intervals[0][0], end = intervals[0][1];
        
        // 3. check if interval is overlapping or not
        //    by comparing second one of prev with first one of new intervals
        //      s of prev > f of new -> overlapping
        for(int[] interval: intervals){
            // if overlapping, update end
            if(!res.isEmpty() && res.getLast()[1]>=interval[0]){
                res.getLast()[1] = Math.max(res.getLast()[1], interval[1]);
            // else update start, end, prev
            }else{
                res.add(interval);                
            }
        }

        return res.toArray(new int[res.size()][]);

        
    }
}
