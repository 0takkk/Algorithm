import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static ArrayList<Integer>[] graph;
    public static int[] child;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 1; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        child = new int[n+1];
        visited = new boolean[n+1];
        Arrays.fill(child, 1);

        dfs(r);

        StringBuilder sb = new StringBuilder();
        while(q-->0){
            int num = Integer.parseInt(br.readLine());
            sb.append(child[num]).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static int dfs(int now){
        visited[now] = true;

        for (int next : graph[now]) {
            if(!visited[next]){
                child[now] += dfs(next);
            }
        }

        return child[now];
    }

}
