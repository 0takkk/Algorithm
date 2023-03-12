import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        System.out.println(solution(new int[] {1, 2, 1, 3, 1, 4, 1, 2}));
    }

    public static int solution(int[] topping) {
        int answer = 0;
        int size = topping.length;

        int[] left = new int[size];
        int[] right = new int[size];

        HashSet<Integer> leftSet = new HashSet<>();
        HashSet<Integer> rightSet = new HashSet<>();

        for(int i = 0; i < size; i++){
            leftSet.add(topping[i]);
            left[i] = leftSet.size();

            rightSet.add(topping[size-1-i]);
            right[size-1-i] = rightSet.size();
        }

        for(int i = 1; i < size; i++){
            if(left[i-1] == right[i]) answer++;
        }

        return answer;
    }

}
