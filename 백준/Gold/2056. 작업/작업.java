import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] times = new int[n+1];

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());

            int k = Integer.parseInt(st.nextToken());
            int max = 0;
            while(k-->0) {
                int work = Integer.parseInt(st.nextToken());
                max = Math.max(max, times[work]);
            }

            times[i] += max;
        }

        int ans = 0;
        for (int time : times) {
            ans = Math.max(ans, time);
        }

        System.out.println(ans);
    }

}