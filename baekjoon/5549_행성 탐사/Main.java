import java.io.*;
import java.util.*;

public class Main {

    public static int[][][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());

        map = new int[3][n+1][m+1];

        for(int i = 1; i <= n; i++){
            String str = br.readLine();
            for(int j = 1; j <= m; j++){
                char c = str.charAt(j-1);

                if(c == 'J') map[0][i][j] = 1;
                else if(c == 'O') map[1][i][j] = 1;
                else map[2][i][j] = 1;
            }
        }

        for(int i = 2; i <= n; i++){
            for(int d = 0; d < 3; d++){
                map[d][i][1] += map[d][i-1][1];
            }
        }

        for(int j = 2; j <= m; j++){
            for(int d = 0; d < 3; d++){
                map[d][1][j] += map[d][1][j-1];
            }
        }

        for(int i = 2; i <= n; i++){
            for(int j = 2; j <= m; j++){
                for(int d = 0; d < 3; d++){
                    map[d][i][j] = map[d][i-1][j] + map[d][i][j-1] + map[d][i][j] - map[d][i-1][j-1];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while(k-->0){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for(int i = 0; i < 3; i++){
                sb.append(cal(x1, y1, x2, y2, i)).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static int cal(int x1, int y1, int x2, int y2, int d){
        int[][] tmp = map[d];
        return tmp[x2][y2] - tmp[x1-1][y2] - tmp[x2][y1-1] + tmp[x1-1][y1-1];
    }

}
