import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-->0){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int size = n + m;
            int[] arr = new int[size];
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for(int i = n+1; i < size; i++){
                arr[i] = arr[i-n];
            }

            int[] pSum = new int[size];
            for(int i = 1; i < size; i++){
                pSum[i] = pSum[i-1] + arr[i];
            }

            int ans = 0;
            if(n == m) size = n+1;
            for(int i = m; i < size; i++){
                int sum = pSum[i] - pSum[i-m];

                if(sum >= k) continue;
                ans++;
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

}
