package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static char[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str;
        StringBuilder sb = new StringBuilder();

        while( (str = br.readLine())!=null ){
            int n = (int)Math.pow(3, Integer.parseInt(str));

            arr = new char[n];
            for(int i = 0; i < n; i++) arr[i] = '-';

            dfs(0, n);

            for(int i = 0; i < n; i++) sb.append(arr[i]);
            sb.append('\n');
        }

        System.out.println(sb.toString());
    }

    public static void dfs(int start, int size){
        if(size == 1) return;

        for(int i = start + size/3; i < start + 2*size/3; i++){
            arr[i] = ' ';
        }

        dfs(start, size/3);
        dfs(start+2*size/3, size/3);
    }
}
