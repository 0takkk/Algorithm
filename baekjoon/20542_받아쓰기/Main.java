import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String a = br.readLine();
        String b = br.readLine();

        long[][] dp = new long[n+1][m+1];

        for(int i = 1; i <= n; i++) dp[i][0] = i;
        for(int i = 1; i <= m; i++) dp[0][i] = i;

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(isSame(a.charAt(i-1), b.charAt(j-1))) dp[i][j] = dp[i-1][j-1];
                else dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j])) + 1;
            }
        }

        System.out.println(dp[n][m]);
    }

    public static boolean isSame(char a, char b){
        if(a == b) return true;
        else if(a == 'i' && (b == 'j' || b == 'l')) return true;
        else if(a == 'v' && b == 'w') return true;
        return false;
    }

}
