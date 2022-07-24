import java.io.*;
import java.util.*;

public class Main {

    public static class Pos{
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int n, m, d, ans, result;
    public static boolean[] archer;
    public static int[][] enemy, copy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        enemy = new int[n][m];
        archer = new boolean[m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                enemy[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int t = 0; t <= n-3; t++){
            positionArcher(t, 0);
        }

        System.out.println(result);
    }

    public static void positionArcher(int idx, int cnt){
        if(cnt == 3){
            ans = 0;
            copy = new int[n][m];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    copy[i][j] = enemy[i][j];
                }
            }

            game();
            result = Math.max(ans, result);
            return;
        }

        for(int i = idx; i < m; i++){
            if(!archer[i]){
                archer[i] = true;
                positionArcher(i+1, cnt+1);
                archer[i] = false;
            }
        }
    }

    public static void game(){
        while(true){
            if(isFinish()) return;
            shoot();
            move();
        }
    }

    public static void shoot(){
        ArrayList<Pos> list = new ArrayList<>();

        for(int a = 0; a < m; a++){
            if(!archer[a]) continue;

            Pos target = null;
            int min = 100;
            for(int i = n-1; i >= 0; i--){
                for(int j = 0; j < m; j++){
                    if(copy[i][j] == 1){
                        int dist = (n-i + Math.abs(a-j));

                        if(dist <= d && dist <= min) {
                            if(dist == min && target.y < j){
                                continue;
                            }

                            target = new Pos(i, j);
                            min = dist;
                        }
                    }
                }
            }

            if(target != null) list.add(target);
        }

        for (Pos p : list) {
            if(copy[p.x][p.y] == 1){
                ans++;
                copy[p.x][p.y] = 0;
            }
        }
    }

    public static void move(){
        Arrays.fill(copy[n-1], 0);

        for(int i = n-1; i >= 1; i--){
            for(int j = 0; j < m; j++){
                copy[i][j] = copy[i-1][j];
            }
        }

        Arrays.fill(copy[0], 0);
    }

    public static boolean isFinish(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(copy[i][j] == 1) return false;
            }
        }
        return true;
    }
}
