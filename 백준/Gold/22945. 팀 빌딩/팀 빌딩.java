import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = n-1;
        int ans = 0;

        while(left < right) {
            ans = Math.max(ans, Math.min(arr[left], arr[right]) * (right - left -1));

            if(arr[left] > arr[right]) {
                right--;
            }
            else {
                left++;
            }
        }

        System.out.println(ans);
    }

}