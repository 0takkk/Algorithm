import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int[] dp = new int[y+1];
        dp[0] = h;

        int[] years = {1, 3, 5};
        double[] rate = {1.05, 1.2, 1.35};
        for(int i = 1; i <= y; i++){
            for(int j = 0; j < 3; j++){
                if(i >= years[j]){
                    dp[i] = Math.max(dp[i], (int)(dp[i - years[j]] * rate[j]));
                }
            }
        }

        System.out.println(dp[y]);
    }
    
}
