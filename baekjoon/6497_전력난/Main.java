import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node> {
        int from;
        int to;
        int cost;

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

    public static int n;
    public static int[] parent;
    public static PriorityQueue<Node> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        while(true){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0) break;

            long total = 0;

            parent = new int[n];
            for(int i = 0; i < n; i++)
                parent[i] = i;

            pq = new PriorityQueue<>();

            while(m-->0){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                pq.offer(new Node(a, b, c));
                total += c;
            }

            sb.append(total - mst() + "\n");
        }
        System.out.println(sb.toString());
    }

    public static long mst(){
        long sum = 0;

        while(!pq.isEmpty()){
            Node node = pq.poll();
            int from = node.from;
            int to = node.to;

            if(find(from) == find(to)) continue;

            union(from, to);
            sum += node.cost;
        }

        return sum;
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
