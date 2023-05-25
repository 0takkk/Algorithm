import java.util.*;

class Solution {
    public static int[][] sisos = {{4, 3}, {3, 2}, {2, 1}};

    public static long solution(int[] weights) {
        long answer = 0;

        long[] arr = new long[1001];

        for (int weight : weights) {
            arr[weight]++;
        }

        for(int i = 100; i <= 1000; i++){
            if(arr[i] == 0) continue;

            answer += (arr[i] * (arr[i]-1)) / 2;

            for (int[] siso : sisos) {
                int a = siso[0];
                int b = siso[1];

                if(a * i / b > 1000) break;
                if(i % b == 0){
                    int weight = i / b * a;

                    answer += (arr[i] * arr[weight]);
                }
            }
        }

        return answer;
    }
}