package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution("1231234", 3));
    }

    public static String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();

        int idx = 0;
        for(int i = 0; i < number.length() - k; i++){
            int max = 0;
            for(int j = idx; j <= k+i; j++){
                int num = number.charAt(j) - '0';
                if(max < num){
                    max = num;
                    idx = j + 1;
                }
            }
            sb.append(max);
        }

        return sb.toString();
    }

}
