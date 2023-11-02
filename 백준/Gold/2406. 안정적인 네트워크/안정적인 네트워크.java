import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node> {
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

    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        while(m-->0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(find(a) != find(b)) {
                union(a, b);
            }
        }

        int[][] cost = new int[n+1][n+1];
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 2; i <= n; i++) {
            for(int j = i+1; j <= n; j++) {
                pq.offer(new Node(i, j, cost[i][j]));
            }
        }

        int min = 0;
        int count = 0;
        ArrayList<Node> list = new ArrayList<>();

        while(!pq.isEmpty()) {
            Node node = pq.poll();

            int x = find(node.from);
            int y = find(node.to);

            if(x == y) continue;

            parent[y] = x;
            min += node.cost;
            count++;
            list.add(node);
        }

        System.out.println(min + " " + count);
        StringBuilder sb = new StringBuilder();
        for (Node node : list) {
            sb.append(node.from).append(" ").append(node.to).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static int find(int x) {
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        parent[y] = x;
    }

}