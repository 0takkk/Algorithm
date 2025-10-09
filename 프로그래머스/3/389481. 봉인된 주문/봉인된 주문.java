import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        String answer = "";
        
        Arrays.sort(bans, (a, b) -> {
            if(a.length() == b.length()) {
                return a.compareTo(b);
            }
            return a.length() - b.length();
        });
        
        for(String ban : bans) {
            long number = 0;
        
            for(int i = ban.length()-1; i >= 0; i--) {
                char c = ban.charAt(i);

                number += (alpaToNum(c) * (long) Math.pow(26, ban.length()-i-1));
            }
            
            if(n >= number) {
                n++;
            } else {
                break;
            }
        }
        
        return numToOrder(n);
    }
    
    public int alpaToNum(char c) {
        return (int) c - 'a' + 1;
    }
    
    public String numToOrder(long n) {
        StringBuilder sb = new StringBuilder();
        
        while(n > 0) {
            int r = (int) (n % 26);
        
            if(r == 0) {
                r = 26;
                n = n / 26 - 1;
            } else {
                n = n / 26;
            }
            
            sb.append(numToAlpa(r));
        }
        
        return sb.reverse().toString();
    }
    
    public char numToAlpa(int num) {
        return (char) ('a' + num-1);
    }
}