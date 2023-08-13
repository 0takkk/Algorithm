import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int preSum = 0;
        for(int i = 0; i < k; i++) {
            preSum += arr[i];
        }
        int ans = preSum;

        for(int i = 1; i <= n-k; i++) {
            preSum -= arr[i-1];
            preSum += arr[i+k-1];
            ans = Math.max(ans, preSum);
        }

        System.out.println(ans);
    }

}
