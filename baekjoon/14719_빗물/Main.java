import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        st = new StringTokenizer(br.readLine());

        for(int j = 0; j < m; j++){
            int h = Integer.parseInt(st.nextToken());
            for(int i = n-h; i < n; i++){
                map[i][j] = 1;
            }
        }

        int ans = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(map[i][j] != 0) continue;

                int left = j, right = j;

                while(left >= 0 && map[i][left] == 0){
                    left--;
                }
                
                while(right < m && map[i][right] == 0){
                    right++;
                }

                if(left == -1 || right == m) continue;

                for(int k = left+1; k < right; k++){
                    map[i][k] = 2;
                    ans++;
                }
            }
        }

        System.out.println(ans);
    }

}
