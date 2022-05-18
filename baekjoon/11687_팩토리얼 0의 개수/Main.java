import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int m = Integer.parseInt(br.readLine());

        System.out.println(binarySearch(m));
    }

    public static int binarySearch(int m){
        int left = 1;
        int right = 1000000000;
        int ans = -1;

        while(left <= right){
            int cnt = 0;
            int mid = (left + right) / 2;
            for(int i = 5; i <= mid; i*=5){
                cnt += mid/i;
            }

            if(m <= cnt){
                if(m == cnt){
                    ans = mid;
                }
                right = mid-1;
            }
            else{
                left = mid+1;
            }
        }

        return ans;
    }

}
