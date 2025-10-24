class Solution {
    
    public int removeZeroCount = 0;
    public int toBinaryCount = 0;
    
    public int[] solution(String s) {
        int[] answer = new int[2];
        
        while(true) {
            int len = removeZero(s);
            s = toBinary(len);
            if(s.equals("1")) {
                break;
            }
        }
        
        answer[0] = toBinaryCount;
        answer[1] = removeZeroCount;
        return answer;
    }
    
    public int removeZero(String s) {
        int zero = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '0') {
                zero++;
            }
        }
        
        removeZeroCount += zero;
        return s.length() - zero;
    }
    
    public String toBinary(int len) {
        toBinaryCount++;
        return Integer.toBinaryString(len);
    }
}