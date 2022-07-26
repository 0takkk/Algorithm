import java.io.*;
import java.util.*;

public class Main {

    public static int n, idx = 1;
    public static ArrayList<Integer>[] graph;
    public static int[] left, right;
    public static boolean[] visited;

    public static void main(String[] args) throws Exception{
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

            while(st.hasMoreTokens()){
                int edge = Integer.parseInt(st.nextToken());
                if(edge == -1) break;

                graph[num].add(edge);
            }

            Collections.sort(graph[num]);
        }

        int root = Integer.parseInt(br.readLine());
        left = new int[n+1];
        right = new int[n+1];
        visited = new boolean[n+1];

        dfs(root);

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++){
            sb.append(i).append(" ").append(left[i]).append(" ").append(right[i]).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void dfs(int now){
        left[now] = idx++;
        visited[now] = true;

        for(int next : graph[now]){
            if(!visited[next]){
                dfs(next);
            }
        }

        right[now] = idx++;
    }

}
