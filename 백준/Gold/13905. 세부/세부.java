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
            return o.cost - this.cost;
        }
    }

    public static int n;
    public static ArrayList<Node>[] graph;
    public static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        dist = new int[n+1];
        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        while(m-->0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        dijkstra(s);
        System.out.println(dist[e]);
    }

    public static void dijkstra(int s) {
        boolean[] visited = new boolean[n+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(s, 0));
        dist[s] = 987654321;

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(visited[now.x]) continue;
            visited[now.x] = true;

            for (Node next : graph[now.x]) {
                if(!visited[next.x]) {
                    dist[next.x] = Math.max(dist[next.x], Math.min(next.cost, dist[now.x]));
                    pq.offer(new Node(next.x, dist[next.x]));
                }
            }
        }
    }

}