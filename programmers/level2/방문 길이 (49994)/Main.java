package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution("ULURRDLLU"));
        System.out.println(solution("LULLLLLLU"));
    }

    public static final int SIZE = 11;
    public static int answer;
    public static boolean[][][][] visited;
    public static Pos p;

    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static class Pos{
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int solution(String dirs) {
        answer = 0;

        visited = new boolean[SIZE][SIZE][SIZE][SIZE];
        p = new Pos(5, 5);

        for(int i = 0; i < dirs.length(); i++){
            move(dirs.charAt(i));
        }

        return answer;
    }

    public static void move(char dir){
        int d = getDir(dir);

        int nx = p.x + dx[d];
        int ny = p.y + dy[d];

        if(nx < 0 || nx >= SIZE || ny < 0 || ny >= SIZE) return;

        if(!visited[nx][ny][p.x][p.y]) answer++;
        visited[nx][ny][p.x][p.y] = true;
        visited[p.x][p.y][nx][ny] = true;

        p.x = nx;
        p.y = ny;
    }

    public static int getDir(char dir){
        if(dir == 'U') return 0;
        else if(dir == 'R') return 1;
        else if(dir == 'D') return 2;
        else return 3;
    }

}
