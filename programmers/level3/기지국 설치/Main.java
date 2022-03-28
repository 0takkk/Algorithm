import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(11, new int[] {4,11}, 1));
        System.out.println(solution(16, new int[] {9}, 2));
    }

    public static int solution(int n, int[] stations, int w) {
        int answer = 0;

        int start = 1;
        int end = n;
        int cover = 1 + 2 * w;

        for (int station : stations) {
            end = station - w - 1;
            int term = end - start + 1;

            int r = term % cover;
            answer += term / cover;
            if(r > 0) answer += 1;

            start = station + w + 1;
        }

        if(start <= n){
            int term = n - start + 1;
            int r = term % cover;
            answer += term / cover;
            if(r > 0) answer += 1;
        }

        return answer;
    }
}
