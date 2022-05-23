import java.io.*;
import java.util.*;

public class Main {

    public static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        arr = new int[n+1];
        for(int i = 1; i <= n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        boolean[][] dp = new boolean[n+1][n+1];
        for(int i = 1; i <= n; i++){
            dp[i][i] = true;
            if(arr[i] == arr[i-1]) dp[i-1][i] = true;
        }

        for(int i = 2; i <= n; i++){
            for(int j = 1; j <= n-i; j++){
                if(dp[j+1][i+j-1] && arr[j] == arr[j+i]){
                    dp[j][i+j] = true;
                }
            }
        }

        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            if(dp[l][r]) sb.append("1\n");
            else sb.append("0\n");
        }

        System.out.println(sb.toString());
    }

}
