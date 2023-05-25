import java.util.*;

class Solution {
    public static int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;

        Arrays.sort(data, (a, b) -> {
            if(a[col-1] == b[col-1]){
                return b[0] - a[0];
            }
            return a[col-1] - b[col-1];
        });

        int len = data.length;
        int[] sum = new int[len];

        for(int i = row_begin-1; i < row_end; i++){
            int[] d = data[i];

            for(int j = 0; j < data[i].length; j++){
                sum[i] += (d[j] % (i+1));
            }
        }

        for(int i = row_begin-1; i < row_end; i++){
            answer ^= sum[i];
        }

        return answer;
    }
}