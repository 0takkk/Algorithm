import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node>{

        int to, from, cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

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

    public static int n, total, root, max = 0;
    public static int[] parent;
    public static ArrayList<Node>[] graph;
    public static PriorityQueue<Node> pq;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n];
        parent = new int[n];
        for(int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
            parent[i] = i;
        }

        pq = new PriorityQueue<>();
        while(k-->0){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            pq.add(new Node(from, to, cost));
        }

        mst();
        visited = new boolean[n];
        visited[0] = true;
        dfs(0, 0);

        max = 0;
        visited = new boolean[n];
        visited[root] = true;
        dfs(root, 0);

        System.out.println(total);
        System.out.println(max);
    }

    public static void dfs(int now, int sum){
        if(sum > max){
            root = now;
            max = sum;
        }

        for (Node next : graph[now]) {
            if(!visited[next.to]){
                visited[next.to] = true;
                dfs(next.to, sum + next.cost);
            }
        }
    }

    public static void mst(){
        int m = 0;

        while(!pq.isEmpty()){
            Node node = pq.poll();
            int to = node.to;
            int from = node.from;
            int cost = node.cost;

            if(find(to) == find(from)) continue;

            union(to, from);
            total += cost;
            m++;
            graph[to].add(new Node(from, cost));
            graph[from].add(new Node(to, cost));

            if(m == n-1) return;
        }
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
