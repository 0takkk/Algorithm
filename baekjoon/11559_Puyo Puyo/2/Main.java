import java.io.*;
import java.util.*;

public class Main {

    public static char[][] map;
    public static boolean[][] visited;
    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static class Pos{
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new char[12][6];
        for(int i = 0; i < 12; i++){
            String str = br.readLine();
            for(int j = 0; j < 6; j++){
                map[i][j] = str.charAt(j);
            }
        }

        int result = 0;

        while(true){
            visited = new boolean[12][6];
            boolean flag = false;

            for(int i = 0; i < 12; i++){
                for(int j = 0; j < 6; j++){
                    if(!visited[i][j] && map[i][j] != '.'){
                        if(check(i, j, map[i][j])){
                            destroy(i, j, map[i][j]);
                            flag = true;
                        }
                    }
                }
            }

            if(flag) result++;
            else break;

            drop();
        }

        System.out.println(result);
    }

    public static boolean check(int i, int j, char c){
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(i, j));
        visited[i][j] = true;
        int count = 1;

        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;

            for(int d = 0; d < 4; d++){
                int nx = x + dx[d];
                int ny = y + dy[d];

                if(count >= 4) return true;

                if(!isRange(nx, ny)) continue;

                if(!visited[nx][ny] && map[nx][ny] == c){
                    visited[nx][ny] = true;
                    count++;
                    q.offer(new Pos(nx, ny));
                }
            }
        }

        return false;
    }

    public static void destroy(int i, int j, char c){
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(i, j));
        map[i][j] = '.';

        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;

            for(int d = 0; d < 4; d++){
                int nx = x + dx[d];
                int ny = y + dy[d];

                if(!isRange(nx, ny)) continue;

                if(map[nx][ny] == c){
                    q.offer(new Pos(nx, ny));
                    map[nx][ny] = '.';
                }
            }
        }
    }

    public static void drop(){
        for(int j = 0; j < 6; j++){
            int count = map[11][j] == '.' ? 1 : 0;
            for(int i = 10; i >= 0; i--){
                if(map[i][j] == '.') count++;

                else if(map[i+1][j] == '.'){
                    map[i+count][j] = map[i][j];
                    map[i][j] = '.';
                }
            }
        }
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < 12 && y >= 0 && y < 6;
    }
}
