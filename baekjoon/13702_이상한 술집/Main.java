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
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        long left = 1;
        long right = arr[n-1];

        long ans = 0;

        while(left <= right){
            long mid = (left + right) / 2;

            long sum = Arrays.stream(arr).mapToLong(i -> i / mid).sum();

            if(sum >= k){
                left = mid+1;
                ans = Math.max(ans, mid);
            }
            else{
                right = mid-1;
            }
        }

        System.out.println(ans);
    }

}
