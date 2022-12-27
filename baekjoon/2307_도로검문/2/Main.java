import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node>{
        int idx;
        int x, cost;

        public Node(int x, int cost) {
            this.x = x;
            this.cost = cost;
        }

        public Node(int idx, int x, int cost) {
            this.idx = idx;
            this.x = x;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static final int INF = 987654321;
    public static int n;
    public static ArrayList<Node>[] graph;
    public static int[] route;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for(int i = 1; i <= m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(i, b, c));
            graph[b].add(new Node(i, a, c));
        }

        route = new int[m+1];
        int beginDist = dijkstra(-1, -1);

        int ans = 0;
        for (int i = 1; i <= m; i++) {
            if(route[i] != 0) ans = Math.max(ans, dijkstra(route[i], i));
        }

        System.out.println(ans == INF ? -1 : ans - beginDist);
    }

    public static int dijkstra(int exFrom, int exTo){
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));
        dist[1] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();
            int x = now.x;
            int cost = now.cost;

            if(x == n) return dist[n];

            if(dist[x] < cost) continue;

            for (Node next : graph[x]) {
                if(x == exFrom && next.x == exTo) continue;

                if(dist[next.x] > dist[x] + next.cost){
                    dist[next.x] = dist[x] + next.cost;
                    pq.offer(new Node(next.x, dist[next.x]));
                    if(exFrom == -1) route[next.x] = x;
                }
            }
        }

        return INF;
    }

}
