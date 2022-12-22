import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int INF = 987654321;
        int[][] dist = new int[n+1][n+1];
        for(int i = 1; i <= n; i++) Arrays.fill(dist[i], INF);

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            dist[a][b] = dist[b][a] = 1;
        }

        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(i == j) dist[i][j] = 0;
            }
        }

        int min = INF;
        int s1 = 0, s2 = 0, cost = 0;

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                int sum = 0;
                for(int k = 1; k <= n; k++){
                    sum += (Math.min(dist[i][k], dist[j][k]) * 2);
                }

                if(min > sum){
                    s1 = i;
                    s2 = j;
                    cost = sum;
                    min = sum;
                }
            }
        }

        System.out.println(s1 + " " + s2 + " " + cost);
    }

}
