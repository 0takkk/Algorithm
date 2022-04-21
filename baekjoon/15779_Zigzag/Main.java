import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] zigzag = new int[n];
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int max = 0;

        for(int i = 2; i < n; i++){
            int c = Integer.parseInt(st.nextToken());
            zigzag[i] = ((a <= b && b <= c) || (a >= b && b >= c)) ? 0 : zigzag[i-1] + 1;
            a = b;
            b = c;

            max = Math.max(max, zigzag[i]);
        }

        System.out.println(max+2);
    }

}
