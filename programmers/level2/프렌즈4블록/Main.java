package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        System.out.println(solution(4, 5, new String[] {"CCBDE", "AAADE", "AAABF", "CCBBF"} ));
        System.out.println(solution(6, 6, new String[] {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"} ));
        System.out.println(solution(4,5, new String[] {"AAAAA", "AUUUA", "AUUAA", "AAAAA"}));
    }

    public static HashSet<String> idx_set = new HashSet<>();

    public static int solution(int m, int n, String[] board) {
        int answer = 0;
        String[][] table = new String[m][n];

        for(int i = 0; i < m; i++){
            String[] tmp = board[i].split("");
            for(int j = 0; j < n; j++){
                table[i][j] = tmp[j];
            }
        }
        boolean flags = true;

        while(true) {
            for (int i = 0; i < m-1; i++) {
                for (int j = 0; j < n-1; j++) {
                    String c = table[i][j];
                    if(!c.equals("0"))
                        check(table, i, j, c);
                }
            }
            if(idx_set.isEmpty()) break;

            for(String str : idx_set){
                String[] tmp = str.split(" ");
                int y = Integer.parseInt(tmp[0]);
                int x = Integer.parseInt(tmp[1]);

                table[y][x] = "0";
            }
            idx_set.clear();

            for(int i = m-1; i >= 0; i--){
                for(int j = 0; j < n; j++){
                    if(table[i][j].equals("0")){
                        for(int k = i-1; k >= 0; k--){
                            if(!table[k][j].equals("0")){
                                table[i][j] = table[k][j];
                                table[k][j] = "0";
                                break;
                            }
                        }
                    }
                }
            }
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(table[i][j].equals("0"))
                    answer++;
            }
        }

        return answer;
    }

    public static void check(String[][] table, int i , int j, String c){
        int[] mx = {1, 0, 1};
        int[] my = {0, 1, 1};

        for(int m = 0; m < 3; m++){
            int mi = i + mx[m];
            int mj = j + my[m];

            if(!table[mi][mj].equals(c)) return;
        }

        for(int m = 0; m < 3; m++){
            int mi = i + mx[m];
            int mj = j + my[m];

            idx_set.add(mi + " " + mj);
        }
        idx_set.add(i + " " + j);
    }

}
