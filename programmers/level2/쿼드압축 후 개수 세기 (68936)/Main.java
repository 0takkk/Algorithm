package com.company;

public class Main {
    public static void main(String[] args) {
        int[] tmp = solution(new int[][] {{1,1,0,0},{1,0,0,0},{1,0,0,1},{1,1,1,1}});
        for (int i : tmp) {
            System.out.print(i + " ");
        }
    }

    public static int[] answer;

    public static int[] solution(int[][] arr) {
        answer = new int[2];

        div(0, 0, arr.length, arr);

        return answer;
    }

    public static void div(int x, int y, int size, int[][] arr){
        if(check(x, y, size, arr)){
            answer[arr[x][y]]++;
            return;
        }
        else{
            int ns = size/2;

            div(x, y, ns, arr);
            div(x, y+ns, ns, arr);
            div(x+ns, y, ns, arr);
            div(x+ns, y+ns, ns, arr);
        }
    }

    public static boolean check(int x, int y, int size, int[][] arr){
        for(int i = x; i < x+size; i++){
            for(int j = y; j < y+size; j++){
                if(arr[i][j] != arr[x][y]) return false;
            }
        }
        return true;
    }

}
