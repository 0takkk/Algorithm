class Solution {
    public int[] solution(int[] sequence, int k) {
        int len = sequence.length;
        int[] answer = {0, len};
        
        int left = 0;
        int right = 0;
        
        int pSum = sequence[0];
        
        while(left < len && right < len) {
            if(pSum == k) {
                if(right - left < answer[1] - answer[0]) {
                    answer[0] = left;
                    answer[1] = right;
                }
            }
            
            if(pSum >= k) {
                pSum -= sequence[left];
                left++;
            }
            else {
                right++;
                if(right < len) pSum += sequence[right];
            }
        }
        
        return answer;
    }
}