import java.io.*;
import java.util.*;

public class Main {

    public static class Pos{
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

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
    
    public static int n;
    public static ArrayList<Node>[] graph;
    public static HashMap<Integer, Pos> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        while (m-->0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        map = new HashMap<>();
        dijkstra();

        System.out.println(map.size());
        StringBuilder sb = new StringBuilder();
        map.values().forEach(p -> sb.append(p.x).append(" ").append(p.y).append("\n"));
        System.out.println(sb.toString());
    }

    public static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));

        int[] dist = new int[n+1];
        Arrays.fill(dist, 1000000);
        dist[1] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();
            int x = now.x;
            int cost = now.cost;

            if(dist[x] < cost) continue;

            for (Node next : graph[x]) {
                if(dist[next.x] > dist[x] + next.cost){
                    dist[next.x] = dist[x] + next.cost;
                    pq.offer(new Node(next.x, dist[next.x]));
                    map.put(next.x, new Pos(x, next.x));
                }
            }
        }
    }

}
