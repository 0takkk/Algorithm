package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static class Point{
        int x, y, time;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static int n, w, h;
    public static char[][] map;
    public static Queue<Point> fires;
    public static Point person;
    public static boolean[][] visited;
    public static boolean isPossible = false;
    public static int minTime;

    public static int[] dx = new int[]{1, -1, 0, 0};
    public static int[] dy = new int[]{0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            map = new char[h][w];
            fires = new LinkedList<>();
            visited = new boolean[h][w];
            isPossible = false;
            minTime = Integer.MAX_VALUE;

            for(int j = 0; j < h; j++){
                String str = br.readLine();
                for(int k = 0; k < w; k++){
                    map[j][k] = str.charAt(k);

                    if(map[j][k] == '*'){
                        fires.offer(new Point(j, k));
                    }
                    else if(map[j][k] == '@'){
                        person = new Point(j, k, 0);
                    }
                }
            }

            bfs();
            if(isPossible)
                System.out.println(minTime + 1);
            else
                System.out.println("IMPOSSIBLE");
        }


    }

    public static void bfs(){
        Queue<Point> q = new LinkedList<>();
        visited[person.x][person.y] = true;
        q.offer(person);

        while(!q.isEmpty()){
            int size = fires.size();
            for(int i = 0; i < size; i++){
                Point fire = fires.poll();

                for(int j = 0; j < 4; j++){
                    int nfx = fire.x + dx[j];
                    int nfy = fire.y + dy[j];

                    //if(nfx < 0 || nfx >= w || nfy < 0 || nfy >= h) continue;
                    if(nfx < 0 || nfx >= h || nfy < 0 || nfy >= w) continue;
                    if(map[nfx][nfy] == '#' || map[nfx][nfy] == '*') continue;

                    map[nfx][nfy] = '*';
                    fires.offer(new Point(nfx, nfy));
                }
            }

            size = q.size();
            for(int i = 0; i < size; i++){
                Point p = q.poll();
                int x = p.x;
                int y = p.y;
                int time = p.time;

                //if(x == 0 || x == w-1 || y == 0 || y == h-1){
                if(x == 0 || x == h-1 || y == 0 || y == w-1){
                    isPossible = true;
                    minTime = Math.min(minTime, time);
                    continue;
                }

                for(int j = 0; j < 4; j++){
                    int npx = x + dx[j];
                    int npy = y + dy[j];

                    //if(npx < 0 || npx >= w || npy < 0 || npy >= h) continue;
                    if(npx < 0 || npx >= h || npy < 0 || npy >= w) continue;
                    if(map[npx][npy] == '#' || map[npx][npy] == '*') continue;

                    if(!visited[npx][npy]){
                        q.offer(new Point(npx, npy, time+1));
                        visited[npx][npy] = true;
                    }
                }
            }

        }
    }

}
