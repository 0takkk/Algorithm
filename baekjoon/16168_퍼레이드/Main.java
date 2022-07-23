import java.io.*;
import java.util.*;

public class Main {

    public static int n, m;
    public static ArrayList<Integer>[] graph;
    public static int[][] visited;
    public static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        visited = new int[n+1][n+1];
        for(int i = 1; i <= n; i++) {
            dfs(i, i, 0);
            if(flag) break;
        }

        if(flag) System.out.println("YES");
        else System.out.println("NO");
    }

    public static void dfs(int idx, int num, int cnt){
        if(cnt == m){
            flag = true;
            return;
        }

        for(int next : graph[idx]){
            if(visited[idx][next] == num || visited[next][idx] == num) continue;
            visited[idx][next] = visited[next][idx] = num;
            dfs(next, num, cnt+1);
        }
    }

}
