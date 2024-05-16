import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        while(m-->0) {
            int target = Integer.parseInt(br.readLine());
            sb.append(binary(target)).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static int binary(int target) {
        int left = 0;
        int right = n-1;

        while(left < right) {
            int mid = (left + right) / 2;

            if(arr[mid] >= target) {
                right = mid;
            }
            else {
                left = mid+1;
            }
        }

        if(arr[right] == target) return right;
        return -1;
    }

}
