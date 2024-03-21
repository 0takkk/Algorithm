class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        
        int playTimes = timeToSecond(play_time);
        int[] watch = new int[playTimes];
        
        for(String log : logs) {
            String[] times = log.split("-");
            int startTime = timeToSecond(times[0]);
            int endTime = timeToSecond(times[1]);
            
            for(int i = startTime; i < endTime; i++) {
                watch[i]++;
            }
        }
        
        int ans = 0;
        long watchTimes = 0;
        long maxWatch = 0;
        int advTime = timeToSecond(adv_time);
        
        for(int i = 0; i < advTime; i++) {
            watchTimes += watch[i];
        }
        maxWatch = watchTimes;

        for(int i = 1; i < playTimes-advTime+1; i++) {
            watchTimes -= watch[i-1];
            watchTimes += watch[i+advTime-1];
            if(maxWatch < watchTimes) {
                maxWatch = watchTimes;
                ans = i;
            }
        }
    
        return secondToTime(ans);
    }
    
    public int timeToSecond(String time) {
        String[] times = time.split(":");
        int h = Integer.parseInt(times[0]) * 3600;
        int m = Integer.parseInt(times[1]) * 60;
        int s = Integer.parseInt(times[2]);
        return h+m+s;
    }
    
    public String secondToTime(int second) {
        int h = second / 3600;
        second -= h * 3600;
        
        int m = second / 60;
        second -= m * 60;
        
        int s = second % 60;
        
        StringBuilder sb = new StringBuilder();
        if(h >= 10) {
            sb.append(h);
        } else {
            sb.append("0").append(h);
        }
        sb.append(":");
        
        if(m >= 10) {
            sb.append(m);
        } else {
            sb.append("0").append(m);
        }
        sb.append(":");
        
        if(s >= 10) {
            sb.append(s);
        } else {
            sb.append("0").append(s);
        }
        
        return sb.toString();
    }
}