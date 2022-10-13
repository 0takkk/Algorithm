import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            long n = Long.parseLong(st.nextToken());
            long r = Long.parseLong(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            long[] fact = new long[p];
            fact[0] = 1;
            for(int i = 1; i < p; i++){
                fact[i] = (fact[i-1] * i) % p;
            }

            long ans = 1;
            // 뤼카의 정리
            while(n > 0 || r > 0){
                int x = (int) (n % p);
                int y = (int) (r % p);

                if(x < y){
                    ans = 0;
                    break;
                }

                // 페르마의 소정리
                ans = (ans * fact[x]) % p;
                ans = (ans * power((fact[x-y] * fact[y]) % p, p-2, p)) % p;

                n /= p;
                r /= p;
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static long power(long base, int exp, int p){
        if(exp == 1) return base;

        long val = power(base, exp/2, p);
        val = (val * val) % p;
        if(exp % 2 == 0) return val;
        else return (val * base) % p;
    }

}
