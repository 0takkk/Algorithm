package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-->0) {
            char[] chars = br.readLine().toCharArray();

            int result = check(chars);

            if(result >= 2) sb.append(result-1 + "\n");
            else sb.append(result + "\n");
        }

        System.out.println(sb.toString());
    }

    public static int check(char[] chars){
        int left = 0;
        int right = chars.length-1;
        int result = 0;

        while(left <= right){
            if(chars[left] == chars[right]){
                left++;
                right--;
            }
            else{
                result = 1;

                int l = left + 1;
                int r = right;
                while(l <= r){
                    if (chars[l] == chars[r]) {
                        l++;
                        r--;
                    }
                    else {
                        result++;
                        break;
                    }
                }

                l = left;
                r = right - 1;
                while(l <= r){
                    if(chars[l] == chars[r]){
                        l++;
                        r--;
                    }
                    else{
                        result++;
                        break;
                    }
                }

                return result;
            }
        }

        return result;
    }

}
