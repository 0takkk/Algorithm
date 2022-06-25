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

        int max = arr[0];
        int[] left = new int[n];
        left[0] = arr[0];
        for(int i = 1; i < n; i++){
            left[i] = Math.max(left[i-1] + arr[i], arr[i]);
            max = Math.max(left[i], max);
        }

        int[] right = new int[n];
        right[n-1] = arr[n-1];
        for(int i = n-2; i >= 0; i--){
            right[i] = Math.max(right[i+1] + arr[i], arr[i]);
        }

        for(int i = 1; i < n-1; i++){
            max = Math.max(max, left[i-1] + right[i+1]);
        }

        System.out.println(max);
    }

}
