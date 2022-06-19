import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        long[] arr = new long[n];
        for(int i = 0; i < n; i++)
            arr[i] = Long.parseLong(st.nextToken());

        Arrays.sort(arr);

        long ans = n % 2 == 0 ? 0 : arr[n-1];
        int size = n % 2 == 0 ? n : n-1;

        for(int i = 0; i < size/2; i++){
            ans = Math.max(ans, arr[i] + arr[size-i-1]);
        }

        System.out.println(ans);
    }

}
