import java.util.*;

class Solution {
     public static int[][] sisos = {{4, 3}, {3, 2}, {2, 1}};

    public static long solution(int[] weights) {
        long answer = 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();

        for (int weight : weights) {
            map.put(weight, map.getOrDefault(weight, 0) + 1);
            set.add(weight);
        }

        boolean[] visited = new boolean[1001];

        for (int weight : weights) {
            if(visited[weight]) continue;
            visited[weight] = true;

            if(map.get(weight) > 1){
                int cnt = map.get(weight);
                answer += (((long) cnt * (cnt-1)) / 2);
            }

            for (int[] siso : sisos) {
                int a = siso[0];
                int b = siso[1];

                if(weight % b == 0){
                    int num = weight / b * a;

                    if(set.contains(num)){
                        answer += ((long) map.get(weight) * map.get(num));
                    }
                }
            }
        }

        return answer;
    }
}
