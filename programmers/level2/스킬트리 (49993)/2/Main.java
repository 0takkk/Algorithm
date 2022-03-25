import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for(int i = 0; i < skill_trees.length; i++){
            String str = skill_trees[i];
            
            for(int j = 0; j < skill_trees[i].length(); j++){
                String c = skill_trees[i].substring(j, j+1);
                if(!skill.contains(c)) str = str.replace(c, "");
            }
            
            if(skill.indexOf(str) == 0) answer++;
        }
        
        return answer;
    }
}
