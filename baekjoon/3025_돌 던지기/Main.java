import java.util.*;
import java.io.*;

public class Main {

    public static class Pos{
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int r, c;
    public static char[][] map;
    public static Stack<Pos>[] dp;


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r+1][c+1];
        dp = new Stack[c+1];

        for(int i = 1; i <= r; i++) {
            String str = br.readLine();
            for(int j = 1; j <= c; j++) {
                map[i][j] = str.charAt(j-1);
            }
        }

        for(int i = 1; i <= c; i++){
            dp[i] = new Stack<>();
        }

        int n = Integer.parseInt(br.readLine());
        while(n-->0){
            int startY = Integer.parseInt(br.readLine());

            while(!dp[startY].isEmpty() && map[dp[startY].peek().x][dp[startY].peek().y] == 'O'){
                dp[startY].pop();
            }
            if(dp[startY].isEmpty()) fall(1, startY, startY);
            else fall(dp[startY].peek().x, dp[startY].peek().y, startY);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= r; i++) {
            for(int j = 1; j <= c; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void fall(int x, int y, int startY){
        while(x+1 <= r && map[x+1][y] != 'X'){
            if(map[x+1][y] == 'O'){
                if(isRange(x+1, y-1) && map[x][y-1] == '.' && map[x+1][y-1] == '.'){
                    x++;
                    y--;
                }
                else if(isRange(x+1, y+1) && map[x][y+1] == '.' && map[x+1][y+1] == '.'){
                    x++;
                    y++;
                }
                else break;
            }
            else x++;

            dp[startY].push(new Pos(x, y));
        }

        map[x][y] = 'O';
    }

    public static boolean isRange(int x, int y){
        return x > 0 && x <= r && y > 0 && y <= c;
    }

}
