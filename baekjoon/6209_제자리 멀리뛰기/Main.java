package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int d, n, m;
    public static int[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        d = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new int[n+2];
        for(int i = 1; i < n+1; i++){
            list[i] = Integer.parseInt(br.readLine());
        }
        list[n+1] = d;
        Arrays.sort(list);

        int left = 0; int right = d;
        int mid;
        int answer = 0;

        while(left <= right){
            mid = (left + right) / 2;

            int idx = 0;
            int cnt = 0;

            for(int i = 1; i < n+2; i++){
                if(list[i] - list[idx] < mid) cnt++;
                else idx = i;
            }

            if(cnt > m){
                right = mid-1;
            }
            else{
                left = mid+1;
                answer = mid;
            }
        }

        System.out.println(answer);
    }
}
