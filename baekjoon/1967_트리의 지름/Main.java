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

    public static int n, root, ans;
    public static ArrayList<Node>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n+1];

        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 1; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        root = 1;
        bfs(root);

        ans = 0;
        bfs(root);

        System.out.println(ans);
    }

    public static void bfs(int start){
        boolean[] visited = new boolean[n+1];
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(start, 0));
        visited[start] = true;

        while(!q.isEmpty()){
            Node now = q.poll();

            if(ans < now.cost){
                ans = now.cost;
                root = now.x;
            }

            for (Node next : graph[now.x]) {
                if(!visited[next.x]){
                    visited[next.x] = true;
                    q.offer(new Node(next.x, now.cost + next.cost));
                }
            }
        }
    }

}
