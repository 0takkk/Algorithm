package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        HashMap<Integer, Integer> parent;
        int idx = 0;

        while(true){
            parent = new HashMap<>();
            int count  = 0;

            st = new StringTokenizer(br.readLine());

            while(true){
                if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if(a == 0 && b == 0) break;
                if(a < 0 && b < 0) return;

                parent.put(a, parent.getOrDefault(a, 0));
                parent.put(b, parent.getOrDefault(b, 0) + 1);
                count++;
            }

            int root = 0;
            boolean flag = true;

            for (Integer key : parent.keySet()) {
                if(parent.get(key) == 0) root++;
                else if(parent.get(key) > 1){
                    flag = false;
                    break;
                }

                if(root > 1){
                    flag = false;
                    break;
                }
            }

            idx++;

            if(parent.size() == 0 || (flag && root == 1 && count == parent.size() - 1))
                System.out.println("Case " + idx + " is a tree.");
            else{
                System.out.println("Case " + idx + " is not a tree.");
            }
        }

    }

}
