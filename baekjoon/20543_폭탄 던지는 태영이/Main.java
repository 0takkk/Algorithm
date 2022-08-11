import java.io.*;
import java.util.*;

public class Main {

    public static int n, m;
    public static long[][] map, bomb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new long[n][n];
        bomb = new long[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = -1 * Long.parseLong(st.nextToken());
            }
        }

        int r = m/2;
        int start = r;
        int end = n-r;

        for(int i = start; i < end; i++){
            for(int j = start; j < end; j++){
                bomb[i][j] = map[i-r][j-r];

                if(i-r-1 >= 0) bomb[i][j] -= map[i-r-1][j-r];
                if(j-r-1 >= 0) bomb[i][j] -= map[i-r][j-r-1];
                if(i-r-1 >= 0 && j-r-1 >= 0) bomb[i][j] += map[i-r-1][j-r-1];
                if(i-m >= 0) bomb[i][j] += bomb[i-m][j];
                if(j-m >= 0) bomb[i][j] += bomb[i][j-m];
                if(i-m >= 0 && j-m >= 0) bomb[i][j] -= bomb[i-m][j-m];
            }
        }


        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                sb.append(bomb[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }


}
