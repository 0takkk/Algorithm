import java.io.*;
import java.util.*;

public class Solution {

    public static class Cell implements Comparable<Cell>{
        int x, y;
        int initLife, restLife;
        int state;  // 0 : 비활성, 1 : 활성, 2 : 죽음
        int time;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Cell(int x, int y, int initLife, int restLife, int state) {
            this.x = x;
            this.y = y;
            this.initLife = initLife;
            this.restLife = restLife;
            this.state = state;
        }

        public Cell(int x, int y, int initLife, int restLife, int state, int time) {
            this.x = x;
            this.y = y;
            this.initLife = initLife;
            this.restLife = restLife;
            this.state = state;
            this.time = time;
        }

        public Cell goTime(){
            this.restLife--;
            this.time++;
            return this;
        }

        public Cell changeState(int state){
            this.restLife = this.initLife;
            this.state = state;
            this.time++;
            return this;
        }

        @Override
        public int compareTo(Cell o) {
            if(this.time == o.time){
                if(o.initLife == this.initLife) return this.restLife - o.restLife;
                return o.initLife - this.initLife;
            }
            return this.time - o.time;
        }
    }

    public static int k, ans;
    public static boolean[][] visited;
    public static PriorityQueue<Cell> pq;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= t; tc++) {
            ans = 0;
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            visited = new boolean[500][500];
            pq = new PriorityQueue<>();

            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < m; j++){
                    int life = Integer.parseInt(st.nextToken());
                    if(life == 0) continue;

                    int x = i+200;
                    int y = j+200;

                    visited[x][y] = true;
                    pq.offer(new Cell(x, y, life, life, 0));
                    ans++;
                }
            }

            bfs();
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void bfs(){
        while(!pq.isEmpty()) {
            Cell cell = pq.poll();
            int x = cell.x;
            int y = cell.y;
            int initLife = cell.initLife;
            int restLife = cell.restLife;
            int state = cell.state;
            int time = cell.time;
            
            if(time == k) return;

            // 비활성 세포
            if (state == 0) {
                if (restLife > 1) pq.offer(cell.goTime());
                else pq.offer(cell.changeState(1));
            }
            // 활성 세포
            else if (state == 1) {
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (!visited[nx][ny]) {
                        visited[nx][ny] = true;
                        pq.offer(new Cell(nx, ny, initLife, initLife, 0, time+1));
                        ans++;
                    }
                }

                if (restLife > 1) pq.offer(cell.goTime());
                else {
                    cell.changeState(2);
                    ans--;
                }
            }
        }
    }

}
