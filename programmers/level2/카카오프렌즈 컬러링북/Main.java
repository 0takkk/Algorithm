import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(6, 4, new int[][] {{1,1,1,0}, {1,2,2,0}, {1,0,0,1}, {0,0,0,1}, {0,0,0,3}, {0,0,0,3}}));
    }

    public static int M, N;
    public static boolean[][] visited;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static int[] solution(int m, int n, int[][] picture) {
        M = m;
        N = n;
        int count = 0;
        int max = 0;

        visited = new boolean[m][n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(picture[i][j] != 0 && !visited[i][j]){
                    count++;
                    max = Math.max(max, bfs(i, j, picture[i][j], picture));
                }
            }
        }

        return new int[] {count, max};
    }

    public static int bfs(int x, int y, int color, int[][] picture){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;
        int cnt = 0;

        while(!q.isEmpty()){
            int[] pos = q.poll();
            cnt++;

            for(int i = 0; i < 4; i++){
                int nx = pos[0] + dx[i];
                int ny = pos[1] + dy[i];

                if(!isRange(nx, ny)) continue;

                if(!visited[nx][ny] && picture[nx][ny] == color){
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        return cnt;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < M && y >= 0 && y < N;
    }

}
