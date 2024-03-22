import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int l = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int ml = Integer.parseInt(st.nextToken());
        int mk = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(br.readLine());

        int[] zombi = new int[l];
        for(int i = 0; i < l; i++) {
            zombi[i] = Integer.parseInt(br.readLine());
        }

        int sum = mk;

        for(int i = 0; i < l; i++) {
            if(zombi[i]-sum > 0) {
                if(c > 0) {
                    zombi[i] = 0;
                    c--;
                }
                else {
                    System.out.println("NO");
                    return;
                }
            } else {
                if(i+ml < l) {
                    zombi[i+ml] += sum;
                }
                sum+=mk;
            }
        }

        System.out.println("YES");
    }

}