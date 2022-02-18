package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] tmp = solution(4, 7, 14);
        for (int i : tmp) {
            System.out.print(i + " ");
        }
    }

    public static int[] solution(int n, long left, long right) {
        List<Long> list = new ArrayList<>();

        for(long i = left; i <= right; i++){
            list.add(Math.max(i/n, i%n) + 1);
        }

        return list.stream().mapToInt(Long::intValue).toArray();
    }

}
