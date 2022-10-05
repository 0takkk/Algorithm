import java.io.*;
import java.util.*;

public class Main {

    public static int n, m, ans;
    public static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        dfs(0);
        System.out.println(ans);
    }

    public static void dfs(int idx){
        if(idx == n*m){
            ans++;
            return;
        }

        int x = idx/m;
        int y = idx%m;

        if(leftUpBox(x, y)){
            map[x][y] = 1;
            dfs(idx+1);
            map[x][y] = 0;
        }
        dfs(idx+1);
    }

    public static boolean leftUpBox(int x, int y){
        if(isRange(x-1, y-1) && isRange(x-1, y) && isRange(x, y-1)){
            if(map[x-1][y-1] == 1 && map[x-1][y] == 1 && map[x][y-1] == 1) return false;
        }
        return true;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}
