package com.company;
import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static int[][] tree;
    public static long k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        tree = new int[n+1][2];

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            tree[i][0] = left;
            tree[i][1] = right;
        }

        k = Long.parseLong(br.readLine());

        System.out.println(solution());
    }

    public static int solution(){
        int idx = 1;

        while(true){
            if(tree[idx][0] == -1 && tree[idx][1] == -1) break;
            else if(tree[idx][0] == -1) idx = tree[idx][1];
            else if(tree[idx][1] == -1) idx = tree[idx][0];

            else if(k % 2 == 1){
                idx = tree[idx][0];
                k = k / 2 + 1;
            }
            else{
                idx = tree[idx][1];
                k = k / 2;
            }
        }

        return idx;
    }
}
