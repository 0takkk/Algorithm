import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static int[][] map, copy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while(t-->0){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int angle = Integer.parseInt(st.nextToken());

            map = new int[n][n];
            copy = new int[n][n];

            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    copy[i][j] = map[i][j];
                }
            }

            int count = angle / 45;
            if(count < 0) count += 8;
            else if(count == 8) count = 0;

            rotate(count);

            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    public static void rotate(int count){
        while(count-->0){
            rotate();
        }
    }

    public static void rotate(){
        for(int i = 0; i < n; i++){
            copy[i][i] = map[n/2][i];
            copy[i][n/2] = map[i][i];
            copy[n/2][i] = map[n-i-1][i];
            copy[n-i-1][i] = map[n-i-1][n/2];
        }

       for(int i = 0; i < n; i++){
           for(int j = 0; j < n; j++){
               map[i][j] = copy[i][j];
           }
       }
    }

}
