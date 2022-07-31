import java.io.*;
import java.util.*;

public class Main {

    public static int n, m;

    public static int[] dx = {0, 1, 1};
    public static int[] dy = {1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] a = new int[n+2][m+2];
        int[][] b = new int[n+2][m+2];

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= m; j++){
                String str = st.nextToken();
                int count = Integer.parseInt(str.substring(1));

                if(str.charAt(0) == 'A') a[i][j] = count;
                else b[i][j] = count;
            }
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                a[i][j] += a[i][j-1];
            }

            for(int j = m; j >= 1; j--){
                b[i][j] += b[i][j+1];
            }
        }

        int[][] dp = new int[n+2][m+2];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(i == 1 || j == 1){
                    dp[i][j] = dp[i-1][j] + b[i][j+1];
                    continue;
                }

                dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]) + a[i][j-1] + b[i][j+1];
                dp[i][j] = Math.max(dp[i][j], dp[i][j-1] - b[i][j] + b[i][j+1]);
            }
        }

        System.out.println(dp[n][m]);
    }

}
