package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        char[] origin = new char[n];
        Arrays.fill(origin, '?');

        for(int i = 0; i < h; i++){
            String str = br.readLine();

            for(int j = 0; j < n*w; j++){
                char c = str.charAt(j);

                if(c == '?') continue;
                else origin[j/w] = c;
            }
        }


        System.out.println(new String(origin));
    }

}
