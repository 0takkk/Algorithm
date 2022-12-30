import java.io.*;
import java.util.*;

public class Main {

    public static int n = 100;
    public static boolean[][] map;

    public static int[] dy = {0, -1, 0, 1};
    public static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new boolean[n+1][n+1];
        int m = Integer.parseInt(br.readLine());

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            dragon(x, y, d, g);
        }

        int ans = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]){
                    ans++;
                }
            }
        }

        System.out.println(ans);
    }

    public static void dragon(int x, int y, int d, int g){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(d);

        while(g-->0){
            for(int i = list.size()-1; i >= 0; i--){
                list.add((list.get(i) + 1) % 4);
            }
        }

        map[y][x] = true;
        for (int dir : list) {
            x += dx[dir];
            y += dy[dir];
            map[y][x] = true;
        }
    }

}
