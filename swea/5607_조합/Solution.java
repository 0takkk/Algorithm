import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        int p = 1234567891;

        // n! / (r! * (n-r)!) mod p => n! * (r! * (n-r)!)^(p-2) mod p
        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            long ans = 1;
            for(int i = r+1; i <= n; i++) ans = (ans*i) % p;

            long a = 1;
            for(int i = 1; i <= n-r; i++) a = (a*i) % p;

            // p의 역원 사용
            long tmp = p-2;
            while(tmp > 0){
                if(tmp % 2 == 1) ans = (ans * a) % p;

                tmp /= 2; // 분할 정복
                a = (a*a) % p;
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

}
