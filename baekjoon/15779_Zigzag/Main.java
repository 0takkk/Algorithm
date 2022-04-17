import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int[] zigzag = new int[n];
        for(int i = 2; i < n; i++){
            int a = arr[i];
            int b = arr[i-1];
            int c = arr[i-2];
            zigzag[i] = ((a >= b && b >= c) || (a <= b && b <= c)) ? 0 : (zigzag[i-1] == 0 ? 1 : zigzag[i-1]+1);
        }

        int max = 0;
        for(int i = 0; i < n; i++){
            max = Math.max(max, zigzag[i]);
        }

        System.out.println(max+2);
    }
}
