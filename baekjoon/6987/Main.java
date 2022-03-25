package com.company;
import java.io.*;
import java.util.*;

public class Main {
    public static int[] team1 = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
    public static int[] team2 = {1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5};
    public static int win[], draw[], lose[];
    public static boolean isPossible;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int t = 0; t < 4; t++){
            st = new StringTokenizer(br.readLine());

            win = new int[6];
            draw = new int[6];
            lose = new int[6];
            isPossible = false;

            int totalWin = 0, totalDraw = 0, totalLose = 0;
            for(int i = 0; i < 6; i++){
                totalWin += win[i] = Integer.parseInt(st.nextToken());
                totalDraw += draw[i] = Integer.parseInt(st.nextToken());
                totalLose += lose[i] = Integer.parseInt(st.nextToken());
            }

            if(totalWin + totalDraw + totalLose != 30 || totalWin != totalLose || (totalDraw % 2) != 0)
                isPossible = false;
            else
                dfs(0);

            if(isPossible) System.out.println(1);
            else System.out.println(0);
        }
    }

    public static void dfs(int idx){
        if(idx == 15){
            isPossible = true;
            return;
        }

        int t1 = team1[idx];
        int t2 = team2[idx];

        // t1이 이기는 경우
        if(win[t1] > 0 && lose[t2] > 0){
            win[t1]--;
            lose[t2]--;
            dfs(idx+1);
            win[t1]++;
            lose[t2]++;
        }
        // t1과 t2가 비기는 경우
        if(draw[t1] > 0 && draw[t2] > 0){
            draw[t1]--;
            draw[t2]--;
            dfs(idx+1);
            draw[t1]++;
            draw[t2]++;
        }
        //t2가 이기는 경우
        if(lose[t1] > 0 && win[t2] > 0){
            lose[t1]--;
            win[t2]--;
            dfs(idx+1);
            lose[t1]++;
            win[t2]++;
        }
    }

}
