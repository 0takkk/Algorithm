import java.io.*;
import java.util.*;

public class Main {

    public static int n, m;
    public static int[] ride;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        ride = new int[m+1];
        st = new StringTokenizer(br.readLine());

        int max = 0;
        for(int i = 1; i <= m; i++){
            ride[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, ride[i]);
        }

        if(n <= m){
            System.out.println(n);
            return;
        }

        long time = binary(max);
        long cnt = m;

        for(int i = 1; i <= m; i++){
            cnt += (time-1) / ride[i];
        }

        int ans = 0;
        for(int i = 1; i <= m; i++){
            if(time % ride[i] == 0) cnt++;

            if(cnt == n){
                ans = i;
                break;
            }
        }

        System.out.println(ans);
    }

    public static long binary(int max){
        long left = 0;
        long right = (long)n / m * max;
        long result = 0;

        while(left <= right){
            long mid = (left + right) / 2;
            long cnt = m;

            for(int i = 1; i <= m; i++){
                cnt += mid / ride[i];
            }

            if(n <= cnt){
                result = mid;
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }

        return result;
    }

}
