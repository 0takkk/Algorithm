import java.io.*;
import java.util.*;

public class Main {

    public static int n, m;
    public static int[] dist, dp;
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
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        dijkstra(2);

        dp = new int[n+1];
        Arrays.fill(dp, -1);
        System.out.println(rec(1));
    }

    public static int rec(int now){
        if(dp[now] != -1) return dp[now];
        if(now == 2) return dp[now] = 1;

        dp[now] = 0;

        for(Node next : graph[now]){
            int x = next.x;

            if(dist[now] > dist[x]){
                dp[now] += rec(x);
            }
        }

        return dp[now];
    }

    public static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        dist = new int[n+1];
        Arrays.fill(dist, 1000000001);
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();

            for(Node next : graph[now.x]){
                if(dist[next.x] > dist[now.x] + next.cost){
                    dist[next.x] = dist[now.x] + next.cost;
                    pq.offer(new Node(next.x, dist[next.x]));
                }
            }
        }
    }

}
