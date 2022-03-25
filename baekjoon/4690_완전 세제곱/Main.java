package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        for(int a = 2; a <= 100; a++){
            for(int b = 2; b <= a; b++){
                for(int c = b; c <= a; c++){
                    for(int d = c; d <= a; d++){
                        if(Math.pow(a, 3) == (Math.pow(b, 3) + Math.pow(c, 3) + Math.pow(d, 3))){
                            sb.append("Cube = " + a + ", Triple = (" + b + "," + c + "," + d + ")\n");
                        }
                    }
                }
            }
        }

        System.out.println(sb.toString());
    }

}
