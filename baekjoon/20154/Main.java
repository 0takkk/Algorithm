package com.company;
import java.io.*;
import java.util.*;

public class Main {
    public static int[] score = {3, 2, 1, 2, 3, 3, 3, 3, 1, 1, 3, 1, 3, 3, 1, 2, 2, 2, 1, 2, 1,1, 2, 2, 2, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Queue<Integer> q = new LinkedList<>();

        String str = br.readLine();
        for(int i = 0; i < str.length(); i++){
            q.offer(score[str.charAt(i)-'A']);
        }

        while(!q.isEmpty()){
            Queue<Integer> tmp = new LinkedList<>();
            if(q.size() == 1)
                break;

            while(!q.isEmpty()){
                tmp.offer(q.poll());
            }

            while(!tmp.isEmpty()){
                int first = tmp.poll();
                int second = tmp.poll();

                int sum = (first + second) % 10;

                q.offer(sum);

                if(tmp.size() == 1){
                    q.offer(tmp.poll());
                }
            }

        }

        if(q.poll() % 2 == 1) System.out.println("I'm a winner!");
        else System.out.println("You're the winner?");

    }


}
