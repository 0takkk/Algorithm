import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        while(t-->0){
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int[] arr = new int[n];
            for(int i = 0; i < n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);

            long result = 0;
            for(int i = 2; i <= n; i++){
                long sum = 0;
                long min = Integer.MAX_VALUE;

                for(int j = 0; j < i; j++){
                    sum += arr[j];
                }
                min = (long)arr[i-1] * i - sum;

                for(int j = 1; j < n-i+1; j++){
                    sum -= arr[j-1];
                    sum += arr[j+i-1];

                    min = Math.min((long)arr[j+i-1] * i - sum, min);
                }

                result += min;
            }

            System.out.println(result);
        }
    }
}
