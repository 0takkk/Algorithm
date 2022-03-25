package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int n, m, k, t;
    public static Queue<Pos> molds;
    public static Pos[] floors;
    public static boolean[][][] visited;

    public static int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
    public static int[] dy = {-2, -1, 1, 2, -2, -1, 1, 2};

    public static class Pos{
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        molds = new LinkedList<>();
        floors = new Pos[k];
        visited = new boolean[2][n+1][n+1];

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            molds.offer(new Pos(x, y));
        }

        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            floors[i] = new Pos(x, y);
        }

        bfs();

        for (Pos floor : floors) {
            int x = floor.x;
            int y = floor.y;

            if(visited[t % 2][x][y]){
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");
    }

    public static void bfs(){
        int day = 0;

        while(!molds.isEmpty() && day++ < t) {

            int size = molds.size();
            for(int i = 0; i < size; i++){
                Pos p = molds.poll();
                int x = p.x;
                int y = p.y;

                for(int j = 0; j < 8; j++){
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    if(nx < 1 || nx > n || ny < 1 || ny > n) continue;

                    if(!visited[day % 2][nx][ny]){
                        visited[day % 2][nx][ny] = true;
                        molds.offer(new Pos(nx, ny));
                    }
                }
            }
        }

    }

}
