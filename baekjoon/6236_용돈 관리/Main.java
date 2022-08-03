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
        int sum = 0;
        int max = 0;
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
            max = Math.max(max, arr[i]);
        }

        int l = max;
        int r = sum;

        int ans = Integer.MAX_VALUE;

        while(l <= r){
            int mid = (l + r) / 2;
            int cnt = 1;
            int tmp = 0;

            for(int i = 0; i < n; i++){
                tmp += arr[i];
                if(tmp > mid){
                    tmp = arr[i];
                    cnt++;
                }
            }

            if(cnt <= m){
                ans = mid;
                r = mid-1;
            }else{
                l = mid+1;
            }
        }

        System.out.println(ans);
    }

}
