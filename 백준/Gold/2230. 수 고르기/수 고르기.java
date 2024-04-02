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
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int ans = Integer.MAX_VALUE;

        for(int i = 0; i < n-1; i++) {
            int binary = binary(arr, i, m);
            if(binary != Integer.MIN_VALUE) {
                ans = Math.min(ans, binary - arr[i]);
            }
        }

        System.out.println(ans);
    }

    public static int binary(int[] arr, int idx, int m) {
        int target = arr[idx] + m;

        int left = idx+1;
        int right = arr.length-1;
        int ans = Integer.MIN_VALUE;

        while(left <= right) {
            int mid = (left + right) / 2;

            if(arr[mid] < target) {
                left = mid+1;
            }
            else {
                right = mid-1;
                ans = arr[mid];
            }
        }

        return ans;
    }

}