package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(new String[] {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"}));

    }


    public static int solution(String[] lines) {
        if(lines.length == 1) return 1;

        int[][] times= new int[lines.length][2];

        for(int i = 0; i < lines.length; i++){
            String[] str = lines[i].split(" ");
            int endTime = getTime(str[1]);
            int processingTime = (int)(Double.parseDouble(str[2].substring(0,str[2].length()-1)) * 1000);
            int startTime = endTime - processingTime + 1;

            times[i][0] = startTime;
            times[i][1] = endTime;
        }

        int max = 0;

        for(int i = 0; i < times.length-1; i++){
            int tmpStartTime = times[i][1];
            int tmpEndTime = tmpStartTime + 999;
            int count = 1;

            for(int j = i+1; j < times.length; j++){
                if((times[j][0] >= tmpStartTime && times[j][0] <= tmpEndTime) 
                        || (times[j][1] >= tmpStartTime && times[j][1] <= tmpEndTime) 
                        || (times[j][0] <= tmpStartTime && times[j][1] >= tmpEndTime)){
                    count++;
                }
            }

            max = Math.max(max, count);
        }

        return max;
    }

    public static int getTime(String str){
        int time = 0;
        String[] tmp = str.split(":");
        time += Integer.parseInt(tmp[0]) * 60 * 60 * 1000;
        time += Integer.parseInt(tmp[1]) * 60 * 1000;
        time += (int)(Double.parseDouble(tmp[2]) * 1000);

        return time;
    }

}
