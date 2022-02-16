package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int min = Integer.MAX_VALUE;

        for(int i = 0; i < n-3; i++){
            for(int j = i+3; j < n; j++){
                int left = i+1;
                int right = j-1;

                while(left < right){
                    int tmp = arr[left] + arr[right] - arr[i] - arr[j];

                    if(min > Math.abs(tmp)) min = Math.abs(tmp);

                    if(tmp > 0) right -= 1;
                    else left += 1;
                }

            }
        }

        System.out.println(min);
    }
}
