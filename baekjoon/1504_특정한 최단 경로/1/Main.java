import java.io.*;
import java.util.*;

public class Main {

    public static int n, v1, v2;
    public static int[][] map;

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
        int e = Integer.parseInt(st.nextToken());

        map = new int[n+1][n+1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(i != j){
                    map[i][j] = 1001;
                }
            }
        }

        for(int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[a][b] = map[b][a] = Math.min(map[a][b], c);
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        int ans1 = dijkstra(1, v1) + dijkstra(v1, v2) +dijkstra(v2, n);
        int ans2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, n);

        int ans = Math.min(ans1, ans2);

        if(ans >= 200000001) System.out.println(-1);
        else System.out.println(ans);
    }

    public static int dijkstra(int start, int end){
        int[] dist = new int[n+1];
        Arrays.fill(dist, 200000001);
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();

            int x = now.x;

            for(int next = 1; next <= n; next++){
                if(x != next && map[x][next] != 1001){
                    if(dist[next] > dist[x] + map[x][next]){
                        dist[next] = dist[x] + map[x][next];
                        pq.offer(new Node(next, dist[next]));
                    }
                }
            }
        }

        return dist[end];
    }

}
