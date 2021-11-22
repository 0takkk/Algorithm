package com.company;
import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static ArrayList<Integer> primeList;
    public static int[] dp;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        primeList = new ArrayList<>();
        makePrime(n);

        dp = new int[n+1];
        dp[0] = 1;
        makeDp();

        System.out.println(dp[n]);
    }

    public static void makeDp(){
        for(int i = 0; i < primeList.size(); i++){
            int tmp = primeList.get(i);

            for(int j = tmp; j <= n; j++){
                dp[j] = (dp[j] + dp[j-tmp]) % 123456789;
            }
        }
    }

    public static void makePrime(int num){
        boolean[] prime = new boolean[n+1];;

        prime[0] = prime[1] = true;

        for(int i = 2; i <= Math.sqrt(num); i++){
            if(prime[i] == true) continue;

            for(int j = i*i; j < prime.length; j = j+i){
                prime[j] = true;
            }
        }

        for(int i = 2; i <= n; i++) {
            if (!prime[i])
                primeList.add(i);
        }

    }

}
