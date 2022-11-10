import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static ArrayList<Integer>[] graph;
    public static int[][] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n];

        for(int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                if(Integer.parseInt(st.nextToken()) == 0) continue;
                graph[i].add(j);
            }
        }

        result = new int[n][n];
        for(int i = 0; i < n; i++){
            bfs(i);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void bfs(int start){
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);

        while(!q.isEmpty()){
            int now = q.poll();

            for (int next : graph[now]) {
                if(!visited[next]){
                    visited[next] = true;
                    result[start][next] = 1;
                    q.offer(next);
                }
            }
        }
    }
}
