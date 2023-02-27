import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(new String[][] {
                {"15:00", "17:00"},
                {"16:40", "18:20"},
                {"14:20", "15:20"},
                {"14:10", "19:20"},
                {"18:20", "21:20"}
        }));
    }

    public static class Room implements Comparable<Room>{
        int startTime, endTime;

        public Room(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Room o) {
            return this.endTime - o.endTime;
        }

    }

    public static int solution(String[][] book_time) {
        int answer = 0;

        int n = book_time.length;
        Room[] rooms = new Room[n];

        for(int i = 0; i < n; i++){
            String[] times = book_time[i];
            rooms[i] = new Room(timeToMin(times[0]), timeToMin(times[1]) + 10);
        }

        Arrays.sort(rooms, Comparator.comparing(room -> room.startTime));

        PriorityQueue<Room> pq = new PriorityQueue<>();

        for (Room room : rooms) {
            while(!pq.isEmpty() && pq.peek().endTime <= room.startTime){
                pq.poll();
            }

            pq.offer(room);

            answer = Math.max(answer, pq.size());
        }

        return answer;
    }

    public static int timeToMin(String times){
        String[] time = times.split(":");
        return Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
    }
}
