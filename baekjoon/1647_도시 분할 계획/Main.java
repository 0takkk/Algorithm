import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node>{
        int to, from, cost;

        public Node(int to, int from, int cost) {
            this.to = to;
            this.from = from;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static int n;
    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for(int i = 1; i <= n; i++) parent[i] = i;

        PriorityQueue<Node> pq = new PriorityQueue<>();

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pq.offer(new Node(a, b, c));
        }

        int cnt = 0;
        int max = 0;
        int sum = 0;

        while(!pq.isEmpty()){
            Node node = pq.poll();
            int from = node.from;
            int to = node.to;
            int cost = node.cost;

            if(union(from, to)){
                cnt++;
                sum += cost;
                max = Math.max(max, cost);

                if(cnt == n-1) break;
            }
        }

        System.out.println(sum - max);
    }

    public static int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public static boolean union(int x, int y){
        x = find(x);
        y = find(y);

        if(x == y) return false;

        parent[y] = x;
        return true;
    }

}
