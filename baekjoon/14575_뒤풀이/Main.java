package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int n, t, beer[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        beer = new int[n][2];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            beer[i][0] = Integer.parseInt(st.nextToken());
            beer[i][1] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = t;

        while(left <= right){
            int mid = (left + right) / 2;
            boolean flag = true;
            int minSum = 0, maxSum = 0;

            for(int i = 0; i < n; i++){
                if(beer[i][0] <= mid){
                    minSum += beer[i][0];
                    maxSum += Math.min(mid, beer[i][1]);
                }
                else{
                    flag = false;
                    break;
                }
            }

            if(flag && minSum <= t && maxSum >= t) right = mid-1;
            else left = mid+1;
        }

        System.out.println(left == t+1 ? -1 : left);
    }

}
