import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static ArrayList<Integer>[] graph;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        visited = new boolean[n+1];

        int ans = 0;
        for(int i = 1; i <= n; i++){
            if(!visited[i]){
                dfs(i);
                ans++;
            }
        }

        System.out.println(ans);
    }

    public static void dfs(int now){
        visited[now] = true;

        for (int next : graph[now]) {
            if(!visited[next]){
                visited[next] = true;
                dfs(next);
            }
        }
    }

}
