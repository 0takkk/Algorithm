import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        int max = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        int[] counts = new int[max+1];
        int banner = (int) Math.ceil((double)9 * m / 10);

        for(int i = 0; i < m; i++) {
            counts[arr[i]]++;
            if(counts[arr[i]] >= banner) {
                System.out.println("YES");
                return;
            }
        }

        for(int i = 1; i <= n-m; i++) {
            int prev = arr[i - 1];
            int now = arr[i + m - 1];
            counts[prev]--;
            counts[now]++;
            if(counts[now] >= banner) {
                System.out.println("YES");
                return;
            }
        }

        System.out.println("NO");
    }

}
