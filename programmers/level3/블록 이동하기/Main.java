import java.util.*;

public class Main {
    public static void main(String[] args) {
//        System.out.println(solution(new int[][] {{0, 0, 0, 1, 1},{0, 0, 0, 1, 0},{0, 1, 0, 1, 1},{1, 1, 0, 0, 1},{0, 0, 0, 0, 0}}));
        System.out.println(solution(new int[][] {
                {0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 1, 1},
                {0, 0, 1, 0, 0, 0, 0}}));
    }

    public static class Drone{
        int x1, y1, x2, y2;
        int state;  // 0 : 가로, 1 : 세로
        int time;

        public Drone(int x1, int y1, int x2, int y2, int state, int time) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.state = state;
            this.time = time;
        }
    }

    public static int n, answer;
    public static boolean[][][] visited;
    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static int[][] rowToCol1 = {{0, 0}, {-1, 0}, {-1, 1}, {0, 1}};
    public static int[][] rowToCol2 = {{1, -1}, {0, -1}, {0, 0}, {1, 0}};
    public static int[][] rowCheck = {{1, 1}, {-1, 1}, {-1, 0}, {1, 0}};

    public static int[][] colToRow1 = {{0, 0}, {0, -1}, {1, -1}, {1, 0}};
    public static int[][] colToRow2 = {{-1, 1}, {-1, 0}, {0, 0}, {0, 1}};
    public static int[][] colCheck = {{1, 1}, {1, -1}, {0, -1}, {0, 1}};

    public static int solution(int[][] board) {
        answer = 0;

        n = board.length;
        visited = new boolean[2][n][n];

        bfs(board);

        return answer;
    }

    public static void bfs(int[][] board){
        Queue<Drone> q = new LinkedList<>();
        q.offer(new Drone(0, 0, 0, 1, 0, 0));

        while(!q.isEmpty()){
            Drone drone = q.poll();
            int x1 = drone.x1;
            int y1 = drone.y1;
            int x2 = drone.x2;
            int y2 = drone.y2;
            int state = drone.state;

            if((x1 == n-1 && y1 == n-1) || (x2 == n-1 && y2 == n-1)){
                answer = drone.time;
                return;
            }

            if(visited[state][x1][y1]) continue;
            visited[state][x1][y1] = true;

            for(int i = 0; i < 4; i++){
                int nx1 = x1 + dx[i];
                int ny1 = y1 + dy[i];

                int nx2 = x2 + dx[i];
                int ny2 = y2 + dy[i];

                if(!isRange(nx1, ny1, nx2, ny2)) continue;
                if(isWell(board, nx1, ny1, nx2, ny2)) continue;

                if(!visited[state][nx1][ny1]){
                    q.offer(new Drone(nx1, ny1, nx2, ny2, state, drone.time+1));
                }
            }

            if(state == 0){
                for(int i = 0; i < 4; i++){
                    int nx1 = x1 + rowToCol1[i][0];
                    int ny1 = y1 + rowToCol1[i][1];

                    int nx2 = x2 + rowToCol2[i][0];
                    int ny2 = y2 + rowToCol2[i][1];

                    if(!isRange(nx1, ny1, nx2, ny2)) continue;
                    if(isWell(board, nx1, ny1, nx2, ny2)) continue;

                    if(board[x1 + rowCheck[i][0]][y1 + rowCheck[i][1]] == 1) continue;

                    if(!visited[1][nx1][ny1]){
                        q.offer(new Drone(nx1, ny1, nx2, ny2, 1, drone.time+1));
                    }
                }
            }else{
                for(int i = 0; i < 4; i++){
                    int nx1 = x1 + colToRow1[i][0];
                    int ny1 = y1 + colToRow1[i][1];

                    int nx2 = x2 + colToRow2[i][0];
                    int ny2 = y2 + colToRow2[i][1];

                    if(!isRange(nx1, ny1, nx2, ny2)) continue;
                    if(isWell(board, nx1, ny1, nx2, ny2)) continue;

                    if(board[x1 + colCheck[i][0]][y1 + colCheck[i][1]] == 1) continue;

                    if(!visited[0][nx1][ny1]){
                        q.offer(new Drone(nx1, ny1, nx2, ny2, 0, drone.time+1));
                    }
                }
            }
        }
    }

    public static boolean isRange(int x1, int y1, int x2, int y2){
        if(x1 < 0 || x1 >= n || y1 < 0 || y1 >= n) return false;
        if(x2 < 0 || x2 >= n || y2 < 0 || y2 >= n) return false;

        return true;
    }

    public static boolean isWell(int[][] board, int x1, int y1, int x2, int y2){
        if(board[x1][y1] == 1 || board[x2][y2] == 1) return true;
        return false;
    }

}
