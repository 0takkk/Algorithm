import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = br.readLine();
        int len = str.length();
        int[][] dp = new int[len+1][26];

        for(int i = 1; i <= str.length(); i++) {
            dp[i] = dp[i-1].clone();
            dp[i][str.charAt(i-1)-'a']++;
        }

        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(n-->0) {
            st = new StringTokenizer(br.readLine());
            int alpa = st.nextToken().charAt(0)-'a';
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            sb.append(dp[right+1][alpa] - dp[left][alpa]).append("\n");
        }

        System.out.println(sb.toString());
    }

}
