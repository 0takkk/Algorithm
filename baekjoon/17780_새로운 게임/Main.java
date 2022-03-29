import java.io.*;
import java.util.*;

public class Main {

    public static class Pawn{
        int x, y;
        int dir;

        public Pawn(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    public static final int WHITE = 0, RED = 1, BLUE = 2;
    public static int n, k;
    public static int[][] board;
    public static LinkedList<Integer>[][] state;
    public static Pawn[] pawns;

    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {1, -1, 0, 0};
    public static int[] reverse = {1, 0, 3, 2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        state = new LinkedList[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                state[i][j] = new LinkedList<>();
            }
        }

        pawns = new Pawn[k+1];
        for(int i = 1; i <= k; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken()) - 1;

            pawns[i] = new Pawn(x, y, dir);
            state[x][y].add(i);
        }

        System.out.println(game());
    }

    public static int game(){
        int turn = 0;
        while(true){
            turn++;
            if(turn > 1000) return -1;

            for(int i = 1; i <= k; i++){
                Pawn pawn = pawns[i];
                int x = pawn.x;
                int y = pawn.y;
                int dir = pawn.dir;

                // 가장 아래 pawn이 아니면 continue
                if(state[x][y].get(0) != i) continue;

                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(!isRange(nx, ny) || board[nx][ny] == BLUE){
                    pawn.dir = reverse[dir];
                    nx = x + dx[pawn.dir];
                    ny = y + dy[pawn.dir];
                }

                if(!isRange(nx, ny) || board[nx][ny] == BLUE){
                    continue;
                } else if (board[nx][ny] == WHITE){
                    for(int j = 0; j < state[x][y].size(); j++){
                        int idx = state[x][y].get(j);
                        state[nx][ny].add(idx);
                        pawns[idx].x = nx;
                        pawns[idx].y = ny;
                    }
                    state[x][y].clear();
                } else if(board[nx][ny] == RED){
                    for(int j = state[x][y].size()-1; j >= 0; j--){
                        int idx = state[x][y].get(j);
                        state[nx][ny].add(idx);
                        pawns[idx].x = nx;
                        pawns[idx].y = ny;
                    }
                    state[x][y].clear();
                }

                if(state[nx][ny].size() >= 4){
                    return turn;
                }
            }
        }
    }

    public static boolean isRange(int x, int y){
        if(x < 0 || x >= n || y < 0 || y >= n) return false;
        return true;
    }
}
