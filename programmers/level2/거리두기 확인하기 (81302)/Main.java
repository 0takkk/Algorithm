package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String[][] pbl = new String[][] {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};

//        solution(pbl);
        for(int i : solution(pbl))
            System.out.println(i);


    }

    public static int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        int idx = 0;

        for(String[] place : places){
            int checked = 1;
            for(int i = 0; i < place.length; i++){
                for(int j = 0; j < place[0].length(); j++){
                    char c = place[i].charAt(j);

                    if(c == 'P') {
                        checked = check(place, i, j);
                        if (checked == 0) break;
                    }
                }
                if(checked == 0) {
                    answer[idx] = 0;
                    break;
                }
            }
            if(checked == 1) answer[idx] = 1;
            idx++;
        }

        return answer;
    }

    public static int check(String[] place, int i, int j){
        int[] mx1 = {0,1,0,-1};
        int[] my1 = {-1,0,1,0};
        for(int m = 0; m < 4; m++){
            int mi = i + mx1[m];
            int mj = j + my1[m];

            if(mi < 0 || mi >= place.length || mj < 0 || mj >= place.length) continue;
            if(place[mi].charAt(mj) == 'P') return 0;
        }

        int[] mx2 = {-1, 1, -1, 1};
        int[] my2 = {-1, -1, 1, 1};
        for(int m = 0; m < 4; m++){
            int mi = i + mx2[m];
            int mj = j + my2[m];

            if(mi < 0 || mi >= place.length || mj < 0 || mj >= place.length) continue;
            if(place[mi].charAt(mj) == 'P'){
                if(place[i].charAt(mj) == 'O' || place[mi].charAt(j) == 'O') return 0;
            }
        }

        int[] mx3 = {0, 2, 0, -2};
        int[] my3 = {-2, 0, 2, 0};
        for(int m = 0; m < 4; m++){
            int mi = i + mx3[m];
            int mj = j + my3[m];

            if(mi < 0 || mi >= place.length || mj < 0 || mj >= place.length) continue;
            if(place[mi].charAt(mj) == 'P'){
                if(place[i + mx3[m]/2].charAt(j + my3[m]/2) == 'O') return 0;
            }
        }

        return 1;
    }

}
