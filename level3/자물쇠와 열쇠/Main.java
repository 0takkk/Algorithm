package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(new int[][] {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}}, new int[][] {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}));
    }

    public static int n, m, size;
    public static int[][] map;

    public static boolean solution(int[][] key, int[][] lock) {
        boolean answer;

        n = key.length;
        m = lock.length;
        size = m + 2*(n-1);

        map = new int[size][size];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < m; j++){
                map[n-1+i][n-1+j] = lock[i][j];
            }
        }

        answer = match(key);

        return answer;
    }

    public static boolean match(int[][] key){
        int[][] rotatedKey = key;
        int[][] copy;

        for(int i = 0; i <= size-n; i++){
            for(int j = 0; j <= size-n; j++){
                for(int k = 0; k < 4; k++){
                    rotatedKey = rotate(rotatedKey);
                    copy = copyMap();

                    for(int x = 0; x < n; x++){
                        for(int y = 0; y < n; y++){
                            copy[i+x][j+y] += rotatedKey[x][y];
                        }
                    }

                    if(check(copy)) return true;

                }
            }
        }

        return false;
    }

    public static boolean check(int[][] copy){
        for(int i = n-1; i < n+m-1; i++){
            for(int j = n-1; j < n+m-1; j++){
                if(copy[i][j] != 1) return false;
            }
        }
        return true;
    }

    public static int[][] rotate(int[][] key){
        int[][] rotation = new int[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                rotation[j][n-1-i] = key[i][j];
            }
        }

        return rotation;
    }

    public static int[][] copyMap(){
        int[][] copy = new int[size][size];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                copy[i][j] = map[i][j];
            }
        }

        return copy;
    }

}
