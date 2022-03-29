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
            int count = 0;

            for(int i = n; i <= m; i++){
                int num = i;
                for(int j = 10; j <= Math.max(i, 10); j *= 10){
                    int r = num % j;
                    if(r == 0) count++;
                    num -= r;
                }
            }

            sb.append(count+"\n");
        }
        System.out.println(sb.toString());
    }

}
