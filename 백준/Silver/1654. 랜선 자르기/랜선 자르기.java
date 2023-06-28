import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] lan = new int[k];
        for(int i = 0; i < k; i++){
            lan[i] = Integer.parseInt(br.readLine());
        }

        long left = 0;
        long right = Integer.MAX_VALUE;
        long ans = 0;

        while(left <= right){
            long mid = (left + right) / 2;
            long cnt = 0;

            for(int i = 0; i < k; i++){
                cnt += (lan[i] / mid);
            }

            if(cnt >= n){
                ans = mid;
                left = mid+1;
            }
            else{
                right = mid-1;
            }
        }

        System.out.println(ans);
    }

}
