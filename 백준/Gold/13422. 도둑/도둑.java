import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-->0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int size = n + m - 1;
            if(n == m) size = n;
            int[] home = new int[size];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                home[i] = Integer.parseInt(st.nextToken());
            }

            for(int i = n; i < size; i++) {
                home[i] = home[i-n];
            }

            int preSum = 0;
            for(int i = 0; i < m; i++) {
                preSum += home[i];
            }

            int ans = 0;
            if(preSum < k) ans++;

            for(int i = m; i < size; i++) {
                preSum += home[i];
                preSum -= home[i-m];

                if(preSum < k) ans++;
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

}
