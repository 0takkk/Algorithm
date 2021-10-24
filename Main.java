package com.company;
import java.io.*;
import java.util.*;

public class Main {
    public static class pos{
        int x;
        int y;
        boolean visited;

        public pos(int x, int y){
            this.x = x;
            this.y = y;
        }

        public pos(int x, int y, boolean visited){
            this.x = x;
            this.y = y;
            this.visited = visited;
        }
    }

    static int n, m, g, r;
    static int[][] garden;
    static int max;
    static List<pos> possible, red, green;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static final int RED = 4;
    static final int GREEN = 5;
    static final int FLOWER = 6;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        garden = new int[n][m];
        possible = new ArrayList<>();
        red = new ArrayList<>();
        green = new ArrayList<>();

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < m; j++){
                garden[i][j] = Integer.parseInt(st.nextToken());
                if(garden[i][j] == 2){
                    possible.add(new pos(i, j, false));
                }
            }
        }

        sow(0, RED, 0);

        System.out.println(max);
    }

    public static void sow(int cnt, int color, int idx){
        if(color == RED && cnt == r){   // RED를 다 뿌렸으면
            sow(0, GREEN, 0);   // GREEN 뿌리기
            return;
        }
        if(color == GREEN && cnt == g){  // GREEN까지 다 뿌렸으면
            spread();                       // 퍼트리기
            return;
        }

        for(int i = idx; i < possible.size(); i++){
            pos p = possible.get(i);
            if(p.visited == false){
                p.visited = true;
                garden[p.x][p.y] = color;
                if(color == RED)
                    red.add(new pos(p.x, p.y));
                if(color == GREEN)
                    green.add(new pos(p.x, p.y));

                sow(cnt+1, color, i+1);

                p.visited = false;
                if(color == RED)
                    red.remove(red.size()-1);
                if(color == GREEN)
                    green.remove(green.size()-1);
                garden[p.x][p.y] = 2;
            }
        }
    }

    public static void spread(){
        int[][] copy = new int[n][m];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                copy[i][j] = garden[i][j];

        Deque<pos> redQueue = new ArrayDeque<>();
        Deque<pos> greenQueue = new ArrayDeque<>();

        for(pos p : red)
            redQueue.offer(p);
        for(pos p : green)
            greenQueue.offer(p);

        int[][] times = new int[n][m];
        int flowerCnt = 0;
        int time = 1;

        while(true){
            if(redQueue.isEmpty() && greenQueue.isEmpty()){
                if(flowerCnt > max)
                    max = flowerCnt;
                break;
            }

            int redSize = redQueue.size();
            int greenSize = greenQueue.size();

            // RED 퍼트리기
            while(redSize-- > 0){
                pos p = redQueue.poll();
                if(copy[p.x][p.y] == FLOWER)
                    continue;

                for(int i = 0; i < 4; i++){
                    int nx = p.x + dx[i];
                    int ny = p.y + dy[i];

                    if(nx < 0 || ny < 0 || nx >= n || ny >= m || times[nx][ny] > 0)
                        continue;
                    if(copy[nx][ny] == 1 || copy[nx][ny] == 2) { // 퍼트릴 수 있으면
                        times[nx][ny] = time;
                        copy[nx][ny] = RED;
                        redQueue.offer(new pos(nx, ny));
                    }
                }
            }

            // GREEN 퍼트리기
            while(greenSize-- > 0){
                pos p = greenQueue.poll();
                if(copy[p.x][p.y] == FLOWER)
                    continue;

                for(int i = 0; i < 4; i++){
                    int nx = p.x + dx[i];
                    int ny = p.y + dy[i];

                    if(nx < 0 || ny < 0 || nx >= n || ny >= m)
                        continue;
                    if(copy[nx][ny] == 1 || copy[nx][ny] == 2){ // 퍼트릴 수 있으면
                        copy[nx][ny] = GREEN;
                        greenQueue.offer(new pos(nx, ny));
                    }
                    if(copy[nx][ny] == RED && times[nx][ny] == time){  // RED이고 같은 time이면
                        flowerCnt++;
                        copy[nx][ny] = FLOWER;
                    }
                }
            }

            time++;
        }
    }

}
