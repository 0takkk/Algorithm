package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int n, min_nutri[], food[][], selected[];
    public static int minPrice = Integer.MAX_VALUE;
    public static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        min_nutri = new int[4];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++)
            min_nutri[i] = Integer.parseInt(st.nextToken());

        food = new int[n+1][5];
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 5; j++){
                food[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i <= n; i++){
            selected = new int[n];
            dfs(0, i, 1);
        }

        if(minPrice == Integer.MAX_VALUE) System.out.println(-1);
        else{
            Collections.sort(list);
            System.out.println(minPrice);
            System.out.println(list.get(0));
        }

    }

    public static void dfs(int idx, int num, int start){
        if(idx == num){
            check(num);
            return;
        }

        for(int i = start; i <= n; i++){
            selected[idx] = i;
            dfs(idx+1, num, i+1);
        }

    }

    public static void check(int num){
        int sum_nutri[] = new int[4];
        int price = 0;

        for(int i = 0; i < num; i++){
            for(int j = 0; j < 4; j++){
                sum_nutri[j] += food[selected[i]][j];
            }
            price += food[selected[i]][4];
        }

        for(int i = 0; i < 4; i++){
            if(min_nutri[i] > sum_nutri[i]) return;
        }

        if(price <= minPrice){
            if(price < minPrice) list.clear();

            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < num; i++){
                sb.append(selected[i] + " ");
            }

            list.add(sb.toString());
            minPrice = price;
        }
    }

}
