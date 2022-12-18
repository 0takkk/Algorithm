import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(25, new int[]{2, 14, 11, 21, 17}, 2));
        System.out.println(solution(16, new int[]{4, 8, 11}, 2));
    }


    public static int solution(int distance, int[] rocks, int n) {
        int size = rocks.length+2;
        int[] arr = new int[size];
        arr[size-1] = distance;

        for(int i = 1; i < size-1; i++){
            arr[i] = rocks[i-1];
        }

        Arrays.sort(arr);

        int ans = 0;
        int left = 1;
        int right = distance;

        while(left <= right){
            int mid = (left + right) / 2;
            int cnt = 0;
            int prev = 0;

            for(int i = 1; i < size; i++){
                if(arr[i] - prev < mid) cnt++;
                else prev = arr[i];
            }

            if(cnt > n) right = mid-1;
            else{
                ans = Math.max(ans, mid);
                left = mid+1;
            }
        }

        return ans;
    }

}
