import java.util.*;

class Solution {
    public static int[] solution(long begin, long end) {
        int[] answer = new int[(int)(end - begin) + 1];

        for(int i = (int)begin, idx = 0; i <= end; i++){
            answer[idx++] = maxDiv(i);
        }

        return answer;
    }

    public static int maxDiv(int n){
        if(n == 1) return 0;

        ArrayList<Integer> divs = new ArrayList<>();

        for(int i = 2; i <= Math.sqrt(n); i++){
            if(n % i == 0){
                divs.add(i);
                if(n / i <= 10000000) return n/i;
            }
        }

        if(!divs.isEmpty()) return divs.get(divs.size()-1);

        return 1;
    }
}