class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        
        int playTime = timeToSecond(play_time);
        int advTime = timeToSecond(adv_time);
        long[] watch = new long[playTime+1];
        
        for(String log : logs) {
            String[] times = log.split("-");
            int startTime = timeToSecond(times[0]);
            int endTime = timeToSecond(times[1]);
            watch[startTime]++;
            watch[endTime]--;
        }
        
        for(int i = 0; i < playTime; i++) {
            watch[i+1] += watch[i];
        }
        
        int startTime = 0;
        int ans = 0;
        long watchTime = 0;
        long maxWatchTime = 0;
        
        while(startTime + advTime <= playTime) {
            if(maxWatchTime < watchTime) {
                maxWatchTime = watchTime;
                ans = startTime;
            }
            
            watchTime -= watch[startTime];
            watchTime += watch[startTime+advTime];
            startTime++;
        }

        return String.format("%02d:%02d:%02d", ans/3600, (ans/60)%60, ans%60);
    }
    
    public int timeToSecond(String time) {
        String[] times = time.split(":");
        int h = Integer.parseInt(times[0]) * 3600;
        int m = Integer.parseInt(times[1]) * 60;
        int s = Integer.parseInt(times[2]);
        return h+m+s;
    }
    
}