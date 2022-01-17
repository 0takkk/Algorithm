package com.company;
import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] abcd = new int[n][4];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++){
                abcd[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] ab = new int[n*n];
        int[] cd = new int[n*n];
        int idx = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                ab[idx] = abcd[i][0] + abcd[j][1];
                cd[idx] = abcd[i][2] + abcd[j][3];
                idx++;
            }
        }

        Arrays.sort(cd);

        long answer = 0;

        for(int num : ab){
            int low = lowerBound(cd, -num);
            int up = upperBound(cd, -num);
            answer += (up - low);
        }

        System.out.println(answer);
    }

    public static int lowerBound(int[] arr, int num){
        int left = 0;
        int right = arr.length;

        while(left < right){
            int mid = (left + right) / 2;

            if(arr[mid] < num){
                left = mid + 1;
            }
            else{
                right = mid;
            }
        }

        return left;
    }

    public static int upperBound(int[] arr, int num) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] <= num) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
