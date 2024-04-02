import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        long len = 3;
        int k = 0;

        while(len < n) {
            k++;
            len = len*2 + k+3;
        }

        System.out.println(rec(n, k, len));
    }

    public static char rec(long n, int k, long len) {
        long pre = (len - k - 3) / 2;

        if(pre >= n) {
            return rec(n, k-1, pre);
        }
        else if(pre+k+3 < n) {
            return rec(n-pre-k-3, k-1, pre);
        }
        else if(pre+1 == n) {
            return 'm';
        }
        else return 'o';
    }

}