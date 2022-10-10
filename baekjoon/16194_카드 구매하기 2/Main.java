import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] cost = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 2; i <= n; i++){
            for(int j = i-1; j >= i/2; j--){
                cost[i] = Math.min(cost[i], cost[j] + cost[i-j]);
            }
        }

        System.out.println(cost[n]);
    }

}
