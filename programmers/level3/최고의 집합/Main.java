import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] tmp = solution(2, 9);
        for (int i : tmp) {
            System.out.print(i + " ");
        }
    }

    public static int[] solution(int n, int s) {
        int[] answer = new int[n];

        if(n > s) return new int[] {-1};

        for(int i = 0; i < n; i++){
            answer[i] = s / n;
        }

        for(int i = 0; i < s % n; i++){
            answer[i] += 1;
        }

        Arrays.sort(answer);

        return answer;
    }

}
