import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int size = 1000001;
        int[] arr = new int[size];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            arr[x] = g;
        }

        int sum = 0;
        int max = 0;
        int d = 2 * k + 1;

        for(int i = 0; i < size; i++){
            if(i >= d){
                sum -= arr[i-d];
            }
            sum += arr[i];

            max = Math.max(max, sum);
        }

        System.out.println(max);
    }

}
