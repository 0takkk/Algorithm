package com.company;
import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static long[] numbers;
    public static long rgcd[], lgcd[];
    public static long num = -1, biggcd = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");

        numbers = new long[n+2];
        rgcd = new long[n+2];
        lgcd = new long[n+2];

        for(int i = 1; i < n+1; i++){
            numbers[i] = Long.parseLong(st.nextToken());
        }

        make_lgcd();
        make_rgcd();

        solution();

        if(num == -1) System.out.println(-1);
        else System.out.println(biggcd + " " + num);

    }

    public static void solution(){
        for(int i = 1; i <= n; i++){
            long tmp = gcd(rgcd[i-1], lgcd[i+1]);

            if(tmp > biggcd && (numbers[i] % tmp != 0)){
                biggcd = tmp;
                num = numbers[i];
            }
        }
    }

    public static long gcd(long a, long b){
        if(b == 0) return a;
        return gcd(b, a%b);
    }

    public static void make_rgcd(){
        rgcd[1] = numbers[1];
        for(int i = 2; i <= n; i++)
            rgcd[i] = gcd(rgcd[i-1], numbers[i]);
    }

    public static void make_lgcd(){
        lgcd[n] = numbers[n];
        for(int i = n-1; i >= 1; i--)
            lgcd[i] = gcd(lgcd[i+1], numbers[i]);
    }

}
