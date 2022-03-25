package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-->0){
            String str = br.readLine();

            HashMap<Character, Integer> map = new HashMap<>();

            str.chars()
                    .mapToObj(c -> (char) c)
                    .filter(c -> c != ' ')
                    .forEach(c -> map.put(c, map.getOrDefault(c, 0)+1));

            int maxCount = 0;
            int num = 0;
            char maxChar = ' ';

            for (Character c : map.keySet()) {
                int cnt = map.get(c);

                if(cnt > maxCount){
                    num = 1;
                    maxCount = cnt;
                    maxChar = c;
                }
                else if(cnt == maxCount){
                    num++;
                }
            }

            if(num > 1) sb.append("?\n");
            else sb.append(maxChar + "\n");
        }

        System.out.println(sb.toString());
    }

}
