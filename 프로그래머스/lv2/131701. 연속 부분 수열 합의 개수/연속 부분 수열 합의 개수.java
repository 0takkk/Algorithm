import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int n = elements.length;
        int size = 2*n-1;

        int[] arr = new int[size];
        for(int i = 0; i < n; i++) {
            arr[i] = elements[i];
        }

        for(int i = n; i < size; i++) {
            arr[i] = elements[i-n];
        }

        HashSet<Integer> set = new HashSet<>();
        int[][] dp = new int[n+1][size];

        for(int i = 0; i < size; i++) {
            dp[1][i] = arr[i];
            set.add(dp[1][i]);
        }

        for(int i = 2; i <= n; i++) {
            for(int j = i-1; j < size; j++) {
                dp[i][j] = dp[i-1][j-1] + arr[j];
                set.add(dp[i][j]);
            }
        }

        return set.size();
    }
}