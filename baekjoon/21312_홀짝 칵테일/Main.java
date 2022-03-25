package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int ans1 = 1, ans2 = 1;
        boolean isOdd = false;

        for(int i = 0; i < 3; i++){
            int a = Integer.parseInt(st.nextToken());

            if(a % 2 == 1){
                ans1 *= a;
                isOdd = true;
            }
            ans2 *= a;
        }

        int ans = (isOdd) ? ans1 : ans2;

        System.out.println(ans);
    }

}
