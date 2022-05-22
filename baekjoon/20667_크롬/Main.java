import java.io.*;
import java.util.*;

public class Main {

    public static int n, m, k;
    public static int[] cpu, memory, priority;
    public static int[][] dp;

    public static final int pMAX = 500;
    public static final int cMAX = 1000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        cpu = new int[n+1];
        memory = new int[n+1];
        priority = new int[n+1];

        dp = new int[pMAX+1][cMAX+2];

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            cpu[i] = Integer.parseInt(st.nextToken());
            memory[i] = Integer.parseInt(st.nextToken());
            priority[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i <= pMAX; i++)
            Arrays.fill(dp[i], Integer.MIN_VALUE);

        dp[0][0] = 0;

        for(int i = 1; i <= n; i++){
            for(int j = pMAX; j >= 0; j--){
                for(int k = cMAX +1; k >= 0; k--){
                    if(j + priority[i] > pMAX) continue;
                    if(k + cpu[i] <= cMAX){
                        dp[j + priority[i]][k + cpu[i]] = Math.max(dp[j + priority[i]][k + cpu[i]], dp[j][k] + memory[i]);
                    }
                    else{
                        dp[j + priority[i]][cMAX+1] = Math.max(dp[j + priority[i]][cMAX+1], dp[j][k] + memory[i]);
                    }
                }
            }
        }

        int ans = -1;
        for(int i = 0; i <= pMAX; i++){
            for(int j = m; j <= cMAX+1; j++){
                if(dp[i][j] >= k){
                    if(ans == -1 || ans > i)
                        ans = i;
                }
            }
        }

        System.out.println(ans);
    }

}
