import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int left = 1;
        int right = arr[n-1];
        int ans = 0;

        while(left <= right){
            int mid = (left + right) / 2;
            int cnt = 1;
            int idx = 0;

            for(int i = 0; i < n-1; i++){
                if(arr[idx] + mid <= arr[i+1]){
                    cnt++;
                    idx = i+1;
                }
            }

            if(cnt >= c){
                ans = Math.max(ans, mid);
                left = mid+1;
            }else{
                right = mid-1;
            }
        }

        System.out.println(ans);
    }

}
