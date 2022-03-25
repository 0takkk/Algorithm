package com.company;
import java.io.*;
import java.util.*;

public class Main {
    public static int r, c;
    public static char[][] map;
    public static Point myArduino;
    public static Queue<Point> crazyArduinos = new LinkedList<>();
    public static int count = 0;
    public static boolean isLoose = false;

    public static int[] dx = new int[]{0,  1, 1, 1,  0, 0, 0, -1, -1, -1};
    public static int[] dy = new int[]{0, -1, 0, 1, -1, 0, 1, -1,  0,  1};

    public static class Point{
        int x, y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        for(int i = 0 ; i < r; i++){
            String str = br.readLine();
            for(int j = 0; j < c; j++){
                map[i][j] = str.charAt(j);

                if(map[i][j] == 'I')
                    myArduino = new Point(i,j);

                else if(map[i][j] == 'R')
                    crazyArduinos.offer(new Point(i,j));
            }
        }

        String tmp = br.readLine();
        int[] moveOrder = new int[tmp.length()];
        for(int i = 0; i < moveOrder.length; i++)
            moveOrder[i] = Character.getNumericValue(tmp.charAt(i));

        for(int i = 0; i < moveOrder.length; i++){
            if(!isLoose)
                move(moveOrder[i]);
        }


        if(isLoose){
            System.out.println("kraj " + count);
        }
        else{
            printMap();
        }

    }

    public static void makeMap(){
        map = new char[r][c];

        for(int i = 0 ; i < r; i++)
            Arrays.fill(map[i], '.');

        map[myArduino.x][myArduino.y] = 'I';

        for(int i = 0; i < crazyArduinos.size(); i++){
            Point p = crazyArduinos.poll();
            map[p.x][p.y] = 'R';
            crazyArduinos.offer(p);
        }
    }

    public static void printMap(){
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    public static void move(int m){
        int[][] tmp = new int[r][c];

        int newX = myArduino.x + dx[m];
        int newY = myArduino.y + dy[m];

        count++;

        if(map[newX][newY] == 'R'){
            isLoose = true;
            return;
        }
        else{
            myArduino.x = newX;
            myArduino.y = newY;
        }

        int size = crazyArduinos.size();

        for(int i = 0; i < size; i++){
            Point crazy = crazyArduinos.poll();

            int cx = crazy.x;
            int cy = crazy.y;

            int min = Integer.MAX_VALUE;
            int minX = 0;
            int minY = 0;

            for(int j = 1; j <= 9; j++){
                if(j == 5) continue;

                int nx = cx + dx[j];
                int ny = cy + dy[j];

                if(nx < 0 || nx >= r || ny < 0 || ny >= c) continue;

                int distance = Math.abs(myArduino.x - nx) + Math.abs(myArduino.y - ny);

                if(min > distance){
                    min = distance;
                    minX = nx;
                    minY = ny;
                }
            }

            if(minX == myArduino.x && minY == myArduino.y){
                isLoose = true;
                return;
            }

            tmp[minX][minY] += 1;
        }

        for(int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (tmp[i][j] == 1)
                    crazyArduinos.offer(new Point(i, j));
            }
        }

        makeMap();
    }

}
