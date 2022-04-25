class Solution {
    public int minMeetingRooms(int[][] intervals) {
        
        int room = 1;
        Arrays.sort(intervals, (a, b)->Integer.compare(a[0], b[0]));
        
        PriorityQueue<Integer> rooms = new PriorityQueue<Integer>();
        rooms.offer(intervals[0][1]);
        
        for(int i=1; i<intervals.length; ++i){
            if(rooms.peek()>intervals[i][0]) room++;
            else rooms.poll();
            rooms.offer(intervals[i][1]);
        }
        
        return room;
    }
}
