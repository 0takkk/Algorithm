package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static ArrayList<Integer> list = new ArrayList<>();
    public static int[] select;
    public static int n, result;
    public static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        while(t-->0){
            n = Integer.parseInt(br.readLine());

            select = new int[3];
            list.clear();
            flag = false;

            for(int i = 1; (i * (i+1) / 2) <= n; i++){
                list.add((i * (i+1) / 2));
            }

            combi(0, 3, 0);

            if(flag) System.out.println(1);
            else System.out.println(0);
        }

    }

    public static void combi(int idx, int cnt, int start){
        if(idx == cnt){
            result = 0;
            for(int i = 0; i < cnt; i++){
                result += list.get(select[i]);
            }

            if(n == result) flag = true;
            return;
        }

        for(int i = start; i < list.size(); i++){
            select[idx] = i;
            combi(idx+1, cnt, start);
        }
    }

}
