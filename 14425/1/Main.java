package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashMap<Character, ArrayList<String>> map = new HashMap<>();

        for(int i = 0; i < n; i++){
            String str = br.readLine();
            char c = str.charAt(0);

            if(map.containsKey(c)){
                map.get(c).add(str);
            }else{
                ArrayList<String> tmp = new ArrayList<>();
                tmp.add(str);
                map.put(c, tmp);
            }
        }

        int count = 0;

        for(int i = 0; i < m; i++){
            String str = br.readLine();
            char c = str.charAt(0);

            if(map.containsKey(c)){
                if(map.get(c).contains(str)) count++;
            }
        }

        System.out.println(count);
    }

}
