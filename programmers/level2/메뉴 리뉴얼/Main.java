import java.util.*;

public class Main {
    public static void main(String[] args) {
//        System.out.println(solution(new int[][]{{1,0,0,3}, {2,0,0,0}, {0,0,0,2}, {3,0,1,0}}, 1, 0));
//        String[] solution = solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[]{2, 3, 4});
        String[] solution = solution(new String[]{"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"}, new int[]{2, 3, 5});
        for (String s : solution) {
            System.out.print(s + " ");
        }
    }

    public static int[][] order;
    public static HashMap<String, Integer> map;

    public static String[] solution(String[] orders, int[] course) {
        order = new int[orders.length][26];

        for(int i = 0; i < orders.length; i++){
            char[] chars = orders[i].toCharArray();
            for (char c : chars) {
                order[i][c - 'A']++;
            }
        }

        ArrayList<String> result = new ArrayList<>();

        for (int count : course) {
            map = new HashMap<>();
            ArrayList<String> tmp = new ArrayList<>();

            for(int i = 0; i < orders.length; i++){
                combi(0, 0, "", count, i);
            }

            int max = 0;
            for (String key : map.keySet()) {
                if(map.get(key) > 1 && map.get(key) >= max){
                    if(map.get(key) == max){
                        tmp.add(key);
                    }else{
                        tmp.clear();
                        tmp.add(key);
                    }

                    max = map.get(key);
                }
            }

            result.addAll(tmp);
        }

        Collections.sort(result);

        String[] answer = new String[result.size()];
        for(int i = 0; i < result.size(); i++){
            answer[i] = result.get(i);
        }

        return answer;
    }

    public static void combi(int idx, int cnt, String str, int max, int orderNum){
        if(cnt == max){
            map.put(str, map.getOrDefault(str, 0) + 1);
            return;
        }

        for(int i = idx; i < 26; i++){
            if(order[orderNum][i] > 0){
                order[orderNum][i]--;
                combi(i+1, cnt+1, str + (char)(i + 'A'), max, orderNum);
                order[orderNum][i]++;
            }
        }
    }

}
