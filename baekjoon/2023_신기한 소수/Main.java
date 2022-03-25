package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        dfs(0, n, "");

        System.out.println(sb.toString());
    }

    public static void dfs(int idx, int n, String num){
        if(idx == n){
            sb.append(num+"\n");
            return;
        }

        for(int i = 1; i <= 9; i++){
            if(isPrime(Integer.parseInt(num+i)))
                dfs(idx+1, n, num+i);
        }
    }

    public static boolean isPrime(int num){
        if(num == 1) return false;

        for(int i = 2; i <= (int)Math.sqrt(num); i++){
            if(num % i == 0 )return false;
        }
        return true;
    }
}
