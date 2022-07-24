import java.io.*;
import java.util.*;

public class Main {


    public static int n;
    public static int[][] map;
    public static boolean[][] origin;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n+1][n+1];
        origin = new boolean[n+1][n+1];

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){

                    if(i == j || i == k || j == k) continue;

                    if(map[i][j] == (map[i][k] + map[k][j])){
                        origin[i][j] = true;
                    } else if(map[i][j] > (map[i][k] + map[k][j])){
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        int sum = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(!origin[i][j]) sum += map[i][j];
            }
        }
        System.out.println(sum/2);
    }
}
