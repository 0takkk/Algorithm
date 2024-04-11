import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static ArrayList<Integer>[] graph;
    public static int[] parent, height;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n+1];
        parent = new int[n+1];
        height = new int[n+1];
        visited = new boolean[n+1];

        for(int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for(int i = 1; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(1, 0, 1);

        StringBuilder sb = new StringBuilder();
        
        int m = Integer.parseInt(br.readLine());

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(lca(a, b)).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static int lca(int a, int b){
        if(height[a] == height[b]) {
            if(a == b) return a;

            while(parent[a] != parent[b]){
                a = parent[a];
                b = parent[b];
            }

            return parent[a];
        }
        else if(height[a] > height[b]){
            while(height[a] > height[b]){
                a = parent[a];
            }

            return lca(a, b);
        }
        else{
            while(height[a] < height[b]){
                b = parent[b];
            }

            return lca(a, b);
        }
    }

    public static void dfs(int now, int p, int h){
        visited[now] = true;
        parent[now] = p;
        height[now] = h;

        for (int next : graph[now]) {
            if(!visited[next]){
                dfs(next, now, h+1);
            }
        }
    }

}
