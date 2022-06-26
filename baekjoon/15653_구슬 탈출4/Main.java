import java.io.*;
import java.util.*;

public class Main {

    public static class Pos{
        int rx, ry;
        int bx, by;
        int move;

        public Pos(int rx, int ry, int bx, int by, int move) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.move = move;
        }
    }

    public static int n, m;
    public static char[][] map;
    public static Pos red, blue;
    public static int holeX, holeY;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < m; j++){
                map[i][j] = str.charAt(j);

                if(map[i][j] == 'B') blue = new Pos(0, 0, i, j, 0);
                else if(map[i][j] == 'R') red = new Pos(i, j, 0, 0, 0);
                else if(map[i][j] == 'O') {
                    holeX = i;
                    holeY = j;
                }
            }
        }

        System.out.println(bfs());
    }

    public static int bfs(){
        boolean[][][][] visited = new boolean[n][m][n][m];
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(red.rx, red.ry, blue.bx, blue.by, 0));
        visited[red.rx][red.ry][blue.bx][blue.by] = true;

        while(!q.isEmpty()){
            Pos p = q.poll();
            int rx = p.rx;
            int ry = p.ry;
            int bx = p.bx;
            int by = p.by;
            int move = p.move;

            for(int i = 0; i < 4; i++){
                int nrx = rx;
                int nry = ry;
                int nbx = bx;
                int nby = by;

                boolean isRedHole = false, isBlueHole = false;
                while(map[nrx + dx[i]][nry + dy[i]] != '#'){
                    nrx += dx[i];
                    nry += dy[i];

                    if(nrx == holeX && nry == holeY){
                        isRedHole = true;
                        break;
                    }
                }

                while(map[nbx + dx[i]][nby + dy[i]] != '#'){
                    nbx += dx[i];
                    nby += dy[i];

                    if(nbx == holeX && nby == holeY){
                        isBlueHole = true;
                        break;
                    }
                }

                if(isBlueHole) continue;
                if(isRedHole) return move+1;

                if(nrx == nbx && nry == nby){
                    if(i == 0){
                        if(ry < by) nry -= dy[i];
                        else nby -= dy[i];
                    }
                    else if(i == 1){
                        if(ry < by) nby -= dy[i];
                        else nry -= dy[i];
                    }
                    else if(i == 2){
                        if(rx < bx) nrx -= dx[i];
                        else nbx -= dx[i];
                    }
                    else{
                        if(rx < bx) nbx -= dx[i];
                        else nrx -= dx[i];
                    }
                }

                if(!visited[nrx][nry][nbx][nby]){
                    visited[nrx][nry][nbx][nby] = true;
                    q.offer(new Pos(nrx, nry, nbx, nby, move+1));
                }

            }
        }

        return -1;
    }

}
