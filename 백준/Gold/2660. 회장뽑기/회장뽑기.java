import java.io.*;
import java.util.*;

public class Main {

    public static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] dist = new int[n+1][n+1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i != j) dist[i][j] = INF;
            }
        }

        while(true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a == -1 && b == -1) break;

            dist[a][b] = dist[b][a] = 1;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int min = INF;
        ArrayList<Integer> result = new ArrayList<>();

        for(int i = 1; i <= n; i++) {
            int cost = 0;
            for(int j = 1; j <= n; j++) {
                if(i != j) {
                    cost = Math.max(cost, dist[i][j]);
                }
            }

            if(min > cost) {
                result.clear();
                result.add(i);
                min = cost;
            }
            else if(min == cost) {
                result.add(i);
            }
        }

        System.out.println(min + " " + result.size());
        Collections.sort(result);
        StringBuilder sb = new StringBuilder();
        for (Integer i : result) {
            sb.append(i).append(" ");
        }

        System.out.println(sb.toString());
    }

}