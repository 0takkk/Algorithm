import java.util.*;

class Solution {
    
    public class Music implements Comparable<Music>{
        int idx, runningTime;
        String name;
        String realMusic;
        
        public Music(int idx, int t, String n, String r) {
            idx = idx;
            runningTime = t;
            name = n;
            realMusic = r;
        }
        
        public int compareTo(Music m) {
            if(m.runningTime == this.runningTime) return this.idx - m.idx;
            return m.runningTime - this.runningTime;
        }
    }
    
    public String solution(String m, String[] musicinfos) {
        String answer = "";
        
        int len = musicinfos.length;
        Music[] musics = new Music[len];
        
        for(int i = 0; i < len; i++) {
            String[] musicinfo = musicinfos[i].split(",");
            int runningTime = getRunningTime(musicinfo[0], musicinfo[1]);
            String name = musicinfo[2];
            String realMusic = getRealMusic(runningTime, replaceMusic(musicinfo[3]));
            
            musics[i] = new Music(i, runningTime, name, realMusic);
        }
        
        ArrayList<Music> list = new ArrayList<>();
        m = replaceMusic(m);
        for(Music music : musics) {
            if(music.realMusic.contains(m)) {
                list.add(music);
            }
        }
        
        if(list.size() == 0) return "(None)";
        
        Collections.sort(list);
        return list.get(0).name;
    }
    
    public int getRunningTime(String start, String end) {
        String[] endTime = end.split(":");
        String[] startTime = start.split(":");
        
        return Integer.parseInt(endTime[0]) * 60 + Integer.parseInt(endTime[1]) 
            - (Integer.parseInt(startTime[0]) * 60 + Integer.parseInt(startTime[1]));
    }
    
    public String getRealMusic(int runningTime, String music) {
        int len = music.length();
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < runningTime / len; i++) {
            sb.append(music);
        }
        
        sb.append(music.substring(0, runningTime % len));
        return sb.toString();
    }
    
    public String replaceMusic(String music) {
        music = music.replaceAll("C#", "c");
        music = music.replaceAll("D#", "d");
        music = music.replaceAll("F#", "f");
        music = music.replaceAll("G#", "g");
        music = music.replaceAll("A#", "a");
        return music;
    }
}