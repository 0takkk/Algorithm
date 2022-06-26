import java.io.*;
import java.util.*;

public class Main {

    public static int n, k;
    public static ArrayList<Integer>[] list;
    public static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        dp = new int[k+1][n+1];
        for(int i = 1; i <= n; i++){
            dp[0][i] = 1;
            dp[1][i] = 1 + list[i].size();
        }

        for(int dist = 2; dist <= k; dist++){
            for(int i = 1; i <= n; i++){
                for (int next : list[i]) {
                    dp[dist][i] += dp[dist-1][next];
                }

                dp[dist][i] -= dp[dist-2][i] * (list[i].size()-1);
            }
        }

        int max = 0;
        for(int i = 1; i <= n; i++){
            max = Math.max(max, dp[k][i]);
        }

        System.out.println(max);
    }

}
