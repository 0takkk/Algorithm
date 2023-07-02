import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[] dist = new int[d+1];
        int[][] shortcut = new int[n][3];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            shortcut[i] = new int[]{start, end, time};
        }

        for(int i = 1; i <= d; i++){
            int cost = dist[i-1] + 1;

            for(int j = 0; j < n; j++){
                if(shortcut[j][1] == i){
                    cost = Math.min(cost, shortcut[j][2] + dist[shortcut[j][0]]);
                }
            }

            dist[i] = cost;
        }

        System.out.println(dist[d]);
    }

}
