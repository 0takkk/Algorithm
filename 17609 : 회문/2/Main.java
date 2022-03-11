package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static char[] chars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

       int t = Integer.parseInt(br.readLine());
       StringBuilder sb = new StringBuilder();

       while(t-->0){
           chars = br.readLine().toCharArray();

           int left = 0;
           int right = chars.length-1;

           if(check(left, right)){
               sb.append("0\n");
           }
           else{
               if(reCheck(left, right)) sb.append("1\n");
               else sb.append("2\n");
           }
       }

        System.out.println(sb.toString());
    }

    public static boolean check(int left, int right){
        while(left <= right){
            if(chars[left] != chars[right]){
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    public static boolean reCheck(int left, int right){
        while(left <= right){
            if(chars[left] != chars[right]){
                boolean a = check(left+1, right);
                boolean b = check(left, right-1);

                if(!a && !b) return false;
                else return true;
            }

            left++;
            right--;
        }

        return true;
    }

}
