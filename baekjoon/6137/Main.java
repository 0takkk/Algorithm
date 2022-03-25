package com.company;
import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static char[] list;
    public static ArrayList<Character> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        list = new char[n];
        for(int i = 0; i < n; i++){
            list[i] = br.readLine().charAt(0);
        }

        solve();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < result.size(); i++){
            if(i != 0 && i % 80 == 0) sb.append("\n");
            sb.append(result.get(i));
        }

        System.out.println(sb.toString());
    }

    public static void solve(){
        int start = 0;
        int end = n-1;

        while(start <= end){
            if(list[start] < list[end]){
                result.add(list[start++]);
            }
            else if(list[start] > list[end]){
                result.add(list[end--]);
            }
            else{
                int tmpS = start+1;
                int tmpE = end-1;
                int size = result.size();

                while(tmpS <= tmpE){
                    if(list[tmpS] < list[tmpE]){
                        result.add(list[start++]);
                        break;
                    }
                    else if(list[tmpS] > list[tmpE]){
                        result.add(list[end--]);
                        break;
                    }
                    else{
                        tmpS++;
                        tmpE--;
                    }
                }

                if(size == result.size()) result.add(list[start++]);
            }
        }

    }

}
