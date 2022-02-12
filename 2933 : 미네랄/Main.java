package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int r, c;
    public static char[][] map;
    public static boolean[][] visited;

    public static int[] dx = {1, 0, 0, -1};
    public static int[] dy = {0, -1, 1, 0};

    public static class Pos implements Comparable<Pos>{
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Pos p){
            return p.x - x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];

        for(int i = 0; i < r; i++){
            map[i] = br.readLine().toCharArray();
        }

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int dir = 0;

        while(n-->0){
            int h = r - Integer.parseInt(st.nextToken());

            if(dir % 2 == 0) dir = 0;
            else dir = 1;

            shoot(h, dir);
            find();

            dir++;
        }

        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }

    }

    public static void shoot(int h, int dir){
        if(dir == 0){
            for(int i = 0; i < c; i++){
                if(map[h][i] == 'x'){
                    map[h][i] = '.';
                    break;
                }
            }
        }
        else{
            for(int i = c-1; i >= 0; i--){
                if(map[h][i] == 'x'){
                    map[h][i] = '.';
                    break;
                }
            }
        }
    }

    public static void find(){
        visited = new boolean[r][c];
        Queue<Pos> q = new LinkedList<>();

        // 땅에 붙어있는 클러스터 체크
        for(int i = 0; i < c; i++){
            if(map[r-1][i] == '.' || visited[r-1][i]) continue;

            visited[r-1][i] = true;
            q.add(new Pos(r-1, i));

            while(!q.isEmpty()){
                Pos p = q.poll();
                int x = p.x;
                int y = p.y;

                for(int j = 0; j < 4; j++){
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    if(nx < 0 || nx >= r || ny < 0 || ny >= c) continue;

                    if(!visited[nx][ny] && map[nx][ny] == 'x'){
                        visited[nx][ny] = true;
                        q.offer(new Pos(nx, ny));
                    }
                }
            }
        }

        // 공중에 떠 있는 클러스터 찾기
        ArrayList<Pos> arr = new ArrayList<>();
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(!visited[i][j] && map[i][j] == 'x'){
                    map[i][j] = '.';
                    arr.add(new Pos(i, j));
                }
            }
        }

        if(arr.isEmpty()) return;

        move(arr);
    }

    public static void move(ArrayList<Pos> arr){
        boolean flag = true;

        while(flag){
            for (Pos p : arr) {
                int x = p.x + 1;
                int y = p.y;

                if((x < 0 || x >= r || y < 0 || y >= c) || map[x][y] == 'x'){
                    flag = false;
                    break;
                }
            }

            if(flag){
                for (Pos p : arr) {
                    p.x++;
                }
            }
        }

        for (Pos p : arr) {
            map[p.x][p.y] = 'x';
        }
    }

}
