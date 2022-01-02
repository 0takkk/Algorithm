package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int[] parent, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        while(t-->0) {
            int f = Integer.parseInt(br.readLine());

            parent = new int[f * 2];
            cnt = new int[f * 2];

            for(int i = 0; i < f*2; i++){
                parent[i] = i;
                cnt[i] = 1;
            }

            int idx = 0;
            HashMap<String, Integer> map = new HashMap<>();

            for(int i = 0; i < f; i++){
                st = new StringTokenizer(br.readLine());
                String f1 = st.nextToken();
                String f2 = st.nextToken();

                if(!map.containsKey(f1)) map.put(f1, idx++);
                if(!map.containsKey(f2)) map.put(f2, idx++);

                System.out.println(union(map.get(f1), map.get(f2)));
            }
        }

    }

    public static int find(int x){
        if(x == parent[x]) return x;

        return parent[x] = find(parent[x]);
    }


    public static int union(int x, int y){
        x = find(x);
        y = find(y);

        if(x != y){
            parent[y] = x;
            cnt[x] += cnt[y];
        }

        return cnt[x];
    }

}
