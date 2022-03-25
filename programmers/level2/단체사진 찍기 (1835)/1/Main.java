package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(2, new String[] {"N~F=0", "R~T>2"}));
    }

    public static int answer;
    public static boolean[] visited;
    public static String[] people = {"A", "C", "F", "J", "M", "N", "R", "T"};

    public static int solution(int n, String[] data) {
        visited = new boolean[8];
        answer = 0;
        
        dfs("", data);

        return answer;
    }

    public static void dfs(String lines, String[] data){
        if(lines.length() == 7){
            if(check(lines, data)) answer++;
            return;
        }

        for(int i = 0; i < 8; i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(lines + people[i], data);
                visited[i] = false;
            }
        }
    }

    public static boolean check(String lines, String[] data){
        for(String d : data){
            int idx1 = lines.indexOf(d.charAt(0));
            int idx2 = lines.indexOf(d.charAt(2));
            char op = d.charAt(3);
            int num = d.charAt(4) - '0' + 1;

            if(op == '='){
                if(Math.abs(idx1 - idx2) != num) return false;
            }
            else if(op == '>'){
                if(Math.abs(idx1 - idx2) <= num) return false;
            }
            else if(op == '<'){
                if(Math.abs(idx1 - idx2) >= num) return false;
            }
        }

        return true;
    }

}
