import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-->0){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] rows = new int[n+1];
            int[] cols = new int[n+1];

            for(int i = 1; i <= n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 1; j <= n; j++){
                    int num = Integer.parseInt(st.nextToken());
                    rows[i] += num;
                    cols[j] += num;
                }
            }

            while(m-->0){
                st = new StringTokenizer(br.readLine());
                int r1 = Integer.parseInt(st.nextToken());
                int c1 = Integer.parseInt(st.nextToken());
                int r2 = Integer.parseInt(st.nextToken());
                int c2 = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                for(int i = r1; i <= r2; i++) rows[i] += (c2-c1+1) * v;
                for(int i = c1; i <= c2; i++) cols[i] += (r2-r1+1) * v;
            }

            for(int i = 1; i <= n; i++) sb.append(rows[i]).append(" ");
            sb.append("\n");
            for(int i = 1; i <= n; i++) sb.append(cols[i]).append(" ");
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

}
