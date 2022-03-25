import java.io.*;
import java.util.*;

public class Main {

    public static int n, m, dist[], route[];
    public static ArrayList<Node>[] graph;

    public static class Node implements Comparable<Node>{
        int x, weight;

        public Node(int x, int weight) {
            this.x = x;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, w));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        route = new int[n+1];

        dijkstra(start, end);

        ArrayList<Integer> routes = new ArrayList<>();
        int now = end;
        while(now != 0){
            routes.add(now);
            now = route[now];
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dist[end] + "\n");
        sb.append(routes.size() + "\n");
        for(int i = routes.size()-1; i >= 0; i--){
            sb.append(routes.get(i) + " ");
        }

        System.out.println(sb.toString());
    }

    public static void dijkstra(int start, int end){
        boolean[] visited = new boolean[n+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;
        route[start] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(visited[now.x]) continue;
            visited[now.x] = true;

            if(now.x == end) return;

            for(Node next : graph[now.x]){
                if(dist[next.x] > dist[now.x] + next.weight){
                    dist[next.x] = dist[now.x] + next.weight;
                    pq.offer(new Node(next.x, dist[next.x]));
                    route[next.x] = now.x;
                }
            }
        }
    }
}
