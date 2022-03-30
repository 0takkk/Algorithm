import java.io.*;
import java.util.*;

public class Main {

    public static ArrayList<Integer>[] graph;
    public static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            int boss = Integer.parseInt(st.nextToken());
            if(i != 1){
                graph[boss].add(i);
            }
        }

        arr = new int[n+1];

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int staff = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            arr[staff] += w;
        }

        dfs(1, 0);

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++){
            sb.append(arr[i] + " ");
        }

        System.out.println(sb.toString());
    }

    public static void dfs(int staff, int w){
        arr[staff] += w;

        for(int s : graph[staff]){
            dfs(s, arr[staff]);
        }
    }
}
