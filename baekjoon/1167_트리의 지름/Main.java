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

    public static int n, ans, root;
    public static ArrayList<Node>[] graph;
    public static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            while(true) {
                int x = Integer.parseInt(st.nextToken());
                if (x == -1) break;
                graph[num].add(new Node(x, Integer.parseInt(st.nextToken())));
            }
        }

        visited = new boolean[n+1];
        dfs(1, 0);

        visited = new boolean[n+1];
        dfs(root, 0);

        System.out.println(ans);
    }

    public static void dfs(int idx, int len){
        if(len > ans){
            ans = len;
            root = idx;
        }

        visited[idx] = true;

        for (Node next : graph[idx]) {
            if(!visited[next.x]){
                dfs(next.x, len + next.cost);
            }
        }
    }
}
