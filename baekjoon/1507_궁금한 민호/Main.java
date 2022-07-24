import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node>{
        int from, to;
        int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "from=" + from +
                    ", to=" + to +
                    ", cost=" + cost +
                    '}';
        }
    }

    public static int n;
    public static int[][] map;
    public static ArrayList<Node>[] graph;
    public static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n+1][n+1];
        graph = new ArrayList[n+1];

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            graph[i] = new ArrayList<>();
            for(int j = 1; j <= n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        for(int i = 1; i <= n; i++){
            for(int j = 1; j < i; j++){
                pq.offer(new Node(i, j, map[i][j]));
            }
        }

        int ans = 0;
        while(!pq.isEmpty()){
            Node node = pq.poll();
            int from = node.from;
            int to = node.to;
            int cost = node.cost;

            dijkstra(from, to);
            if(dist[to] == cost) continue;

            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
            ans += cost;
        }

        for(int i = 1; i <= n; i++){
            dijkstra(i, -1);
            for(int j = 1; j <= n; j++){
                if(dist[j] != map[i][j]) ans = -1;
            }
        }

        System.out.println(ans);
    }

    public static void dijkstra(int start, int end){
        dist = new int[n+1];
        Arrays.fill(dist, 1000000);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();
            int to = now.to;
            int cost = now.cost;

            if(to == end) return;
            if(dist[to] < cost) continue;

            for(Node next : graph[to]){
                if(dist[next.to] > dist[to] + next.cost){
                    dist[next.to] = dist[to] + next.cost;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
        }
    }

}
