import java.io.*;
import java.util.*;

public class Main {

    public static int[][] map;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[3][3];
        int o = 0;
        int x = 0;

        for(int i = 0; i < 3; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 1) x++;
                else if(map[i][j] == 2) o++;
            }
        }

        int player = x <= o ? 1 : 2;

        int result = game(player);
        char ans;
        if(result == 1) ans = 'W';
        else if(result == 0) ans = 'D';
        else ans = 'L';

        System.out.println(ans);
    }

    public static int game(int now){
        int min = 2;  // 상대방의 최선 선택

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(map[i][j] == 0){
                    map[i][j] = now;
                    if(isWin(now)) min = -1;
                    min = Math.min(min, game(now == 1 ? 2 : 1));
                    map[i][j] = 0;
                }
            }
        }

        if(min == -1) return 1;
        else if(min == 0 || min == 2) return 0;
        else return -1;
    }

    public static boolean isWin(int now){
        for(int i = 0; i < 3; i++){
            if(map[i][0] == now && map[i][1] == now && map[i][2] == now) return true;
            if(map[0][i] == now && map[1][i] == now && map[2][i] == now) return true;
        }

        if(map[0][0] == now && map[1][1] == now && map[2][2] == now) return true;
        if(map[0][2] == now && map[1][1] == now && map[2][0] == now) return true;

        return false;
    }

}
