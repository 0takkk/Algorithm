import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node>{
        int x;
        long cost, shy;

        public Node(int x, long cost) {
            this.x = x;
            this.cost = cost;
        }

        public Node(int x, long cost, long shy) {
            this.x = x;
            this.cost = cost;
            this.shy = shy;
        }

        @Override
        public int compareTo(Node o) {
            if(this.shy - o.shy > 0) return 1;
            else if(this.shy - o.shy < 0) return -1;
            return 0;
        }

    }

    public static int n, c;
    public static ArrayList<Node>[] graph;
    public static long[] shame;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());

            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        shame = new long[n+1];
        Arrays.fill(shame, Long.MAX_VALUE);
        System.out.println(dijkstra(start, end));
    }

    public static long dijkstra(int start, int end){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0, 0));
        shame[start] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(now.x == end) return shame[now.x];

            if(shame[now.x] < now.shy) continue;

            for (Node next : graph[now.x]) {
                if(shame[next.x] > Math.max(shame[now.x], next.cost) && now.cost + next.cost <= c){
                    shame[next.x] = Math.max(shame[now.x], next.cost);
                    pq.offer(new Node(next.x, now.cost + next.cost, shame[next.x]));
                }
            }
        }
        return -1;
    }

}
