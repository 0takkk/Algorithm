import java.io.*;
import java.util.*;

public class Main {

    public static class Room implements Comparable<Room>{
        String food;
        int depth;
        HashSet<Room> unders;

        public Room(String food, int depth, HashSet<Room> unders) {
            this.food = food;
            this.depth = depth;
            this.unders = unders;
        }

        @Override
        public int compareTo(Room o) {
            if(this.depth == o.depth) return this.food.compareTo(o.food);
            return o.depth - this.depth;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Room enter = new Room("entrance", -1, new HashSet<>());

        while(n-->0){
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            Room parent = enter;

            for(int i = 0; i < m; i++){
                String food = st.nextToken();
                Room now = new Room(food, i, new HashSet<>());
                Optional<Room> room = parent.unders.stream().filter(f -> f.food.equals(food)).findAny();

                if(room.isEmpty()) {
                    parent.unders.add(now);
                    parent = now;
                }
                else {
                    parent = room.get();
                }
            }
        }

        PriorityQueue<Room> pq = new PriorityQueue<>();
        enter.unders.stream().forEach(pq::add);

        StringBuilder sb = new StringBuilder();

        while(!pq.isEmpty()){
            Room now = pq.poll();
            sb.append("--".repeat(now.depth)).append(now.food + "\n");

            for(Room next : now.unders){
                pq.offer(next);
            }
        }

        System.out.println(sb.toString());
    }

}
