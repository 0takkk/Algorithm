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

        int left = 0;
        int right = 1;
        int ans = Integer.MAX_VALUE;

        while(left < right && right < n) {
            int diff = arr[right] - arr[left];

            if(diff >= m) {
                ans = Math.min(ans, diff);
                left++;
            }
            else {
                right++;
            }

            if(left == right) {
                right++;
            }
        }

        System.out.println(ans);
    }

}