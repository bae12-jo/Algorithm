/* Top-down */

// can not start new job on the same day when prev job finished
// have to finish at least one task every day (no free day)

// 일별 난이도는 그날 종료된 일 중 최대 난이도와 동일
// 전체 난이도는 모든 요일의 난이도를 더한 것

// 최소 전체 난이도를 반환하되 없다면 -1을 반환하라

// state는 날 d와 각 날에서 수행할 첫번째 잡의 인덱스 i (jobs are dependent!!)
// base case는 d일의 최대 난이도 (별도의 공간에 미리 계산해두면 편함)

class Solution {
    
    int N, d;
    int[] highestDiff;
    int[][] cache;
    int[] jobDifficulty;
    
    public int minDifficulty(int[] jobDifficulty, int d) {
        
        N = jobDifficulty.length;
        
        // edge case - can not divide jobs into d
        if(N<d) return -1;
        
        // init
        this.d=d;
        this.jobDifficulty=jobDifficulty;
        highestDiff = new int[N];
        cache = new int[N][d+1];
        for(int[] c: cache) Arrays.fill(c, -1); // zero is valid data
        
        // get left highest difficulty based on idx
        int highest = 0;
        for(int i=N-1; i>=0; i--){
            highest = Math.max(highest, jobDifficulty[i]);
            highestDiff[i] = highest;
        }
        
        return getDiff(0, 1);
    }
    
    private int getDiff(int idx, int day){
        
        // base case - last day's highest difficulty (that's last difficulty in given array)
        if(day==d) return highestDiff[idx];
        
        if(cache[idx][day]==-1){
            int highest = 0; // highest difficulty
            int bestOption = Integer.MAX_VALUE; // min sum of difficulty
            for(int i=idx; i<N-(d-day); i++){
                highest = Math.max(highest, jobDifficulty[i]); // get today's higest
                bestOption = Math.min(bestOption, highest+getDiff(i+1, day+1)); // find the min sum case
            }
            cache[idx][day] = bestOption;
        }
        
        return cache[idx][day];
        
    }
}


/* Bottom-up */

class Solution {
    
    int N, d;
    int[] highestDiff;
    int[][] cache;
    int[] jobDifficulty;
    
    public int minDifficulty(int[] jobDifficulty, int d) {
        
        N = jobDifficulty.length;
        
        // edge case - can not divide jobs into d
        if(N<d) return -1;
        
        // init
        this.d=d;
        this.jobDifficulty=jobDifficulty;
        highestDiff = new int[N];
        
        // init dp array
        cache = new int[N][d+1];
        for(int[] c: cache) Arrays.fill(c, Integer.MAX_VALUE);
        
        // base case - fill out highest diff for last day
        cache[N-1][d] = jobDifficulty[N-1];
        for(int i=N-2; i>=0; i--) cache[i][d] = Math.max(cache[i+1][d], jobDifficulty[i]);
        
        for(int day=d-1; day>0; day--){
            for(int i=day-1; i<N-(d-day); i++){ // possible days of the day (from 1 to prev of next day)
                int highest = 0;
                for(int j=i; j<N-(d-day); j++){ // until start idx of remain days
                    highest = Math.max(highest, jobDifficulty[j]); // hardest job scheduled today
                    cache[i][day] = Math.min(cache[i][day], highest+cache[j+1][day+1]); // min diffculty job schedule starting tomorrow at the next job
                }
            }
        }
        
        return cache[0][1];        
        
    }
    
}
