import java.io.*;
import java.util.*;

public class Main {

    public static final int INF = 100000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        char[] arr = br.readLine().toCharArray();

        int[] dp = new int[n];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for(int i = 1; i < n; i++){
            char preChar = getPreChar(arr[i]);
            for(int j = i-1; j >= 0; j--){
                if(arr[j] == preChar && dp[j] != INF){
                    dp[i] = Math.min(dp[i], dp[j] + (i-j) * (i-j));
                }
            }
        }

        System.out.println(dp[n-1] == INF ? -1 : dp[n-1]);
    }

    public static char getPreChar(char c){
        if(c == 'B') return 'J';
        else if(c == 'O') return 'B';
        else return 'O';
    }

}
