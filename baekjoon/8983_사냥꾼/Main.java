package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static class Pos{
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int[] zone;
    public static ArrayList<Pos> animals;
    public static int L, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        zone = new int[m+1];
        st = new StringTokenizer(br.readLine());
        zone[m] = Integer.MAX_VALUE;
        for(int i = 0; i < m; i++)
            zone[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(zone);

        animals = new ArrayList<>();

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(y > L) continue;

            animals.add(new Pos(x, y));
        }

        find();

        System.out.println(result);
    }

    public static void find(){

        for (Pos animal : animals) {
            int x = animal.x;
            int y = animal.y;

            if(y == L){
                int idx = binary(x);
                if(zone[idx] == x) result++;
            }
            else{
                int leftNum = Math.max(0, x - (L - y));
                int rightNum = x + (L - y);

                int leftIdx = lowerBound(leftNum);
                int rightIdx = upperBound(rightNum);

                if(rightIdx - leftIdx != 0) result++;
            }
        }
    }

    public static int binary(int num){
        int left = 0;
        int right = zone.length-1;

        while(left <= right){
            int mid = (left + right) / 2;

            if(zone[mid] == num) return mid;
            else if(zone[mid] < num) left = mid+1;
            else right = mid-1;
        }

        return left;
    }

    public static int lowerBound(int num){
        int left = 0;
        int right = zone.length-1;

        while(left < right){
            int mid = (left + right) / 2;

            if(zone[mid] < num) left = mid+1;
            else right = mid;
        }

        return left;
    }

    public static int upperBound(int num){
        int left = 0;
        int right = zone.length-1;

        while(left < right){
            int mid = (left + right) / 2;

            if(zone[mid] <= num) left = mid+1;
            else right = mid;
        }

        return left;
    }

}
