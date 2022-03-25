package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-->0){
            String keylogger = br.readLine();

            sb.append(find(keylogger) + "\n");
        }

        System.out.println(sb.toString());
    }

    public static String find(String str){
        LinkedList<Character> password = new LinkedList<>();

        int idx = 0;
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);

            if(('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9')){
                password.add(idx++, c);
            }
            else if(c == '<' && idx != 0){
                idx--;
            }
            else if(c == '>' && idx <= password.size()-1){
                idx++;
            }
            else if(c == '-' && idx != 0){
                password.remove(--idx);
            }
        }

        StringBuilder sb = new StringBuilder();
        password.stream().forEach(sb::append);
        return sb.toString();
    }


}
