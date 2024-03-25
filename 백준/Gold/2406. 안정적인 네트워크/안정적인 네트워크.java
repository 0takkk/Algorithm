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

            union(a, b);
        }

        int[][] arr = new int[n+1][n+1];
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 2; i <= n; i++) {
            for(int j = i+1; j <= n; j++) {
                pq.offer(new Node(i, j, arr[i][j]));
            }
        }

        int totalCost = 0;
        int count = 0;
        ArrayList<Node> result = new ArrayList<>();
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int from = node.from;
            int to = node.to;
            int cost = node.cost;

            if(find(from) != find(to)) {
                union(from, to);
                totalCost += cost;
                count++;
                result.add(node);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(totalCost).append(" ").append(count).append("\n");
        for (Node node : result) {
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

        if(x != y) {
            parent[y] = x;
        }
    }

}