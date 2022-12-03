import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] map = new int[31][31];

        for(int i = 1; i < 31; i++){
            map[i][1] = map[i][i] = 1;
            for(int j = 2; j < i; j++){
                map[i][j] = map[i-1][j-1] + map[i-1][j];
            }
        }

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int ans = 0;
        for(int i = r; i < r+w; i++){
            int y = c + (i-r);
            for(int j = c; j <= y; j++){
                ans += map[i][j];
            }
        }

        System.out.println(ans);
    }

}
