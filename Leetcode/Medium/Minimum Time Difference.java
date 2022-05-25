class Solution {
    
    private int convertFormat(String s){
        int hour = Integer.valueOf(s.substring(0, 2))*60;
        int min = Integer.valueOf(s.substring(3, 5));
        return hour+min;
    }
    
    public int findMinDifference(List<String> timePoints) {
        
        int n = timePoints.size();
        int[] time = new int[n];
        for(int i=0; i<n; i++){
            time[i] = convertFormat(timePoints.get(i));
        }
        Arrays.sort(time);
        
        int minGap = Integer.MAX_VALUE;
        for(int i=0; i<n-1; i++){
            minGap = Math.min(time[i+1]-time[i], minGap);
        }
        
        return Math.min(minGap, time[0]+(1440-time[n-1]));
        
    }
}
