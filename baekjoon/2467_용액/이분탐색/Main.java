import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }


        int min = Integer.MAX_VALUE;
        int[] ans = new int[2];

        for(int i = 0; i < n-1; i++){
            int left = i+1;
            int right = n-1;

            while(left <= right && right < n){
                int mid = (left + right) / 2;

                int diff = Math.abs(arr[i] + arr[mid]);
                if(min > diff) {
                    min = diff;
                    ans[0] = arr[i];
                    ans[1] = arr[mid];
                }

                if(arr[i] + arr[mid] > 0) right = mid-1;
                else left = mid+1;
            }
        }

        System.out.println(ans[0] + " " + ans[1]);
    }

}
