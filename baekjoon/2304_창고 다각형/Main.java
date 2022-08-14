import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] arr= new int[1002];
        int[] left = new int[1002];
        int[] right = new int[1002];

        int n = Integer.parseInt(br.readLine());

        while(n-->0){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            arr[l] = h;
        }

        for(int i = 1; i <= 1000; i++){
            left[i] = Math.max(arr[i], left[i-1]);
            right[1001-i] = Math.max(arr[1001-i], right[1002-i]);
        }

        int ans = 0;
        for(int i = 1; i <= 1000; i++){
            ans += Math.min(left[i], right[i]);
        }

        System.out.println(ans);
    }

}
