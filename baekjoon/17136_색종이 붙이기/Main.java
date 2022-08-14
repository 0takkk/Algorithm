import java.io.*;
import java.util.*;

public class Main {

    public static int ans = Integer.MAX_VALUE;
    public static int[][] map;
    public static int[] paper = new int[]{0, 5, 5, 5, 5, 5};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[10][10];
        for(int i = 0; i < 10; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 10; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    public static void dfs(int x, int y, int cnt){
        if(x == 9 && y == 10){
            ans = Math.min(ans, cnt);
            return;
        }

        if(ans < cnt) return;

        if(y == 10){
            dfs(x+1, 0, cnt);
            return;
        }

        if(map[x][y] == 1){
            for(int size = 5; size >= 1; size--){
                if(paper[size] > 0 && isRange(x+size, y+size) && isAttach(x, y, size)){
                    attach(x, y, size, 0);
                    paper[size]--;

                    dfs(x, y+1, cnt+1);

                    attach(x, y, size, 1);
                    paper[size]++;
                }
            }
        } else dfs(x, y+1, cnt);
    }

    public static void attach(int x, int y, int size, int state){
        for(int i = x; i < x+size; i++){
            for(int j = y; j < y+size; j++){
                map[i][j] = state;
            }
        }
    }

    public static boolean isAttach(int x, int y, int size){
        for(int i = x; i < x+size; i++){
            for(int j = y; j < y+size; j++){
                if(map[i][j] == 0) return false;
            }
        }
        return true;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x <= 10 && y >= 0 && y <= 10;
    }

}
