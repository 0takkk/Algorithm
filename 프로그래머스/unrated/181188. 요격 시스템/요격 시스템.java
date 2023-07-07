import java.util.*;

class Solution {
    public static int solution(int[][] targets) {
        int answer = 0;

        Arrays.sort(targets, Comparator.comparingInt(a -> a[1]));

        int nowEnd = 0;
        for (int[] target : targets) {
            int start = target[0];
            int end = target[1];

            if(start >= nowEnd){
                answer++;
                nowEnd = end;
            }
        }

        return answer;
    }
}