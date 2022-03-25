package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int r, c, minTime;
    public static int[][] map;
    public static Pos jihun;
    public static Queue<Pos> fires = new LinkedList<>();
    public static boolean[][] visited;
    public static boolean flag;

    public static int dx[] = {0, 0, 1, -1}, dy[] = {1, -1, 0, 0};

    public static class Pos{
        int x, y, time;

        public Pos(int x, int y, int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }

        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        minTime = Integer.MAX_VALUE;
        map = new int[r][c];
        visited = new boolean[r][c];

        for(int i = 0; i < r; i++){
            char[] ch = br.readLine().toCharArray();

            for(int j = 0; j < c; j++){
                if(ch[j] == '#') map[i][j] = 1;
                else if(ch[j] == 'J') jihun = new Pos(i, j, 0);
                else if(ch[j] == 'F') {
                    fires.offer(new Pos(i, j));
                    map[i][j] = 1;
                }
            }
        }

        bfs();
        if(flag) System.out.println(minTime);
        else System.out.println("IMPOSSIBLE");
    }

    public static void bfs(){
        Queue<Pos> q = new LinkedList<>();
        q.offer(jihun);
        visited[jihun.x][jihun.y] = true;

        while(!q.isEmpty()){

            int size = fires.size();
            for(int k = 0; k < size; k++){
                Pos fire = fires.poll();
                int x = fire.x;
                int y = fire.y;

                for(int i = 0; i < 4; i++){
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if(nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                    if(map[nx][ny] == 1) continue;

                    map[nx][ny] = 1;
                    fires.offer(new Pos(nx, ny));
                }
            }

            size = q.size();
            for(int k = 0; k < size; k++){
                Pos p = q.poll();
                int x = p.x;
                int y = p.y;
                int time = p.time;

                if(x == 0 || x == r-1 || y == 0 || y == c-1){
                    flag = true;
                    minTime = Math.min(minTime, time+1);
                    continue;
                }

                for(int i = 0; i < 4; i++){
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if(nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                    if(map[nx][ny] == 1) continue;

                    if(!visited[nx][ny]) {
                        q.offer(new Pos(nx, ny, time + 1));
                        visited[nx][ny] = true;
                    }
                }
            }
        }

    }

}
