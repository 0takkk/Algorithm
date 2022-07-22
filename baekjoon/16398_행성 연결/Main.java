import java.io.*;
import java.util.*;

public class Main {

    public static int[] parent;

    public static class Node implements Comparable<Node>{
        int from, to, cost;

        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
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

        int n = Integer.parseInt(br.readLine());

        int[][] map = new int[n+1][n+1];
        parent = new int[n+1];

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            parent[i] = i;
            for(int j = 1; j <= n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 1; i <= n; i++){
            for(int j = 1; j < i; j++){
                pq.offer(new Node(i, j, map[i][j]));
            }
        }

        long ans = 0;

        while(!pq.isEmpty()){
            Node node = pq.poll();
            int from = node.from;
            int to = node.to;
            int cost = node.cost;

            if(find(from) != find(to)){
                union(from, to);
                ans += cost;
            }
        }

        System.out.println(ans);
    }

    public static int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x != y) parent[y] = x;
    }

}
