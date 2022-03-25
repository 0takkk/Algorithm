package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String line = br.readLine();
        int answer = 0;
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < line.length(); i++){
            char c = line.charAt(i);

            if(c == '('){
                stack.push(c);
            } else{
                stack.pop();

                if(line.charAt(i-1) == '('){
                    answer += stack.size();
                } else{
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}
