import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node>{
        int x, cost;

        public Node(int x, int cost) {
            this.x = x;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static final int MAX = 3000000;
    public static int v;
    public static ArrayList<Node>[] graph;
    public static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int k = Integer.parseInt(br.readLine());

        graph = new ArrayList[v+1];
        for(int i = 1; i <= v; i++) graph[i] = new ArrayList<>();

        while(e-->0){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
        }

        dist = new int[v+1];
        Arrays.fill(dist, MAX);

        dijkstra(k);

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= v; i++){
            if(dist[i] == MAX) sb.append("INF");
            else sb.append(dist[i]);
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(dist[now.x] < now.cost) continue;

            for (Node next : graph[now.x]) {
                if(dist[next.x] > dist[now.x] + next.cost){
                    dist[next.x] = dist[now.x] + next.cost;
                    pq.offer(new Node(next.x, dist[next.x]));
                }
            }
        }
    }
}
