class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        
        // sort array based on second element
        Arrays.sort(intervals, (a, b)->Integer.compare(a[1], b[1]));
        
        // initailize prev, count
        int prev = 0, count = 0;
        
        for(int i=1; i<intervals.length; ++i){
            // if interval is overlapping
            if(intervals[prev][1]>intervals[i][0]){
                // if(intervals[prev][1]>intervals[i][1]) prev = i; - 두번째 원소 기준으로 정렬했으므로 해줄 필요가 없음
                count++;
            // if interval is non-overlapping
            }else{
                prev = i;
            }
        }
        
        return count;
        
    }
}
