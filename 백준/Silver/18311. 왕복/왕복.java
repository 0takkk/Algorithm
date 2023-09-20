import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < n; i++) {
            k -= arr[i];
            if(k < 0) {
                System.out.println(i+1);
                return;
            }
        }

        for(int i = n-1; i >= 0; i--) {
            k -= arr[i];
            if(k < 0) {
                System.out.println(i+1);
                return;
            }
        }
    }

}
