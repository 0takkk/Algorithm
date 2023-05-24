import java.io.*;
import java.util.*;

public class Main {

    public static int cnt;
    public static int[] arr;
    public static boolean[] visited, cycle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-->0){
            cnt = 0;

            int n = Integer.parseInt(br.readLine());
            arr = new int[n+1];
            visited = new boolean[n+1];
            cycle = new boolean[n+1];

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for(int i = 1; i <= n; i++){
                dfs(i);
            }

            sb.append(n - cnt).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void dfs(int idx){
        if(visited[idx]) return;

        visited[idx] = true;
        int next = arr[idx];

        if(!visited[next]) dfs(next);
        else{
            if(!cycle[next]){
                cnt++;
                for(int i = next; i != idx; i = arr[i]){
                    cnt++;
                }
            }
        }

        cycle[idx] = true;
    }

}
