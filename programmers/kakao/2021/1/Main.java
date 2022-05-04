import java.util.*;

public class Main {

    public static void main(String[] args) {
        //System.out.println(solution(new String[] {"muzi", "frodo", "apeach", "neo"}, new String[] {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"}, 2 ));
        //System.out.println(solution(new String[] {"con", "ryan"}, new String[] {"ryan con", "ryan con", "ryan con", "ryan con"}, 3 ));

        for(int i : solution(new String[] {"muzi", "frodo", "apeach", "neo"}, new String[] {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"}, 2 ))
            System.out.print(i + " ");
        System.out.println();

        for(int i : solution(new String[] {"con", "ryan"}, new String[] {"ryan con", "ryan con", "ryan con", "ryan con"}, 3 ))
            System.out.print(i + " ");
    }

    public static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        HashSet<String> report_set = new HashSet<>();

        for(String re : report){
            report_set.add(re);
        }

        HashMap<String, Integer> reported_map = new HashMap<>();

        for(String set : report_set){
            String[] str = set.split(" ");
            String user_id = str[0];
            String report_id = str[1];

            reported_map.put(report_id, reported_map.getOrDefault(report_id, 0) + 1);
        }

        ArrayList<String> stoped_id = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : reported_map.entrySet()){
            if(entry.getValue() >= k){
                stoped_id.add(entry.getKey());
            }
        }

        HashMap<String, Integer> map = new HashMap<>();
        for(String set : report_set){
            String[] str = set.split(" ");
            String user_id = str[0];
            String report_id = str[1];

            if(stoped_id.contains(report_id))
                map.put(user_id, map.getOrDefault(user_id, 0) + 1);
        }

        for(int i = 0; i < id_list.length; i++){
            if(map.containsKey(id_list[i]))
                answer[i] = map.get(id_list[i]);
        }
        return answer;
    }

}
