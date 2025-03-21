import java.util.*;

class Solution {
    
    public int n;
    public ArrayList<Integer> has = new ArrayList<>();
    public ArrayList<Integer> trash = new ArrayList<>();
    
    public int solution(int coin, int[] cards) {
        int answer = 1;
        n = cards.length;
        
        int idx = 0;
        while(idx < n/3) {
            has.add(cards[idx++]);    
        }
        
        while(idx < n) {
            trash.add(cards[idx++]);
            trash.add(cards[idx++]);
            
            if(isPossibleMake()) {
                answer++;
            } else if(coin > 0 && isPossibleMakeOneCoin()) {
                answer++;
                coin--;
            } else if(coin > 1 && isPossibleMakeTwoCoin()) {
                answer++;
                coin-=2;
            } else {
                break;
            }
        }
        
        return answer;
    }
    
    public boolean isPossibleMake() {
        for(int i = 0; i < has.size()-1; i++) {
            for(int j = i+1; j < has.size(); j++) {
                if(has.get(i) + has.get(j) == n+1) {
                    has.remove(j);
                    has.remove(i);
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean isPossibleMakeOneCoin() {
        for(int i = 0; i < has.size(); i++) {
            for(int j = 0; j < trash.size(); j++) {
                if(has.get(i) + trash.get(j) == n+1) {
                    trash.remove(j);
                    has.remove(i);
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean isPossibleMakeTwoCoin() {
        for(int i = 0; i < trash.size()-1; i++) {
            for(int j = i+1; j < trash.size(); j++) {
                if(trash.get(i) + trash.get(j) == n+1) {
                    trash.remove(j);
                    trash.remove(i);
                    return true;
                }
            }
        }
        
        return false;
    }
}