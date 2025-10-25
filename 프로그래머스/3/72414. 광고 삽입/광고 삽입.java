class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        
        int time = calcTime(play_time);
        long[] players = new long[time+1];
        
        for(String log : logs) {
            String[] l = log.split("-");
            int startTime = calcTime(l[0]);
            int endTime = calcTime(l[1]);
            
            players[startTime]++;
            players[endTime]--;
        }
        
        for(int i = 1; i < time; i++) {
            players[i] += players[i-1];
        }
         
        for(int i = 1; i < time; i++) {
            players[i] += players[i-1];
        }
        
        int advTime = calcTime(adv_time);
        
        long max = players[advTime];
        int startTime = 0;
        
        for(int i = 1; i <= time-advTime; i++) {
            long now = players[advTime+i-1] - players[i-1];
            if(now > max) {
                max = now;
                startTime = i;
            }
        }
        
        return toTime(startTime);
    }
    
    public int calcTime(String time) {
        String[] times = time.split(":");
        return 3600 * Integer.parseInt(times[0]) + 60 * Integer.parseInt(times[1]) + Integer.parseInt(times[2]);
    }
    
    public String toTime(int time) {
        int h = time / 3600;
        time = time % 3600;
        
        int m = time / 60;
        time = time % 60;
        
        int s = time;
        
        return String.format("%02d", h) + ":" + String.format("%02d", m) + ":" + String.format("%02d", s);
    }
}