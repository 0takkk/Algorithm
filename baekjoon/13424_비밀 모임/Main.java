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

    public static final int INF = 987654321;
    public static int n, k;
    public static ArrayList<Node>[] graph;
    public static int[] people;
    public static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while(t-->0){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            graph = new ArrayList[n+1];
            for(int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

            while(m-->0){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                graph[a].add(new Node(b, c));
                graph[b].add(new Node(a, c));
            }

            k = Integer.parseInt(br.readLine());
            people = new int[k];
            dist = new int[k][n+1];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < k; i++) {
                people[i] = Integer.parseInt(st.nextToken());
                Arrays.fill(dist[i], INF);

                dijkstra(i);
            }

            int ans = 0;
            int min = INF;

            for(int j = 1; j <= n; j++){
                int sum = 0;
                for(int i = 0; i < k; i++){
                    sum += dist[i][j];
                }

                if(sum < min){
                    min = sum;
                    ans = j;
                }
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void dijkstra(int idx){
        int start = people[idx];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[idx][start] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();
            int x = now.x;
            int cost = now.cost;

            if(dist[idx][x] < cost) continue;

            for (Node next : graph[x]) {
                if(dist[idx][next.x] > dist[idx][x] + next.cost){
                    dist[idx][next.x] = dist[idx][x] + next.cost;
                    pq.offer(new Node(next.x, dist[idx][next.x]));
                }
            }
        }
    }
}
