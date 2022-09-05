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

        int left = 0;
        int right = n-1;

        while(left < right){
            int sum = arr[left] + arr[right];
            int abs = Math.abs(sum);

            if(min > abs){
                min = abs;
                ans[0] = arr[left];
                ans[1] = arr[right];
            }

            if(sum >= 0) right--;
            else left++;
        }

        System.out.println(ans[0] + " " + ans[1]);
    }

}
