import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node>{
        int x;
        long cost;

        public Node(int x, long cost) {
            this.x = x;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            if(this.cost > o.cost) {
                return 1;
            }
            else {
                return -1;
            }
        }
    }

    public static int n;
    public static final long MAX = Long.MAX_VALUE;
    public static int[] road;
    public static long[] dist;
    public static ArrayList<Node>[] graph;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        road = new int[n];
        graph = new ArrayList[n];
        dist = new long[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            road[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
            dist[i] = MAX;
        }

        while(m-->0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        dijkstra();

        System.out.println(dist[n-1] == MAX ? -1 : dist[n-1]);
    }

    public static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));
        dist[0] = 0;

        while(!pq.isEmpty()) {
            Node now = pq.poll();

            if(dist[now.x] < now.cost) continue;

            for (Node next : graph[now.x]) {
                if(next.x == n-1) {
                    if(dist[next.x] > dist[now.x] + next.cost) {
                        dist[next.x] = dist[now.x] + next.cost;
                        pq.offer(new Node(next.x, dist[next.x]));
                    }
                }
                else {
                    if(road[next.x] == 0 && dist[next.x] > dist[now.x] + next.cost) {
                        dist[next.x] = dist[now.x] + next.cost;
                        pq.offer(new Node(next.x, dist[next.x]));
                    }
                }
            }
        }
    }

}