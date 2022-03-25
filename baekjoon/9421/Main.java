package com.company;
import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static boolean[] prime;
    public static boolean isPossible;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        prime = new boolean[n+1];

        isPrime();

        for(int i = 1; i <= n; i++){
            if(!prime[i]){
                solution(i);
                if(isPossible) System.out.println(i);

                isPossible = false;
            }
        }

    }

    public static void solution(int num){
        boolean[] visited = new boolean[10000];

        String str = Integer.toString(num);
        int next = 0;
        for(int i = 0; i < str.length(); i++){
            next += Math.pow(str.charAt(i) - '0', 2);
        }

        while(!visited[next]){
            visited[next] = true;

            str = Integer.toString(next);
            next = 0;
            for(int i = 0; i < str.length(); i++){
                next += Math.pow(str.charAt(i) - '0', 2);
            }

            if(next == 1){
                isPossible = true;
                break;
            }
            else{
                isPossible = false;
            }
        }

    }

    public static void isPrime(){
        prime[0] = prime[1] = true;

        for(int i = 2; i <= (int)Math.sqrt(n); i++){
            if(!prime[i]){
                for(int j = i*i; j <= n; j += i)
                    prime[j] = true;
            }
        }
    }
}
