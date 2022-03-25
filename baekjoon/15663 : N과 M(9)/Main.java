package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int n, m, max, arr[];
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] input = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            input[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(input);
        max = input[n-1];

        arr = new int[max+1];
        for(int i = 0; i < n; i++){
            arr[input[i]]++;
        }

        dfs(0, "");

        System.out.println(sb.toString());
    }

    public static void dfs(int cnt, String str){
        if(cnt == m){
            str = str.substring(1);
            sb.append(str + '\n');
            return;
        }

        for(int i = 0; i < max+1; i++){
            if(arr[i] > 0){
                arr[i]--;
                dfs(cnt+1, str + " " + i);
                arr[i]++;
            }
        }

    }
}
