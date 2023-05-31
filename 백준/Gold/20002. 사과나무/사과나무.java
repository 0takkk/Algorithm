import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n+1][n+1];
        int[][] sum = new int[n+1][n+1];

        int ans = -1000;

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                sum[i][j] = sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1] + arr[i][j];
                ans = Math.max(ans, arr[i][j]);
            }
        }

        int k = 0;
        while(k++ < n){
            for(int i = 1; i < n-k+1; i++){
                for(int j = 1; j < n-k+1; j++){
                    int profit = sum[i+k][j+k] - sum[i-1][j+k] - sum[i+k][j-1] + sum[i-1][j-1];
                    ans = Math.max(ans, profit);
                }
            }
        }

        System.out.println(ans);
    }
}
