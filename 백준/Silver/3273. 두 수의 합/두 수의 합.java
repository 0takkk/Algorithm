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

        int x = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        int ans = 0;
        int left = 0;
        int right = n-1;

        while(left < right) {
            int l = arr[left];
            int r = arr[right];

            if(l+r == x) {
                left++;
                right--;
                ans++;
            }
            else if(l+r < x) {
                left++;
            }
            else {
                right--;
            }
        }

        System.out.println(ans);
    }

}