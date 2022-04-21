import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static ArrayList<Node>[] graph;

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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();

        for(int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int ans1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, n);
        int ans2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, n);

        int ans = Math.min(ans1, ans2);
        System.out.println(ans >= 200000000 ? -1 : ans);
    }

    public static int dijkstra(int start, int end){
        int[] dist = new int[n+1];
        Arrays.fill(dist, 200000000);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();

            for(Node next : graph[now.x]){
                if(dist[next.x] > dist[now.x] + next.cost){
                    dist[next.x] = dist[now.x] + next.cost;
                    pq.offer(new Node(next.x, dist[next.x]));
                }
            }
        }

        return dist[end];
    }

}
