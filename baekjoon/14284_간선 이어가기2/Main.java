import java.io.*;
import java.util.*;

public class Main {

    public static class Edge implements Comparable<Edge>{
        int to, cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static ArrayList<Edge>[] graph;
    public static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, cost));
            graph[to].add(new Edge(from, cost));
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        System.out.println(dijkstra(s, t));
    }

    public static int dijkstra(int start, int end){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Edge now = pq.poll();

            if(now.to == end) return dist[now.to];
            if(dist[now.to] < now.cost) continue;

            for (Edge next : graph[now.to]) {
                if(dist[next.to] > dist[now.to] + next.cost){
                    dist[next.to] = dist[now.to] + next.cost;
                    pq.offer(new Edge(next.to, dist[next.to]));
                }
            }
        }

        return -1;
    }

}
