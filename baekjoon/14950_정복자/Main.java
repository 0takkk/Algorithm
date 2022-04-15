import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static int[] parent;
    public static PriorityQueue<Node> pq;

    public static class Node implements Comparable<Node>{
        int x, y, cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
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
        int m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for(int i = 1; i <= n; i++){
            parent[i] = i;
        }
        pq = new PriorityQueue<>();

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pq.offer(new Node(a, b, c));
            pq.offer(new Node(b, a, c));
        }

        int cost = mst();
        for(int i = 1; i < n-1; i++){
            cost += (t * i);
        }

        System.out.println(cost);
    }

    public static int mst(){
        int cost = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();
            int x = now.x;
            int y = now.y;

            if(find(x) == find(y)) continue;

            union(x, y);
            cost += now.cost;
        }

        return cost;
    }

    public static int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y){
        x = find(x);
        y = find(y);

        parent[y] = x;
    }

}
