import java.util.*;

class Solution {
    public int[][] solution(int n) {
        ArrayList<int[]> list = new ArrayList<>();
        hanoi(n, 1, 2, 3, list);
        
        int[][] answer = new int[list.size()][2];
        
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
    
    public void hanoi(int n, int from, int mid, int to, ArrayList<int[]> list){
        if(n == 1){
            list.add(new int[]{from, to});
            return;    
        }
        
        hanoi(n-1, from, to, mid, list);
        list.add(new int[]{from, to});
        hanoi(n-1, mid, from, to, list);
    }
}