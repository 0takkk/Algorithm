import java.io.*;
import java.util.*;

public class Main {

    public static int ans = Integer.MAX_VALUE;
    public static class Pos implements Comparable<Pos>{
        int x, time;

        public Pos(int x, int time) {
            this.x = x;
            this.time = time;
        }

        @Override
        public int compareTo(Pos o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        bfs(n, k);
        System.out.println(ans);
    }

    public static void bfs(int start, int end){
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(start, 0));
        boolean[] visited = new boolean[100001];
        visited[start] = true;

        while(!pq.isEmpty()){
           Pos p = pq.poll();
           visited[p.x] = true;

           if(p.x == end){
               ans = Math.min(ans, p.time);
           }

           if(p.x * 2 < 100001 && !visited[p.x * 2]){
               pq.offer(new Pos(p.x * 2, p.time));
           }
           if(p.x + 1 < 100001 && !visited[p.x + 1]){
               pq.offer(new Pos(p.x + 1, p.time+1));
           }
           if(p.x - 1 >= 0 && !visited[p.x-1]){
               pq.offer(new Pos(p.x-1, p.time+1));
           }
        }
    }

}
