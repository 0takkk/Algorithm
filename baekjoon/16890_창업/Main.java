package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        char[] name1 = br.readLine().toCharArray();
        char[] name2 = br.readLine().toCharArray();

        int size = name1.length;

        Arrays.sort(name1);
        Arrays.sort(name2);

        Deque<Character> d1 = new ArrayDeque<>();
        Deque<Character> d2 = new ArrayDeque<>();

        for(int i = 0; i < (int)Math.ceil(size/(double)2); i++) d1.add(name1[i]);
        for(int i = size-1; i >= (int)Math.ceil(size/(double)2); i--) d2.add(name2[i]);

        StringBuilder front = new StringBuilder();
        StringBuilder back = new StringBuilder();

        for(int i = 0; i < size; i++){
            if(i % 2 == 0){
                if(d2.isEmpty() || d1.peekFirst() < d2.peekFirst()) front.append(d1.pollFirst());
                else back.append(d1.pollLast());
            }
            else{
                if(d1.isEmpty() || d1.peekFirst() < d2.peekFirst()) front.append(d2.pollFirst());
                else back.append(d2.pollLast());
            }
        }

        front.append(back.reverse());

        System.out.println(front.toString());
    }

}
