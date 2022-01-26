package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int[] alpa;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        while(n-->0){
            String str = br.readLine();

            int size = str.length();
            alpa = new int[26];

            for(int i = 0; i < size; i++){
                alpa[str.charAt(i) - 'a']++;
            }

            dfs(0, size, "");
        }
        System.out.println(sb.toString());
    }

    public static void dfs(int idx, int size, String str){
        if(idx == size){
            sb.append(str + '\n');
            return;
        }

        for(int i = 0; i < 26; i++){
            if(alpa[i] > 0){
                alpa[i]--;
                dfs(idx+1, size, str + ((char)('a' + i)));
                alpa[i]++;
            }
        }

    }

}
