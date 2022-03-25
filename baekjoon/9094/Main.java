package com.company;
import java.io.*;
import java.util.*;

public class Main {
    public static int t, n, m;
    public static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < t; tc++){
            st = new StringTokenizer(br.readLine(), " ");

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            result = 0;

            solution();
            System.out.println(result);
        }

    }

    public static void solution(){
        for(int a = 1; a < n-1; a++){
            for(int b = a+1; b < n; b++){
                int tmp = a * a + b * b + m;

                if(tmp % (a*b) == 0)
                    result++;
            }
        }
    }


}
