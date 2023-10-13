import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        long left = 0;
        long right = n/2;

        while(left <= right) {
            long row = (left + right) / 2;
            long col = n - row;

            long cnt = (row+1) * (col+1);

            if(cnt == k) {
                System.out.println("YES");
                return;
            }
            else if(cnt > k) {
                right = row-1;
            } else {
                left = row+1;
            }
        }

        System.out.println("NO");
    }

}