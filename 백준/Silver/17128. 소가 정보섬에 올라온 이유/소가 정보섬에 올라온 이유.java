import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[] cow = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            cow[i] = Integer.parseInt(st.nextToken());
        }

        int[] mul = new int[n+1];
        for(int i = 1; i <= n; i++){
            mul[i] = 1;
            int idx = i;
            int cnt = 0;

            while(cnt++ < 4){
                mul[i] *= cow[idx];
                if(++idx > n) idx = 1;
            }
        }

        st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        int sum = 0;
        for(int i = 1; i <= n; i++){
            sum += mul[i];
        }

        while(q-->0){
            int change = Integer.parseInt(st.nextToken());
            int cnt = 0;

            while(cnt++ < 4){
                mul[change] *= -1;
                sum += (2 * mul[change]);
                if(--change == 0) change = n;
            }

            sb.append(sum).append("\n");
        }

        System.out.println(sb.toString());
    }

}
