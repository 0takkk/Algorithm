import java.io.*;
import java.util.*;

public class Main {

    public static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        long n = Long.parseLong(br.readLine());

        arr = new long[65];
        arr[0] = 1;

        for(int i = 1; i < 65; i++) {
            arr[i] = arr[i-1] * 2;
        }

        System.out.println(rec(n));
    }

    public static int rec(long x) {
        if(x == 1) {
            return 0;
        }

        for(int i = 0; i < 65; i++) {
            if(arr[i] >= x) {
                return 1 - rec(x - arr[i-1]);
            }
        }

        return 0;
    }

}