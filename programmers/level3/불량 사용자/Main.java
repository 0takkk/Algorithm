package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "abc1**"}));
        System.out.println(solution(new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"*rodo", "*rodo", "******"}));
        System.out.println(solution(new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "*rodo", "******", "******"}));
    }

    public static HashSet<HashSet<String>> results;
    public static HashMap<String, ArrayList<String>> map;

    public static int solution(String[] user_id, String[] banned_id) {
        int answer = 0;

        map = new HashMap<>();

        for(int i = 0; i < banned_id.length; i++){
            String bannedId = banned_id[i];

            if(map.containsKey(bannedId)) continue;

            for(int j = 0; j < user_id.length; j++){
                String userId = user_id[j];

                if(bannedId.length() == userId.length()){
                    boolean isSame = true;

                    for(int k = 0; k < bannedId.length(); k++){
                        if (bannedId.charAt(k) != '*' && bannedId.charAt(k) != userId.charAt(k)) {
                            isSame = false;
                            break;
                        }
                    }

                    if(isSame) map.computeIfAbsent(bannedId, id -> new ArrayList<>()).add(userId);
                }
            }
        }

        results = new HashSet<>();
        dfs(0, banned_id.length, new HashSet<>(), banned_id);
        answer = results.size();

        return answer;
    }

    public static void dfs(int idx, int cnt, HashSet<String> set, String[] banned_id){
        if(idx == cnt){
            HashSet<String> tmp = new HashSet<>();
            for (String id : set) {
                tmp.add(id);
            }

            results.add(tmp);
            return;
        }

        for (String userId : map.get(banned_id[idx])) {
            if(!set.contains(userId)){
                set.add(userId);
                dfs(idx+1, cnt, set, banned_id);
                set.remove(userId);
            }
        }
    }

}
