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

    public static int n, t;
    public static int[] dest, dist;

    public static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        while(tc-->0){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            graph = new ArrayList[n+1];
            for(int i = 1; i <= n; i++){
                graph[i] = new ArrayList<>();
            }

            while(m-->0){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int tmp = 2*d;

                if((a == g && b == h) || (a == h && b == g)){
                    graph[a].add(new Node(b, tmp-1));
                    graph[b].add(new Node(a, tmp-1));
                }
                else{
                    graph[a].add(new Node(b, tmp));
                    graph[b].add(new Node(a, tmp));
                }
            }

            dest = new int[t];
            for(int i = 0; i < t; i++){
                dest[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(dest);

            dist = new int[n+1];
            Arrays.fill(dist, 50000000);

            dijkstra(s);

            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < t; i++){
                if(dist[dest[i]] % 2 == 1) sb.append(dest[i] + " ");
            }

            System.out.println(sb.toString());
        }
    }

    public static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();
            int x = now.x;
            int cost = now.cost;

            if(dist[x] < cost) continue;

            for (Node next : graph[x]) {
                if(dist[next.x] > dist[x] + next.cost){
                    dist[next.x] = dist[x] + next.cost;
                    pq.offer(new Node(next.x, dist[next.x]));
                }
            }
        }
    }

}
