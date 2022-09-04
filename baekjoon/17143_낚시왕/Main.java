import java.io.*;
import java.util.*;

public class Main {

    public static class Shark{
        int x, y;
        int speed, dir, size;

        public Shark(int x, int y, int speed, int dir, int size) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }
    }

    public static int n, m, ans;
    public static int[][] map;
    public static Shark[] sharks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map = new int[n+1][m+1];
        sharks = new Shark[k+1];

        for(int i = 1; i <= k; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());

            sharks[i] = new Shark(x, y, speed, dir, size);
            map[x][y] = i;
        }

        moveFisher();

        System.out.println(ans);
    }

    public static void moveFisher(){
        for(int j = 1; j <= m; j++){
            for(int i = 1; i <= n; i++){
                if(map[i][j] != 0){
                    ans += sharks[map[i][j]].size;
                    map[i][j] = 0;
                    break;
                }
            }

            moveShark();
        }
    }

    public static void moveShark(){
        int[][] copy = new int[n+1][m+1];

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                int num = map[i][j];
                if(num == 0) continue;

                Shark shark = sharks[num];

                int range = shark.dir < 3 ? n : m;
                int dist = shark.speed % ((range-1) * 2);
                int x = shark.x;
                int y = shark.y;
                int dir = shark.dir;

                for(int k = 0; k < dist; k++){
                    if(dir == 1){
                        x--;
                        if(x == 0){
                            x = 2;
                            dir = 2;
                        }
                    } else if(dir == 2){
                        x++;
                        if(x == n+1){
                            x = n-1;
                            dir = 1;
                        }
                    } else if(dir == 3){
                        y++;
                        if(y == m+1){
                            y = m-1;
                            dir = 4;
                        }
                    }else{
                        y--;
                        if(y == 0){
                            y = 2;
                            dir = 3;
                        }
                    }
                }

                if(copy[x][y] != 0 && sharks[copy[x][y]].size > shark.size) continue;
                copy[x][y] = num;
                sharks[num] = new Shark(x, y, shark.speed, dir, shark.size);
            }
        }

        map = copy;
    }

}
