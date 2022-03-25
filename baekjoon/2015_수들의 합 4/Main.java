package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n+1];
        int[] psum = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            psum[i] = psum[i-1] + arr[i];
        }

        long answer = 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 1; i <= n; i++){
            if(psum[i] == k) answer++;

            if(map.containsKey(psum[i]-k))
                answer += map.get(psum[i] - k);

            map.put(psum[i], map.getOrDefault(psum[i], 0) + 1);
        }

        System.out.println(answer);
    }

}
