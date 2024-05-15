import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        for(int i = 0; i < x; i++) {
            ans += arr[i];
        }

        int sum = ans;
        int count = 1;

        for(int i = x; i < n; i++) {
            sum -= arr[i-x];
            sum += arr[i];

            if(sum > ans) {
                ans = sum;
                count = 1;
            }
            else if(sum == ans) {
                count++;
            }
        }

        if(ans == 0) {
            System.out.println("SAD");
        }
        else {
            System.out.println(ans);
            System.out.println(count);
        }

    }

}
