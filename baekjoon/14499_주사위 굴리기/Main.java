import java.io.*;
import java.util.*;

public class Main {

    public static class Dice{
        int x, y;
        int[] num = new int[6];

        public Dice(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int n, m;
    public static int[][] map;
    public static Dice dice;
    public static StringBuilder sb = new StringBuilder();

    public static int[] dx = {0, 0, 0, -1, 1};
    public static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        dice = new Dice(x, y);

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        while(k-->0){
            int dir = Integer.parseInt(st.nextToken());
            move(dir);
        }

        System.out.println(sb.toString());
    }

    public static void move(int dir){
        int nx = dice.x + dx[dir];
        int ny = dice.y + dy[dir];

        if(!isRange(nx, ny)) return;

        int[] num = dice.num;
        int[] tmp = num.clone();

        switch (dir){
            case 1:
                for(int i = 0; i < 3; i++) tmp[i] = num[i+1];
                tmp[3] = num[0];
                break;
            case 2:
                for(int i = 1; i < 4; i++) tmp[i] = num[i-1];
                tmp[0] = num[3];
                break;
            case 3:
                tmp[0] = num[4];
                tmp[2] = num[5];
                tmp[4] = num[2];
                tmp[5] = num[0];
                break;
            default:
                tmp[0] = num[5];
                tmp[2] = num[4];
                tmp[4] = num[0];
                tmp[5] = num[2];
                break;
        }

        dice.num = tmp;
        dice.x = nx;
        dice.y = ny;

        if(map[nx][ny] == 0) map[nx][ny] = dice.num[0];
        else {
            dice.num[0] = map[nx][ny];
            map[nx][ny] = 0;
        }

        sb.append(dice.num[2]).append("\n");
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}
