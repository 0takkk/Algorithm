package com.company;
import java.io.*;
import java.util.*;

public class Main {
    public static int t;
    public static int[][] map;
    public static boolean[] visited;
    public static boolean isFinish;
    public static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());

        while(t-- > 0){
            map = new int[3][3];
            visited = new boolean[512];
            isFinish = false;
            count = 0;

            for(int i = 0; i < 3; i++){
                st = new StringTokenizer(br.readLine(), " ");

                for(int j = 0; j < 3; j++) {
                    if (st.nextToken().equals("H")) map[i][j] = 1;
                    else map[i][j] = 0;
                }
            }

            bfs();

            if(!isFinish) System.out.println(-1);
            else System.out.println(count);
        }

    }

    public static void bfs(){
        Queue<Integer> q = new LinkedList<>();
        int start = mapToInt();
        q.offer(start);
        visited[start] = true;

        while(!q.isEmpty()){
            Queue<Integer> tmp = new LinkedList<>();
            while(!q.isEmpty()){
                tmp.offer(q.poll());
            }

            while(!tmp.isEmpty()){
                int now = tmp.poll();

                if(now == 0 || now == 511){
                    isFinish = true;
                    break;
                }

                intToMap(now);

                for(int i = 0; i < 3; i++){
                    changeRow(i);
                    int next = mapToInt();
                    if(!visited[next]){
                        visited[next] = true;
                        q.offer(next);
                    }
                    changeRow(i);
                }

                for(int i = 0; i < 3; i ++){
                    changeCol(i);
                    int next = mapToInt();
                    if(!visited[next]){
                        visited[next] = true;
                        q.offer(next);
                    }
                    changeCol(i);
                }

                for(int i = 0; i < 2; i++){
                    changeCross(i);
                    int next = mapToInt();
                    if(!visited[next]){
                        visited[next] = true;
                        q.offer(next);
                    }
                    changeCross(i);
                }
            }

            if(isFinish) break;
            count++;
        }
    }

    public static void changeRow(int r){
        for(int i = 0; i < 3; i++){
            map[r][i] = map[r][i] == 1 ? 0 : 1;
        }
    }

    public static void changeCol(int c){
        for(int i = 0; i < 3; i++){
            map[i][c] = map[i][c] == 1 ? 0 : 1;
        }
    }

    public static void changeCross(int dir){
        for(int i = 0; i < 3; i++){
            if(dir == 0)
                map[i][i] = map[i][i] == 1 ? 0 : 1;
            else
                map[i][2-i] = map[i][2-i] == 1 ? 0 : 1;
        }
    }

    public static int mapToInt(){
        int num = 0;
        int idx = 0;

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(map[i][j] == 1){
                    num += Math.pow(2, idx);
                }
                idx++;
            }
        }

        return num;
    }

    public static void intToMap(int num){
        for(int i = 2; i >= 0; i--){
            for(int j = 2; j >= 0; j--){
                map[i][j] = num % 2;
                num /= 2;
            }
        }
    }


}
