class Solution {
    public int solution(int n, long l, long r) {
        return count(n, l, r, 1);
    }
    
    public int count(int n, long l, long r, long idx) {
        if(n == 0) return 1;
        
        int num = 0;
        long part = (long) Math.pow(5, n-1);
        for(int i = 0; i < 5; i++) {
            if(i == 2 || (idx + part * (i+1) - 1) < l || r < (idx+part*i)) continue;
            num += count(n-1, l, r, idx+part*i);
        }
        
        return num;
    }
}