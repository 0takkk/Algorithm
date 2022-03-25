package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static class Ball{
        int rx, ry;
        int bx, by;
        int count;

        public Ball(int rx, int ry, int bx, int by, int count){
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.count = count;
        }
    }

    public static int n, m;
    public static char[][] board;
    public static boolean[][][][] visited;
    public static int holeX, holeY;
    public static Ball red, blue;

    public static int[] dx = new int[]{1, -1, 0, 0};
    public static int[] dy = new int[]{0, 0, 1, -1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        visited = new boolean[n][m][n][m];

        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < m; j++){
                board[i][j] = str.charAt(j);

                if(board[i][j] == 'O'){
                    holeX = i;
                    holeY = j;
                }
                else if(board[i][j] == 'R')
                    red = new Ball(i, j, 0, 0, 0);
                else if(board[i][j] == 'B')
                    blue = new Ball(0, 0, i, j, 0);
            }
        }

        System.out.println(bfs());

    }

    public static int bfs(){
        Queue<Ball> queue = new LinkedList<>();
        queue.offer(new Ball(red.rx, red.ry, blue.bx, blue.by, 1));
        visited[red.rx][red.ry][blue.bx][blue.by] = true;

        while(!queue.isEmpty()){
            Ball ball = queue.poll();

            int curRx = ball.rx;
            int curRy = ball.ry;
            int curBx = ball.bx;
            int curBy = ball.by;
            int curCount = ball.count;

            if(curCount > 10) return -1;

            for(int i = 0; i < 4; i++){
                int newRx = curRx;
                int newRy = curRy;
                int newBx = curBx;
                int newBy = curBy;

                boolean isRedHole = false;
                boolean isBlueHole = false;

                while(board[newRx + dx[i]][newRy + dy[i]] != '#'){
                    newRx += dx[i];
                    newRy += dy[i];

                    if(newRx == holeX && newRy == holeY){
                        isRedHole = true;
                        break;
                    }
                }

                while(board[newBx + dx[i]][newBy + dy[i]] != '#'){
                    newBx += dx[i];
                    newBy += dy[i];

                    if(newBx == holeX && newBy == holeY){
                        isBlueHole = true;
                        break;
                    }
                }

                if(isBlueHole) continue;  // 큐에 남은 다른 좌표도 확인

                if(isRedHole) return curCount;

                if(newRx == newBx && newRy == newBy){  // 둘 다 구멍에 빠지지 않았는데 같은 위치인 경우
                    if(i == 0) {  // 오른쪽으로 이동했을 때
                        if(curRx > curBx) newBx -= dx[i];
                        else newRx -= dx[i];
                    }
                    else if(i == 1){  // 왼쪽으로 이동했을 때
                        if(curRx > curBx) newRx -= dx[i];
                        else newBx -= dx[i];
                    }
                    else if(i == 2){  // 아래로 이동했을 때
                        if(curRy > curBy) newBy -= dy[i];
                        else newRy -= dy[i];
                    }
                    else if(i == 3){  // 위로 이동했을 때
                        if(curRy > curBy) newRy -= dy[i];
                        else newBy -= dy[i];
                    }
                }

                if(!visited[newRx][newRy][newBx][newBy]){  // 두 ball이 처음 방문한 위치인 경우만 이동
                    visited[newRx][newRy][newBx][newBy] = true;
                    queue.offer(new Ball(newRx, newRy, newBx, newBy, curCount + 1));
                }

            }
        }

        return -1;
    }


}
