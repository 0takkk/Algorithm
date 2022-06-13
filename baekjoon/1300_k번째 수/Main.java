import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int left = 1;
        int right = k;

        int ans = 0;

        while(left <= right){
            int mid = (left + right) / 2;
            int cnt = 0;

            for(int i = 1; i <= n; i++){
                cnt += Math.min(mid/i, n);
            }

            if(cnt >= k){
                ans = mid;
                right = mid-1;
            }
            else{
                left = mid+1;
            }
        }

        System.out.println(ans);
    }

}
