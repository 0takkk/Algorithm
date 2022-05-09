import java.io.*;
import java.util.*;

public class Main {

    public static class Room implements Comparable<Room>{
        String food;
        int depth;
        HashMap<String, Room> unders;  // 밑에 층 food 이름, 밑에 층 Room

        public Room(String food, int depth, HashMap<String, Room> unders) {
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
        Room enter = new Room("entrance", -1, new HashMap<>());

        while(n-->0){
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            Room parent = enter;

            for(int i = 0; i < m; i++){
                String food = st.nextToken();
                Room now = new Room(food, i, new HashMap<>());

                if(!parent.unders.containsKey(food)){
                    parent.unders.put(food, now);
                    parent = now;
                }
                else{
                    parent = parent.unders.get(food);
                }
            }
        }

        PriorityQueue<Room> pq = new PriorityQueue<>();
        enter.unders.values().forEach(val -> pq.add(val));

        StringBuilder sb = new StringBuilder();

        while(!pq.isEmpty()){
            Room now = pq.poll();
            sb.append("--".repeat(now.depth)).append(now.food + "\n");

            for(Room next : now.unders.values()){
                pq.offer(next);
            }
        }

        System.out.println(sb.toString());
    }

}
