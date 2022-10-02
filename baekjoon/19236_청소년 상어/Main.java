import java.io.*;
import java.util.*;

public class Main {

    public static class Pos{
        int x, y;
        int num, dir;
        int eat;

        public Pos(int x, int y, int num, int dir) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.dir = dir;
        }

        public Pos(int x, int y, int num, int dir, int eat) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.dir = dir;
            this.eat = eat;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "x=" + x +
                    ", y=" + y +
                    ", num=" + num +
                    ", dir=" + dir +
                    ", eat=" + eat +
                    '}';
        }
    }

    public static int n = 4, size, ans;

    public static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    public static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        size = n*n;
        int[][] map = new int[n][n];
        Pos[] fishes = new Pos[size+1];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                fishes[num] = new Pos(i, j, num, dir);
                map[i][j] = num;
            }
        }

        Pos shark = new Pos(fishes[map[0][0]].x, fishes[map[0][0]].y, 0, fishes[map[0][0]].dir, fishes[map[0][0]].num);
        fishes[map[0][0]].dir = 0;
        map[0][0] = -1;

        dfs(map, fishes, shark);

        System.out.println(ans);


    }

    public static void dfs(int[][] map, Pos[] fishes, Pos shark){
        if(ans < shark.eat){
            ans = shark.eat;
        }

        moveFish(map, fishes);

        int x = shark.x;
        int y = shark.y;

        for(int d = 1; d < 4; d++){
            int nx = x + dx[shark.dir] * d;
            int ny = y + dy[shark.dir] * d;

            if(!isRange(nx, ny)) continue;
            if(map[nx][ny] <= 0) continue;

            int[][] cMap = copyMap(map);
            Pos[] cFishes = copyFishes(fishes);

            Pos eatenFish = cFishes[cMap[nx][ny]];
            Pos newShark = new Pos(eatenFish.x, eatenFish.y, 0, eatenFish.dir, shark.eat+eatenFish.num);
            eatenFish.dir = 0;
            cMap[x][y] = 0;
            cMap[eatenFish.x][eatenFish.y] = -1;

            dfs(cMap, cFishes, newShark);
        }
    }

    public static void moveFish(int[][] map, Pos[] fishes){
        for(int i = 1; i <= size; i++){
            Pos fish = fishes[i];
            int x = fish.x;
            int y = fish.y;
            int dir = fish.dir;

            if(dir == 0) continue;

            for(int j = 0; j < 9; j++){
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(!isRange(nx, ny)) {
                    dir++;
                    if(dir == 9) dir = 1;
                    continue;
                }

                if(map[nx][ny] > -1){
                    if(map[nx][ny] == 0){
                        map[x][y] = 0;
                    }else {
                        Pos changedFish = fishes[map[nx][ny]];
                        changedFish.x = fish.x;
                        changedFish.y = fish.y;
                        map[fish.x][fish.y] = changedFish.num;
                    }
                    fish.x = nx;
                    fish.y = ny;

                    map[nx][ny] = fish.num;
                    fish.dir = dir;
                    break;
                }

                dir++;
                if(dir == 9) dir = 1;
            }

        }
    }

    public static int[][] copyMap(int[][] map){
        int[][] copy = new int[n][n];

        for(int i = 0; i < 4; i++){
            copy[i] = map[i].clone();
        }

        return copy;
    }

    public static Pos[] copyFishes(Pos[] fishes){
        Pos[] copy = new Pos[size+1];
        for(int i = 1; i <= size; i++){
            Pos fish = fishes[i];
            copy[i] = new Pos(fish.x, fish.y, fish.num, fish.dir);
        }

        return copy;
    }
    
    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
