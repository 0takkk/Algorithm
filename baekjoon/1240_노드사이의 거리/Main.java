import java.io.*;
import java.util.*;

public class Main {

    public static class Node{
        int x, cost;

        public Node(int x, int cost) {
            this.x = x;
            this.cost = cost;
        }
    }

    public static int n;
    public static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for(int i = 1; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        StringBuilder sb = new StringBuilder();

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            sb.append(bfs(from, to)).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static int bfs(int from, int to){
        boolean[] visited = new boolean[n+1];
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(from, 0));
        visited[from] = true;

        while(!q.isEmpty()){
            Node now = q.poll();
            int x = now.x;
            int cost = now.cost;

            if(x == to) return cost;

            for (Node next : graph[x]) {
                if(!visited[next.x]){
                    visited[next.x] = true;
                    q.offer(new Node(next.x, cost + next.cost));
                }
            }
        }

        return -1;
    }

}
