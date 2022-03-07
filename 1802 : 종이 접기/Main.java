package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-->0){
            String str = br.readLine();
            int size = str.length();

            int[] arr = new int[size];

            for(int i = 0; i < size; i++) {
                arr[i] = Integer.parseInt(str.charAt(i)+"");
            }

            if(check(arr, 0, size-1)) sb.append("YES\n");
            else sb.append("NO\n");
        }

        System.out.println(sb.toString());
    }

    public static boolean check(int[] arr, int start, int end){
        if(start >= end) return true;

        int left = start;
        int right = end;

        while(left < right){
            if(arr[left++] + arr[right--] != 1) return false;
        }

        if(!check(arr, start, right-1)) return false;
        if(!check(arr, left+1, end)) return false;

        return true;
    }

}
