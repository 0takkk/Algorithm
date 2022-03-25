package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] tmp = solution("1111111");
        for (int i : tmp) {
            System.out.print(i + " ");
        }
    }

    public static int[] solution(String s) {
        int[] answer = new int[2];

        while(!s.equals("1")){
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) == '0') answer[1]++;
            }
            s = s.replaceAll("0", "");

            int length = s.length();

            s = Integer.toBinaryString(length);
            answer[0]++;
        }

        return answer;
    }

}
