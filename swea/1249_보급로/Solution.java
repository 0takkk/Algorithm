import java.io.*;
import java.util.*;

public class Solution {

    public static class Pos implements Comparable<Pos>{
        int x, y, cost;

        public Pos(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pos o) {
            return this.cost - o.cost;
        }
    }

    public static int n;
    public static int[][] map;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine());

            map = new int[n][n];
            for(int i = 0; i < n; i++){
                String str = br.readLine();
                for(int j = 0; j < n; j++){
                    map[i][j] = str.charAt(j)-'0';
                }
            }

            int ans = bfs();

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static int bfs(){
        boolean[][] visited = new boolean[n][n];
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(0, 0, 0));
        visited[0][0] = true;

        while(!pq.isEmpty()){
            Pos p = pq.poll();
            int x = p.x;
            int y = p.y;
            int cost = p.cost;

            if(x == n-1 && y == n-1) return cost;

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isRange(nx, ny)) continue;

                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    pq.offer(new Pos(nx, ny, cost + map[nx][ny]));
                }
            }
        }

        return -1;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < n;
    }

}
