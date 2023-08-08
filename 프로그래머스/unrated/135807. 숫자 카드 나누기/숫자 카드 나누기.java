import java.util.*;

class Solution {
    
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);
        
        answer = Math.max(findNum(arrayA, arrayB), findNum(arrayB, arrayA));
        
        return answer;
    }
    
    public int findNum(int[] divs, int[] noDivs) {
        int min = divs[0];
        ArrayList<Integer> nums = new ArrayList<>();
        
        for(int i = 1; i * i <= min; i++) {
            if(min % i == 0) {
                nums.add(min/i);
                nums.add(i);
            }
        }
        
        Collections.sort(nums, Collections.reverseOrder());
        
        for(int num : nums) {
            if(canDiv(num, divs) && cantDiv(num, noDivs)) {
                return num;
            }
        }
        
        return 0;
    }
    
    public boolean canDiv(int num, int[] divs) {
        for(int div : divs) {
            if(div % num != 0) return false;
        }
        return true;
    }
    
    public boolean cantDiv(int num, int[] noDivs) {
        for(int noDiv : noDivs) {
            if(noDiv % num == 0) return false;
        }
        return true;
    }
    
}