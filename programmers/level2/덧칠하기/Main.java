import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        System.out.println(solution(8, 4, new int[] {2, 3, 6}));
        System.out.println(solution(5, 4, new int[] {1,4}));
        System.out.println(solution(4, 1, new int[] {1,2,3,4}));
    }

    public static int solution(int n, int m, int[] section) {
        int answer = 1;

        int start = 0;
        int end = 0;

        while(++end < section.length){
            if(section[end] - section[start] >= m){
                answer++;
                start = end;
            }
        }

        return answer;
    }

}
