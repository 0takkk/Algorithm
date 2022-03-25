import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static int[][] map;
    public static StringBuilder sb;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        map = new int[n+1][n+1];

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = map[b][a] = 1;
        }

        sb = new StringBuilder();

        visited = new boolean[n+1];
        dfs(v);
        sb.append("\n");

        visited = new boolean[n+1];
        bfs(v);

        System.out.println(sb.toString());
    }

    public static void dfs(int v){
        sb.append(v + " ");
        visited[v] = true;

        for(int i = 1; i <= n; i++){
            if(!visited[i] && map[v][i] == 1){
                dfs(i);
            }
        }
    }

    public static void bfs(int v){
        Queue<Integer> q = new LinkedList<>();
        q.offer(v);
        visited[v] = true;

        while(!q.isEmpty()){
            int now = q.poll();

            sb.append(now + " ");

            for(int i = 1; i <= n; i++){
                if(!visited[i] && map[now][i] == 1){
                    visited[i] = true;
                    q.offer(i);
                }
            }
        }
    }

}
