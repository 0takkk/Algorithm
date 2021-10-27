package com.company;
import java.io.*;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int number;

        for(int i = 0; i < n; i++){
            number = Integer.parseInt(br.readLine());
            dfs(number, 1, 1, 1, 0, "1");
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void dfs(int max, int now, int num, int sign, int sum , String str){
        if(max == now){
            sum = sum + (num*sign);
            if(sum == 0)
                sb.append(str + "\n");
            return;
        }

        dfs(max, now+1, num*10 + now+1, sign, sum , str+ " " + String.valueOf(now+1));  // 공백
        dfs(max, now+1, now+1, 1, sum + (sign*num), str+"+"+String.valueOf(now+1));  // +
        dfs(max, now+1, now+1, -1, sum+(sign*num), str+"-"+String.valueOf(now+1));  // -
    }
}
