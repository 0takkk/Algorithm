package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
	    System.out.println(solution(5, 26));
    }

    public static int solution(int N, int number) {
        int answer = -1;
        HashSet<Integer>[] list = new HashSet[9];

        int nn = N;
        for(int i = 1; i < 9; i++){
            list[i] = new HashSet<>();
            list[i].add(nn);
            nn = nn * 10 + N;
        }

        for(int i = 1; i < 9; i++){
            for(int j = 1; j < i; j++){
                for(int a : list[j]){
                    for(int b : list[i-j]){
                        list[i].add(a + b);
                        list[i].add(a - b);
                        list[i].add(b - a);
                        list[i].add(a * b);
                        if(a != 0) list[i].add(b / a);
                        if(b != 0) list[i].add(a / b);
                    }
                }
            }
        }

        for(int i = 1; i < 9; i++){
            if(list[i].contains(number)){
                answer = i;
                break;
            }
        }

        return answer;
    }


}
