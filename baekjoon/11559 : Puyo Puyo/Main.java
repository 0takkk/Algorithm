package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int r = 12, c = 6, count;
    public static char[][] field;
    public static boolean[][] visited;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int result = 0;

        field = new char[r][c];
        for(int i = 0; i < r; i++) field[i] = br.readLine().toCharArray();

        while(true){
            visited = new boolean[r][c];
            boolean flag = false;

            for(int i = 0; i < r; i++){
                for(int j = 0; j < c; j++){
                    if(!visited[i][j] && field[i][j] != '.'){
                        count = 1;

                        if(check(i, j, field[i][j])){
                            destroy(i, j, field[i][j]);
                            flag = true;
                        }
                    }
                }
            }

            if(flag) result++;
            else break;

            drop();

        }

        System.out.println(result);
    }

    public static void drop(){
        for(int i = 0; i < c; i++){
            for(int j = r-2; j >= 0; j--){
                if(field[j][i] != '.' && field[j+1][i] == '.'){
                    int k = j+1;

                    while(k < r){
                        if(field[k][i] != '.') break;
                        k++;
                    }

                    field[k-1][i] = field[j][i];
                    field[j][i] = '.';
                }
            }
        }
    }

    public static void destroy(int x, int y, char color){
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(x, y));

        while(!q.isEmpty()){
            Pos p = q.poll();

            for(int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;

                if(visited[nx][ny] && field[nx][ny] == color){
                    field[nx][ny] = '.';
                    q.offer(new Pos(nx, ny));
                }
            }
        }
    }

    public static boolean check(int x, int y, char color){
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(x, y));
        visited[x][y] = true;

        while(!q.isEmpty()){
            Pos p = q.poll();

            for(int i = 0; i < 4; i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx < 0 || nx >= r || ny < 0 || ny >= c) continue;

                if(!visited[nx][ny] && field[nx][ny] == color){
                    count++;
                    visited[nx][ny] = true;
                    q.offer(new Pos(nx, ny));
                }
            }
        }

        if(count >= 4) return true;
        else return false;
    }

}
