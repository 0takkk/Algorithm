import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{1,0,0,3}, {2,0,0,0}, {0,0,0,2}, {3,0,1,0}}, 1, 0));
    }

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

    public static int[] cardNum;
    public static int cardCnt;
    public static ArrayList<String> orders;
    public static int[][] copy;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static Pos cursor;

    public static int solution(int[][] board, int r, int c) {
        cardNum = new int[7];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                int num = board[i][j];

                if(num == 0) continue;

                if(++cardNum[num] == 2) cardCnt++;
            }
        }

        orders = new ArrayList<>();
        makeOrders("", 0);

        int min = Integer.MAX_VALUE;
        for (String order : orders) {
            int move = 0;
            cursor = new Pos(r, c);
            copy = new int[4][4];

            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    copy[i][j] = board[i][j];
                }
            }

            for(int i = 0; i < order.length(); i++){
                int target = order.charAt(i) - '0';

                move += search(cursor, target);
                copy[cursor.x][cursor.y] = 0;
                move += 1;

                move += search(cursor, target);
                copy[cursor.x][cursor.y] = 0;
                move += 1;
            }

            min = Math.min(min, move);
        }

        return min;
    }

    public static void makeOrders(String str, int cnt){
        if(cnt == cardCnt){
            orders.add(str);
            return;
        }

        for(int i = 1; i < 7; i++){
            if(cardNum[i] == 2){
                if(!str.contains(i+"")){
                    makeOrders(str+i, cnt+1);
                }
            }
        }
    }

    public static int search(Pos cursor, int target){
        boolean[][] visited = new boolean[4][4];
        Queue<Pos> q = new LinkedList<>();
        q.offer(cursor);
        visited[cursor.x][cursor.y] = true;

        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;
            int move = p.move;

            if(copy[x][y] == target){
                cursor.x = x;
                cursor.y = y;
                return move;
            }

            // 한칸 움직임
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isRange(nx, ny)) continue;

                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    q.offer(new Pos(nx, ny, move+1));
                }
            }

            // ctrl
            for(int i = 0; i < 4; i++){
                Pos np = check(x, y, i);
                int nx = np.x;
                int ny = np.y;

                if(nx == x && ny == y) continue;

                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    q.offer(new Pos(nx, ny, move+1));
                }
            }
        }

        return 0;
    }

    // ctrl 이동시 카드 유무 확인
    public static Pos check(int x, int y, int dir){
        x += dx[dir];
        y += dy[dir];

        while(isRange(x, y)){
            if(copy[x][y] != 0) return new Pos(x, y);

            x += dx[dir];
            y += dy[dir];
        }

        return new Pos(x - dx[dir], y - dy[dir]);
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < 4 && y >= 0 && y < 4;
    }

}
