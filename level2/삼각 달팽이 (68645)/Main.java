package com.company;

public class Main {
    public static void main(String[] args) {
	    System.out.println(solution(4));
    }

    public static int[] solution(int n) {
        int[] answer = {};

        int maxNum = n * (n+1) / 2;
        answer = new int[maxNum];

        int[][] tmp = new int[n][n];
        int num = 1;
        tmp[0][0] = 1;
        int i = 0, j = 0;

        while(num < maxNum){
            while(i+1 < n && tmp[i+1][j] == 0 ){
                tmp[++i][j] = ++num;
            }
            while(j+1 < n && tmp[i][j+1] == 0){
                tmp[i][++j] = ++num;
            }
            while(i-1 > 0 && j-1 > 0 && tmp[i-1][j-1] == 0){
                tmp[--i][--j] = ++num;
            }
        }

        int idx = 0;
        for(int x = 0; x < n; x++){
            for(int y = 0; y <= x; y++){
               answer[idx++] = tmp[x][y];
            }
        }

        return answer;
    }

}
