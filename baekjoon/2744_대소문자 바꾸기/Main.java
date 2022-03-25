package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        char[] input = br.readLine().toCharArray();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < input.length; i++){
            char c = input[i];

            if(c >= 'a' && c <= 'z') c = (char)(c - 32);
            else c = (char)(c + 32);

            sb.append(c);
        }

        System.out.println(sb.toString());
        /**
         * for(int i = 0; i < input.length; i++){
         *             char c = input[i];
         *
         *             if(c >= 'a' && c <= 'z'){
         *                 input[i] = (char)(c - 32);
         *             }
         *             else{
         *                 input[i] = (char)(c + 32);
         *             }
         *         }
         *
         *         System.out.println(new String(input));
         */
    }

}
