import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] diff = new int[n-1];
        for(int i = 0; i < n-1; i++){
            diff[i] = arr[i+1] - arr[i];
        }

        Arrays.sort(diff);

        int ans = 0;
        for(int i = 0; i < n-k; i++){
            ans += diff[i];
        }

        System.out.println(ans);
    }

}
