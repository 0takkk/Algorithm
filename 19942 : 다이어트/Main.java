package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int n , nutrient[], food[][];
    public static int minCost = Integer.MAX_VALUE;
    public static int[] select;
    public static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        nutrient = new int[4];
        food = new int[n+1][5];

        for(int i = 0; i < nutrient.length; i++)
            nutrient[i] = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < food[i].length; j++){
                food[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i <= n; i++){
            select = new int[n];
            dfs(0, i, 1);
        }

        if(minCost == Integer.MAX_VALUE) System.out.println(-1);
        else{
            Collections.sort(list);
            System.out.println(minCost);
            for(int i = 0; i < list.size(); i++)
                System.out.println(list.get(i));
        }

    }

    public static void dfs(int idx, int cnt, int start){
        if(idx == cnt){
            check(cnt);
            return;
        }

        for(int i = start; i <= n; i++){
            select[idx] = i;
            dfs(idx+1, cnt, i+1);
        }
    }

    public static void check(int cnt){
        int[] sumNutrient = new int[4];
        int price = 0;

        for(int i = 0; i < cnt; i++){
            for(int j = 0; j < 4; j++){
                sumNutrient[j] += food[select[i]][j];
            }
            price += food[select[i]][4];
        }

        for(int i = 0; i < 4; i++){
            if(nutrient[i] > sumNutrient[i]) return;
        }

        if(minCost >= price){
            if(minCost > price) list.clear();

            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < cnt; i++){
                sb.append(select[i] + " ");
            }

            list.add(sb.toString());
            minCost = price;
        }
    }

}
