import java.io.*;
import java.util.*;

public class Main {

    public static final int INF = 400001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int[][][] dp = new int[100001][5][5];

        for(int i = 0; i < 100001; i++){
            for(int j = 0; j < 5; j++){
                Arrays.fill(dp[i][j], INF);
            }
        }

        dp[0][0][0] = 0;
        int idx = 1;

        while(true){
            int comd = Integer.parseInt(st.nextToken());

            if(comd == 0) break;

            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    if(dp[idx-1][i][j] == INF) continue;

                    if(comd != j) {
                        dp[idx][comd][j] = Math.min(dp[idx][comd][j], dp[idx-1][i][j] + getCost(i, comd));
                    }
                    if(comd != i){
                        dp[idx][i][comd] = Math.min(dp[idx][i][comd], dp[idx-1][i][j] + getCost(j, comd));
                    }
                }
            }

            idx++;
        }

        int min = INF;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                min = Math.min(min, dp[idx-1][i][j]);
            }
        }

        System.out.println(min);
    }

    public static int getCost(int pre, int cur){
        if(pre == 0) return 2;
        else if(pre == cur) return 1;
        else if(Math.abs(pre - cur) == 2) return 4;
        return 3;
    }

}
