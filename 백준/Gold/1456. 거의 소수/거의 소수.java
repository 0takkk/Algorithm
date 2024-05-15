import java.io.*;
import java.util.*;

public class Main {

    public static boolean[] isPrime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        isPrime = new boolean[(int)Math.sqrt(b)+1];
        calcPrime();

        int ans = 0;
        for(int i = 2; i < isPrime.length; i++) {
            if(isPrime[i]) {
                long num = i;
                while((double) i <= (double) b / (double) num) {
                    if((double) i >= (double) a / (double) num) {
                        ans++;
                    }
                    num *= i;
                }
            }
        }

        System.out.println(ans);
    }

    public static void calcPrime() {
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for(int i = 2; i <= (int)Math.sqrt(isPrime.length)+1; i++) {
            if(isPrime[i]) {
                for (int j = i * i; j < isPrime.length; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

}
