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
        int right = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            right = Math.max(right, arr[i]);
        }

        int left = 0;

        while(left < right){
            int mid = (left+right)/2;

            int cnt = 1;
            int min = 100001;
            int max = -1;
            for(int i = 0; i < n; i++){
                min = Math.min(min, arr[i]);
                max = Math.max(max, arr[i]);

                if(max - min > mid){
                    cnt++;
                    max = -1;
                    min = 100001;
                    i--;
                }
            }

            if(cnt <= m) right = mid;
            else left = mid+1;
        }

        System.out.println(right);
    }

}
