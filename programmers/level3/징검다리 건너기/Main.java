import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(new int[] {2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3));
    }

    public static int solution(int[] stones, int k) {
        int answer = 0;

        int min = 0;
        int max = 200000000;

        while(min <= max){
            int mid = (min + max) / 2;

            if(check(mid, stones, k)){
                min = mid+1;
                answer = Math.max(answer, mid);
            }
            else{
                max = mid-1;
            }
        }

        return answer;
    }

    public static boolean check(int mid, int[] stones, int k){
        int cnt = 0;

        for (int stone : stones) {
            if(stone - mid < 0) cnt++;
            else cnt = 0;

            if(cnt >= k) return false;
        }

        return true;
    }

}
