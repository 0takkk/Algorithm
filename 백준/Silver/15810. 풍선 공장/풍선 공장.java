import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] times = new int[n];
        long max = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            times[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, times[i]);
        }

        long left = 0;
        long right = max * m;

        while(left <= right){
            long mid = (left + right) / 2;
            long cnt = 0;

            for(int i = 0; i < n; i++){
                cnt += (mid / times[i]);
            }

            if(cnt >= m){
                right = mid-1;
            }
            else{
                left = mid+1;
            }
        }

        System.out.println(left);
    }

}
