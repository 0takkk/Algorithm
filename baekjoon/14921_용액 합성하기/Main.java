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

        int ans = Integer.MAX_VALUE;
        int diff = Integer.MAX_VALUE;

        int left = 0, right = n-1;

        while(left < right){
            int val = arr[left] + arr[right];

            if(diff > Math.abs(val)){
                ans = val;
                diff = Math.abs(val);
            }

            if(val >= 0) right--;
            else left++;
        }

        System.out.println(ans);
    }

}
