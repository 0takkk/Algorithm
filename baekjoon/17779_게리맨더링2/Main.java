import java.io.*;
import java.util.*;

public class Main {

    public static int n, total;
    public static int[][] A;
    public static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        A = new int[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
                total += A[i][j];
            }
        }

        for(int x = 0; x < n; x++){
            for(int y = 0; y < n; y++){
                for(int d1 = 1; d1 < n; d1++){
                    for(int d2 = 1; d2 < n; d2++){
                        if(x + d1 + d2 >= n) continue;
                        if(y - d1 < 0 || y + d2 >= n) continue;

                        solution(x, y, d1, d2);
                    }
                }
            }
        }

        System.out.println(min);
    }

    public static void solution(int x, int y, int d1, int d2){
        boolean[][] map = new boolean[n][n];

        for(int i = 0; i <= d1; i++){
            map[x+i][y-i] = true;
            map[x+d2+i][y+d2-i] = true;
        }

        for(int i = 0; i <= d2; i++){
            map[x+i][y+i] = true;
            map[x+d1+i][y-d1+i] = true;
        }

        int[] sum = new int[5];

        // 1구역
        for(int i = 0; i < x + d1; i++){
            for(int j = 0; j <= y; j++){
                if(map[i][j]) break;
                sum[0] += A[i][j];
            }
        }

        // 2구역
        for(int i = 0; i <= x + d2; i++){
            for(int j = n-1; j > y; j--){
                if(map[i][j]) break;
                sum[1] += A[i][j];
            }
        }

        // 3구역
        for(int i = x + d1; i < n; i++){
            for(int j = 0; j < y - d1 + d2; j++){
                if(map[i][j]) break;
                sum[2] += A[i][j];
            }
        }

        // 4구역
        for(int i = x + d2 + 1; i < n; i++){
            for(int j = n-1; j >= y - d1 + d2; j--){
                if(map[i][j]) break;
                sum[3] += A[i][j];
            }
        }

        sum[4] = total;

        for(int i = 0; i < 4; i++)
            sum[4] -= sum[i];

        Arrays.sort(sum);

        min = Math.min(min, sum[4] - sum[0]);
    }
}
