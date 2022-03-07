package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(2, 10, 2, new String[] {"09:10", "09:09", "08:00"}));
    }

    public static String solution(int n, int t, int m, String[] timetable) {
        String answer = "";

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        Arrays.stream(timetable)
                .mapToInt(Main::toMinute)
                .forEach(i -> pq.offer(i));

        int startTime = 9 * 60;
        int lastTime = 0;
        int total = 0;

        for(int i = 0; i < n; i++){
            total = 0;

            while(!pq.isEmpty()){
                int currentTime = pq.peek();

                if(currentTime <= startTime && total < m){
                    pq.poll();
                    total++;
                }else break;

                lastTime = currentTime - 1;
            }
            startTime += t;
        }

        if(total < m) lastTime = startTime - t;

        String hour = String.format("%02d", lastTime / 60);
        String minute = String.format("%02d", lastTime % 60);

        answer = hour + ":" + minute;
        return answer;
    }

    public static int toMinute(String str){
        String[] split = str.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }

}
