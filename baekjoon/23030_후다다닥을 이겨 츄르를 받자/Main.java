import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node>{
        int to, dist;

        public Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    public static final int INF = 999999999;
    public static int n, m, size;

    public static ArrayList<Integer>[] stations;
    public static int[] dist;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        size = 50*n + 50;
        stations = new ArrayList[size+1];
        for(int i = 50; i <= size; i++){
            stations[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            int count = Integer.parseInt(st.nextToken());

            for(int j = 2; j <= count; j++){
                stations[i*50 + j-1].add(i*50 + j);
                stations[i*50 + j].add(i*50 + j-1);
            }
        }

        m = Integer.parseInt(br.readLine());

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            int q1 = Integer.parseInt(st.nextToken());
            int q2 = Integer.parseInt(st.nextToken());

            stations[p1*50 + p2].add(q1*50 + q2);
            stations[q1*50 + q2].add(p1*50 + p2);
        }

        StringBuilder sb = new StringBuilder();
        int k = Integer.parseInt(br.readLine());
        while(k-->0){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int u1 = Integer.parseInt(st.nextToken());
            int u2 = Integer.parseInt(st.nextToken());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            dist = new int[size+1];
            Arrays.fill(dist, INF);

            dijkstra(u1*50+u2, v1*50+v2, t);

            sb.append(dist[v1*50+v2]).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void dijkstra(int start, int end, int t){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(now.to == end) return;
            if(now.dist > dist[now.to]) continue;

            for (int next : stations[now.to]) {
                int cost = (now.to-1)/50 != (next-1)/50 ? t : 1;

                if(dist[next] > dist[now.to] + cost){
                    dist[next] = dist[now.to] + cost;
                    pq.offer(new Node(next, dist[next]));
                }
            }
        }
    }

}
