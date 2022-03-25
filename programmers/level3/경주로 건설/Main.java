import java.util.*;

public class Main {
    public static void main(String[] args) {
//        System.out.println(solution(new int[][] {{0,0,0},{0,0,0},{0,0,0}}));
        System.out.println(solution(new int[][] {{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},
                {0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}}));
    }

    public static class Node{
        int x, y, cost, dir;

        public Node(int x, int y, int cost, int dir) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.dir = dir;
        }
    }

    public static int n, min = Integer.MAX_VALUE;
    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static int solution(int[][] board) {
        int answer = 0;

        n = board.length;
        bfs(board);
        answer = min;

        return answer;
    }

    public static void bfs(int[][] board){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 0, -1));
        boolean[][][] visited = new boolean[4][n][n];
        visited[0][0][0] = visited[1][0][0] = visited[2][0][0] = visited[3][0][0] = true;

        while(!q.isEmpty()){
            Node now = q.poll();

            if(now.x == n-1 && now.y == n-1){
                min = Math.min(min, now.cost);
            }

            for(int i = 0; i < 4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if(board[nx][ny] == 1) continue;

                int newCost = 0;
                if(now.dir == -1 || now.dir == i){
                    newCost = now.cost + 100;
                }else{
                    newCost = now.cost + 600;
                }

                if(!visited[i][nx][ny] || board[nx][ny] >= newCost){
                    board[nx][ny] = newCost;
                    visited[i][nx][ny] = true;
                    q.offer(new Node(nx, ny, newCost, i));
                }
            }
        }
    }

}
