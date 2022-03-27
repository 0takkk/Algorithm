import java.io.*;
import java.util.*;

public class Main {

    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] high = new ArrayList[n+1];
        ArrayList<Integer>[] low = new ArrayList[n+1];

        for(int i = 1; i <= n; i++){
            high[i] = new ArrayList<>();
            low[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            high[b].add(a);
            low[a].add(b);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++){
            visited = new boolean[n+1];
            visited[i] = true;
            int h = dfs(i, high, 0);
            int l = dfs(i, low, 0);

            sb.append(n-1-h-l + "\n");
        }

        System.out.println(sb.toString());
    }

    public static int dfs(int now, ArrayList<Integer>[] list, int count){
        for (int next : list[now]) {
            if(!visited[next]){
                visited[next] = true;
                count = dfs(next, list, count+1);
            }
        }
        return count;
    }

}
