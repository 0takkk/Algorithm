package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            String str = br.readLine();

            if(check(str)) System.out.println("YES");
            else System.out.println("NO");
        }

    }

    public static boolean check(String str){
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);

            if(c == '('){
                stack.push(c);
            }
            else{
                if(stack.isEmpty()) return false;
                if(stack.peek() == '(') stack.pop();
                else return false;
            }
        }

        if(stack.isEmpty()) return true;
        else return false;
    }

}
