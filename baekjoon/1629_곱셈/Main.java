import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long c = Long.parseLong(st.nextToken());

        System.out.println(pow(a, b, c));
    }

    public static long pow(long a, long b, long c){
        if(b == 1) return a % c;

        long tmp = pow(a, b/2, c);

        if(b % 2 == 1){
            return (tmp * tmp % c) * a % c;
        }

        return tmp * tmp % c;
    }

}
