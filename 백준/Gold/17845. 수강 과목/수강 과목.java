import java.io.*;
import java.util.*;

public class Main {

    public static class Study{
        int priority, time;

        public Study(int priority, int time) {
            this.priority = priority;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Study[] studies = new Study[k];
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            int priority = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            studies[i] = new Study(priority, time);
        }

        int[] dp = new int[n+1];

        for(int i = 0; i < k; i++){
            Study study = studies[i];

            for(int j = n; j >= study.time; j--){
                dp[j] = Math.max(dp[j], dp[j-study.time] + study.priority);
            }
        }

        System.out.println(dp[n]);
    }

}
