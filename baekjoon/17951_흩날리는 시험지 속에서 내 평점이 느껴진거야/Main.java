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
        int l = 0;
        int r = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            r += arr[i];
        }

        while(l <= r){
            int mid = (l + r) / 2;
            int cnt = 1;
            int sum = 0;

            for(int i = 0; i < n; i++){
                sum += arr[i];
                if(sum >= mid){
                    sum = 0;
                    cnt++;
                }
            }

            if(cnt > k) l = mid+1;
            else r = mid-1;
        }

        System.out.println(l-1);
    }

}
