import java.io.*;
import java.util.*;

public class Main {

    public static class Pos {
        int x, y;
        int health, defence;
        int move;

        public Pos(int x, int y, int health, int defence, int move) {
            this.x = x;
            this.y = y;
            this.health = health;
            this.defence = defence;
            this.move = move;
        }
    }

    public static int n, d;
    public static char[][] map;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        map = new char[n][n];

        int startX = 0, startY = 0;

        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            for(int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'S') {
                    startX = i;
                    startY = j;
                }
            }
        }

        System.out.println(bfs(new Pos(startX, startY, h, 0, 0)));
    }

    public static int bfs(Pos pos) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(pos);
        int[][] visited = new int[n][n];
        visited[pos.x][pos.y] = pos.health;

        while(!q.isEmpty()) {
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int health = p.health;
                int defence = p.defence;

                if(!isRange(nx, ny)) continue;

                if(map[nx][ny] == 'E') {
                    return p.move+1;
                }

                if(map[nx][ny] == 'U') {
                    defence = d;
                }

                if(defence != 0) {
                    defence--;
                } else {
                    health--;
                }

                if(health == 0) continue;

                if(visited[nx][ny] < health + defence) {
                    visited[nx][ny] = health + defence;
                    q.offer(new Pos(nx, ny, health, defence, p.move+1));
                }
            }
        }

        return -1;
    }

    public static boolean isRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

}