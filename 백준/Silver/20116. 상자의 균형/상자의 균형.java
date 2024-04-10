import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[] midX = new int[n];
        long[] preSum = new long[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            midX[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i < n; i++) {
            preSum[i] = preSum[i-1] + midX[i];
        }

        for(int i = 0; i < n; i++) {
            double mid = (double)(preSum[n-1] - preSum[i]) / (n-i-1);
            if(mid >= midX[i]+l || mid <= midX[i]-l) {
                System.out.println("unstable");
                return;
            }
        }

        System.out.println("stable");
    }

}