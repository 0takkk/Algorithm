package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());


        while(t-->0){
            String keylogger = br.readLine();
            find(keylogger);
        }

        System.out.println(sb.toString());
    }

    public static void find(String str){
        Stack<Character> stack1 = new Stack();
        Stack<Character> stack2 = new Stack();

        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);

            if(c != '<' && c != '>' && c != '-'){
                stack1.push(c);
            }
            else if(c == '<' && !stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            else if(c == '>' && !stack2.isEmpty()){
                stack1.push(stack2.pop());
            }
            else if(c == '-' && !stack1.isEmpty()){
                stack1.pop();
            }
        }

        while(!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }

        for(int i = 0; i < stack1.size(); i++){
            sb.append(stack1.elementAt(i));
        }
        sb.append("\n");
    }

}
