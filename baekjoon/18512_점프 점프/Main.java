package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int pa = Integer.parseInt(st.nextToken());
        int pb = Integer.parseInt(st.nextToken());

        while(true){
            if(pa > 10000 || pb > 10000){
                System.out.println(-1);
                break;
            }

            if(pa == pb){
                System.out.println(pa);
                break;
            }
            else if(pa > pb) pb += b;
            else pa += a;
        }

    }

}
