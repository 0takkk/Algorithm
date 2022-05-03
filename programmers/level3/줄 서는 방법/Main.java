import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] solution = solution(4, 8);
        for (int i : solution) {
            System.out.print(i + " ");
        }
    }

    public static int[] solution(int n, long k) {
        int[] answer = new int[n];

        long f = 1;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 1; i <= n; i++){
            f *= i;
            list.add(i);
        }

        k--;
        int idx = 0;

        while(n > 0){
            f /= n;
            answer[idx++] = list.get((int)(k / f));
            list.remove((int)(k / f));
            k %= f;
            n--;
        }

        return answer;
    }

}
