import java.io.*;
import java.util.*;

public class Main {

    public static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] dist = new int[n+1][n+1];
        for(int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], MAX);
        }

        while(m-->0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            dist[a][b] = Math.min(dist[a][b], c);
        }

        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                if(dist[i][k] == MAX) continue;
                for(int j = 1; j <= n; j++) {
                    if(i == j) dist[i][j] = 0;
                    if(dist[k][j] == MAX) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int k = Integer.parseInt(br.readLine());
        int[] states = new int[k];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++) {
            states[i] = Integer.parseInt(st.nextToken());
        }

        int totalDist = MAX;
        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 1; i <= n; i++) {
            int tmpDist = 0;
            for (int state : states) {
                tmpDist = Math.max(tmpDist, dist[state][i] + dist[i][state]);
            }

            if(tmpDist < totalDist) {
                totalDist = tmpDist;
                list = new ArrayList<>();
                list.add(i);
            }
            else if(tmpDist == totalDist) {
                list.add(i);
            }
        }

        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        for (Integer i : list) {
            sb.append(i).append(" ");
        }

        System.out.println(sb.toString());
    }

}