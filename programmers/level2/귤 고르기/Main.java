import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(5, new int[] {1, 1, 1, 1, 2, 2, 2, 3}));
    }

    public static int solution(int k, int[] tangerine) {
        int answer = 1;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int t : tangerine) {
            map.put(t, map.getOrDefault(t, 0)+1);
        }

        List<Integer> keySet = new ArrayList<>(map.keySet());
        keySet.sort(((o1, o2) -> map.get(o2).compareTo(map.get(o1))));

        int sum = 0;
        for (Integer key : keySet) {
            sum += map.get(key);
            if(sum >= k) break;
            answer++;
        }

        return answer;
    }

}
