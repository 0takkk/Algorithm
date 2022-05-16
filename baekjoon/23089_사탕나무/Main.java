import java.io.*;
import java.util.*;

public class Main {

    public static int n,k;
    public static ArrayList<Integer>[] list;
    public static int[][] dp;
    public static int[] parent;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++)
            list[i] = new ArrayList<>();

        for(int i = 1; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        dp = new int[n+1][k+1];
        parent = new int[n+1];
        visited = new boolean[n+1];
        dfs(1);

        int max = 0;
        int id = 0;
        for(int i = 1; i <= n; i++){
            int count = dp[i][k] + 1;
            count += restCount(i);

            if(max < count){
                max = count;
                id = i;
            }
        }

        System.out.println(max);
    }

    public static void dfs(int now){
        visited[now] = true;

        for(int next : list[now]){
            if(!visited[next]) {
                parent[next] = now;
                dfs(next);
                for (int i = 1; i <= k; i++) {
                    dp[now][i] += (dp[next][i - 1] + 1);
                }
            }
        }
    }

    public static int restCount(int now){
        int idx = now;
        int count = 0;
        for(int i = k; i > 0; i--){
            if(parent[idx] == 0) break;
            if(i == 1) count += 1;
            else{
                count += (dp[parent[idx]][i-1]+1) - (dp[idx][i-2]+1);
                idx = parent[idx];
            }
        }
        return count;
    }

}
