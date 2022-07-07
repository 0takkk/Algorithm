import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static int[] weights;
    public static int[][] dp;
    public static ArrayList<Integer>[] graph;
    public static ArrayList<Integer>[][] route;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        weights = new int[n+1];
        graph = new ArrayList[n+1];
        route = new ArrayList[2][n+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            weights[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < 2; i++){
            for(int j = 1; j <= n; j++){
                route[i][j] = new ArrayList<>();
            }
        }

        for(int i = 0; i < n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        visited = new boolean[n+1];
        dp = new int[2][n+1];

        dfs(1);
        System.out.println(Math.max(dp[0][1], dp[1][1]));


        ArrayList<Integer> routes;

        if(dp[0][1] > dp[1][1]) routes = route[0][1];
        else routes = route[1][1];

        StringBuilder sb = new StringBuilder();
        Collections.sort(routes);
        for (int r : routes) {
            sb.append(r + " ");
        }
        System.out.println(sb.toString());
    }

    public static void dfs(int idx){
        dp[1][idx] = weights[idx];
        visited[idx] = true;
        route[1][idx].add(idx);

        for(int next : graph[idx]){
            if(!visited[next]){
                dfs(next);
                dp[0][idx] += Math.max(dp[0][next], dp[1][next]);
                dp[1][idx] += dp[0][next];

                if(dp[0][next] > dp[1][next]) route[0][idx].addAll(route[0][next]);
                else route[0][idx].addAll(route[1][next]);

                route[1][idx].addAll(route[0][next]);
            }
        }
    }

}
