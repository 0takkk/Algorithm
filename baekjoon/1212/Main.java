package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String n = br.readLine();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n.length(); i++){
            String a = Integer.toBinaryString(n.charAt(i) - '0');
            if(a.length() == 1 && i != 0) a = "00" + a;
            else if(a.length() == 2 && i != 0) a = "0" + a;

            sb.append(a);
        }
        System.out.println(sb);
    }
}
