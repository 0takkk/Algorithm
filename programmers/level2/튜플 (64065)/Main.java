package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        for(int i : solution("{{2},{2,1},{2,1,3},{2,1,3,4}}"))
            System.out.print(i + " ");
        System.out.println();

        for(int i : solution("{{1,2,3},{2,1},{1,2,4,3},{2}}"))
            System.out.print(i + " ");
        System.out.println();

        for(int i : solution("{{20,111},{111}}"))
            System.out.print(i + " ");
        System.out.println();

        for(int i : solution("{{123}}"))
            System.out.print(i + " ");
    }

    public static int[] solution(String s) {
        int[] answer = {};

        HashSet<String> set = new HashSet<>();
        s = s.substring(2, s.length()-2).replace("},{", " ");

        String[] arr = s.split(" ");
        Arrays.sort(arr, new Comparator<String>(){
            public int compare(String s1, String s2){
                return s1.length() - s2.length();
            }
        });
        answer = new int[arr.length];

        int idx = 0;
        for(String s1 : arr){
            for(String s2 : s1.split(",")){
                if(set.add(s2)) answer[idx++] = Integer.parseInt(s2);
            }
        }

        return answer;
    }
}
