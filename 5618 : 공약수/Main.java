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

        for(int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 1; i <= arr[0]; i++){
            boolean flag = true;
            for(int j = 0; j < n; j++){
                if(arr[j] % i != 0){
                    flag = false;
                    break;
                }
            }

            if(flag) list.add(i);
        }

        list.stream().forEach(System.out::println);
    }

}
