import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());

        long total = 0;
        long max = 0;
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            total += arr[i];
            max = Math.max(max, arr[i]);
        }

        System.out.println(binary(n, m, max, total, arr));
    }

    public static long binary(int n, int m, long max, long total, int[] arr) {
        long left = max;
        long right = total-1;

        while(left <= right) {
            long mid = (left + right) / 2;
            int cnt = 0;
            int sum = 0;

            for(int i = 0; i < n; i++) {
                sum += arr[i];
                if(sum > mid) {
                    cnt++;
                    sum = arr[i];
                }
            }

            if(cnt >= m) {
                left = mid+1;
            }
            else {
                right = mid-1;
            }
        }

        return left;
    }

}