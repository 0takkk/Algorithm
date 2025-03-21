import java.util.*;

class Solution {
    
    public int n, max = Integer.MIN_VALUE;
    public boolean[] selected;
    public int[] answer;
    public int[][] dices;
    
    public int[] solution(int[][] dice) {
        n = dice.length;
        selected = new boolean[n];
        answer = new int[n/2];
        dices = dice;
        
        select(0, 0);
        
        return answer;
    }
    
    public void select(int idx, int cnt) {
        if(cnt == n/2) {
            play();
            return;
        }
        
        for(int i = idx; i < n; i++) {
            if(!selected[i]){
                selected[i] = true;
                select(i+1, cnt+1);
                selected[i] = false;
            }
        }
    }
    
    public void play() {
        List<Integer> A = makeA();
        List<Integer> B = makeB();
        
        int win = 0;
        for(int a : A) {
            win += binary(B, a);
        }
        
        if(win > max) {
            max = win;
            int idx = 0;
            for(int i = 0; i < n; i++) {
                if(selected[i]) {
                    answer[idx++] = i+1;
                }
            }
        }
        return;
    }
    
    public int binary(List<Integer> B, int num) {
        int left = 0;
        int right = B.size() - 1;
        
        while(left <= right) {
            int mid = (left + right) / 2;
            
            if(B.get(mid) < num) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        
        return left;
    }
    
    public List<Integer> makeA() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        
        for(int i = 0; i < n; i++) {
            if(selected[i]) {
                ArrayList<Integer> tmp = new ArrayList<>();
                for(int n1 : dices[i]) {
                    for(int n2 : list) {
                        tmp.add(n1 + n2);
                    }
                }
                list = tmp;
            }
        }
        
        Collections.sort(list);
        return list;
    }
    
    public List<Integer> makeB() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        
        for(int i = 0; i < n; i++) {
            if(!selected[i]) {
                ArrayList<Integer> tmp = new ArrayList<>();
                for(int n1 : dices[i]) {
                    for(int n2 : list) {
                        tmp.add(n1 + n2);
                    }
                }
                list = tmp;
            }
        }
        
        Collections.sort(list);
        return list;
    }
}