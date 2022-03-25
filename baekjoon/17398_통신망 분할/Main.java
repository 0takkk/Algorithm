import java.io.*;
import java.util.*;

public class Main {

    public static int n, m, q;
    public static int[] parent;
    public static int[] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        cost = new int[n+1];
        int[][] graph = new int[m+1][2];
        int[] order = new int[q+1];
        boolean[] visited = new boolean[m+1];

        for(int i = 1; i <= n; i++){
            parent[i] = i;
            cost[i] = 1;
        }

        for(int i = 1; i <= m; i++){
            st = new StringTokenizer(br.readLine());
            graph[i][0] = Integer.parseInt(st.nextToken());
            graph[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= q; i++){
            order[i] = Integer.parseInt(br.readLine());
            visited[order[i]] = true;
        }

        for(int i = 1; i <= m; i++){
            if(visited[i]) continue;

            union(graph[i][0], graph[i][1]);
        }

        long result = 0;
        for(int i = q; i >= 1; i--){
            int idx = order[i];

            if(find(graph[idx][0]) != find(graph[idx][1])){
                result += (long)cost[find(graph[idx][0])] * (long)cost[find(graph[idx][1])];
            }
            union(graph[idx][0], graph[idx][1]);
        }

        System.out.println(result);
    }

    public static int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x == y) return;

        cost[x] += cost[y];
        parent[y] = x;
    }

}
