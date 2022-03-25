package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(new int[][] {{1,1,0,1,1}, {0,1,0,1,1}, {1,0,0,0,1}, {1,1,0,1,0}}));
    }

    public static int[] dx = {0, 0, 1, -1, 1, -1, -1, 1};
    public static int[] dy = {1, -1, 0, 0, -1, -1, 1, 1};

    public static int r, c;

    public static int[] solution(int[][] images) {
        int[] answer = new int[2];

        r = images.length;
        c = images[0].length;

        int[][] map1 = new int[r][c];
        int[][] map2 = new int[r][c];

        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                map1[i][j] = images[i][j];
                map2[i][j] = images[i][j];
            }
        }

        boolean[][] visited = new boolean[r][c];
        int num1 = 2, num2 = 2;

        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(!visited[i][j] && images[i][j] == 1){
                    dfs1(map1, i, j, visited, num1);
                    num1++;
                }
            }
        }

        visited = new boolean[r][c];
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(!visited[i][j] && images[i][j] == 1){
                    dfs2(map2, i, j, visited, num2);
                    num2++;
                }
            }
        }

        answer[0] = num1-2;
        answer[1] = num2-2;

        return answer;
    }


    public static void dfs1(int[][] map1, int x, int y, boolean[][] visited, int num1){
        visited[x][y] = true;
        map1[x][y] = num1;

        for(int i = 0; i < 4; i ++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
            if(!visited[nx][ny] && map1[nx][ny] == 1){
                dfs1(map1, nx, ny, visited, num1);
            }
        }
    }

    public static void dfs2(int[][] map2, int x, int y, boolean[][] visited, int num2){
        visited[x][y] = true;
        map2[x][y] = num2;

        for(int i = 0; i < 8; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
            if(!visited[nx][ny] && map2[nx][ny] == 1){
                dfs2(map2, nx, ny, visited, num2);
            }
        }
    }

}
