class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        long powD = (long) d * d;
        
        for(int a = 0; a <= d; a += k) {
            long powA = (long) a * a;
            long maxB = (long) Math.sqrt(powD - powA);
            answer += (maxB / k + 1); 
        }
        
        return answer;
    }
}