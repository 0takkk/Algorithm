import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        long a = Integer.parseInt(st.nextToken());
        long b = Integer.parseInt(st.nextToken());
        long c = Integer.parseInt(st.nextToken());
        long d = Integer.parseInt(st.nextToken());

        if(c * b < a * d){
            long tmp = a;
            a = c;
            c = tmp;

            tmp = b;
            b = d;
            d = tmp;
        }

        long ans = Long.MAX_VALUE;

        for(int i = 0; i < c; i++){
            long s = (long) Math.ceil((double) (n - i * a) / c);

            boolean flag = false;
            if(s < 0){
                s = 0;
                flag = true;
            }

            ans = Math.min(ans, i * b + s * d);
            if(flag) break;
        }

        System.out.println(ans);
    }

}
