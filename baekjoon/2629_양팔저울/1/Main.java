import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] weights = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            weights[i] = Integer.parseInt(st.nextToken());
        }

        boolean[] dp = new boolean[40001];

        for(int i = 0; i < n; i++){
            int weight = weights[i];

            boolean[] tmp = new boolean[40001];
            for(int j = 0; j < 40001; j++){
                if(dp[j]){
                    tmp[j] = true;
                    tmp[j+weight] = true;
                    tmp[Math.abs(j-weight)] = true;
                }
            }
            tmp[weight] = true;

            dp = tmp;
        }

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(m-->0){
            int num = Integer.parseInt(st.nextToken());

            sb.append(dp[num] ? "Y " : "N ");
        }

        System.out.println(sb.toString());
    }

}
