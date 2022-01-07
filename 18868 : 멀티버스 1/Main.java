package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] space = new int[m][n];
        HashMap<String, Integer> map = new HashMap<>();

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++){
                space[i][j] = Integer.parseInt(st.nextToken());
            }

            StringBuilder sb = new StringBuilder();

            for(int j = 0; j < n-1; j++){
                for(int k = j+1; k < n; k++){
                    space[i][k] -= space[i][j];

                    if(space[i][k] == 0) sb.append("0");
                    else if(space[i][k] > 0) sb.append("+");
                    else sb.append("-");
                }
            }

            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
        }

        int count = 0;

        for (String s : map.keySet()) {
            int cnt = map.get(s);
            if(cnt > 1){
                count += ((cnt * (cnt - 1)) / 2);
            }
        }

        System.out.println(count);
    }

}
