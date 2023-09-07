import java.util.*;

class Solution {
    public static int solution(int[] cards) {
        int size = cards.length;
        boolean[] visited = new boolean[size];
        ArrayList<Integer> groupCounts = new ArrayList<>();

        for(int now = 0; now < size; now++) {
            if(!visited[now]) {
                visited[now] = true;
                int count = 1;

                for(int next = cards[now]-1; !visited[next]; next = cards[next]-1) {
                    visited[next] = true;
                    count++;
                }

                groupCounts.add(count);
            }
        }

        if(groupCounts.size() == 1) {
            return 0;
        }

        Collections.sort(groupCounts);
        return groupCounts.get(groupCounts.size()-1) * groupCounts.get(groupCounts.size()-2);
    }
}