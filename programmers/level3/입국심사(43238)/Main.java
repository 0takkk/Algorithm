package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
	    System.out.println(solution(6, new int[] {7, 10}));
    }

    public static long solution(int n, int[] times) {
        long answer = 0;

        Arrays.sort(times);

        long left = 0;
        long right = (long)n * times[times.length-1];
        long mid, sum;

        while(left <= right){
            mid = (left + right) / 2;   // 심사를 받는데 주어진 시간
            sum = 0;  // 총 심사한 인원

            for(int i = 0; i < times.length; i++){
                sum += mid / times[i];

                if(sum >= n)
                    break;
            }
            if(n > sum){
                left = mid + 1;
            }
            else{
                right = mid - 1;
                answer = mid;
            }
        }
        return answer;
    }
}
