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

    public static int n, m, ans;
    public static ArrayList<Node>[] list;
    public static int[] dist, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, c));
            list[b].add(new Node(a, c));
        }

        dp = new int[n+1];
        dist = new int[n+1];
        Arrays.fill(dist, 2000000000);

        dijkstra(2);

        System.out.println(dp[1]);
    }

    public static void dijkstra(int start){
        dist[start] = 0;
        dp[start] = 1;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(dist[now.x] < now.cost) continue;

            for (Node next : list[now.x]) {
                if(dist[next.x] > dist[now.x] + next.cost){
                    dist[next.x] = dist[now.x] + next.cost;
                    pq.offer(new Node(next.x, dist[next.x]));
                }
                if(dist[next.x] > dist[now.x]) dp[next.x] += dp[now.x];
            }
        }
    }

}
