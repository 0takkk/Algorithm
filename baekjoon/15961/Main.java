package com.company;
import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static int n, d, k, c;
    public static int[] dish;
    public static int max = Integer.MIN_VALUE;
    public static int[] sushi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        dish = new int[n];
        for(int i = 0; i < n; i++)
            dish[i] = Integer.parseInt(br.readLine());

        sushi = new int[d+1];

        solution();

        System.out.println(max);

    }

    public static void solution(){
        int cnt = 0;

        for(int i = 0; i < k; i++){
            if(sushi[dish[i]]++ == 0)
                cnt++;
        }

        max = cnt;

        for(int i = 1; i < n; i++){
            if(max <= cnt){
                if(sushi[c] == 0)
                    max = cnt+1;
                else
                    max = cnt;
            }

            sushi[dish[i-1]]--;
            if(sushi[dish[i-1]] == 0) cnt--;

            if(sushi[dish[(i + k - 1) % n]] == 0) cnt++;
            sushi[dish[(i + k - 1) % n]]++;
        }

    }


}
