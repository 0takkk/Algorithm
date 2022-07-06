import java.io.*;
import java.util.*;

public class Main {

    public static class Pos{
        int x, y;
        int move;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pos(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }

    public static int n;
    public static int[][] map;
    public static Pos start, end;

    public static int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
    public static int[] dy = {-2, -1, 1, 2, -2, -1, 1, 2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-->0){
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            start = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            st = new StringTokenizer(br.readLine());
            end = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            sb.append(bfs() + "\n");
        }

        System.out.println(sb.toString());
    }

    public static int bfs(){
        boolean[][] visited = new boolean[n][n];
        Queue<Pos> q = new LinkedList<>();
        q.offer(start);
        visited[start.x][start.y] = true;

        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;
            int move = p.move;

            if(x == end.x && y == end.y){
                return move;
            }

            for(int i = 0; i < 8; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isRange(nx, ny)) continue;

                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    q.offer(new Pos(nx, ny, move+1));
                }
            }
        }

        return -1;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < n;
    }

}
