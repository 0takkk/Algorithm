import java.io.*;
import java.util.*;

public class Main {

    public static int n, c, arr[];
    public static ArrayList<Integer> aSum, bSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        aSum = new ArrayList<>();
        bSum = new ArrayList<>();

        aList(0, 0);
        bList(n/2, 0);
        Collections.sort(bSum);

        int ans = 0;
        for (int target : aSum) {
            int result = binarySearch(target);
            ans += result;
        }

        System.out.println(ans);
    }

    public static int binarySearch(int target){
        int l = 0;
        int r = bSum.size()-1;
        int result = -1;

        while(l <= r){
            int mid = (l + r) / 2;

            if(bSum.get(mid) + target <= c){
                result = mid;
                l = mid+1;
            }
            else{
                r = mid-1;
            }
        }

        return result+1;
    }

    public static void aList(int i , int sum){
        if(sum > c) return;

        if(i == n/2){
            aSum.add(sum);
            return;
        }

        aList(i+1, sum);
        aList(i+1, sum + arr[i]);
    }

    public static void bList(int i , int sum){
        if(sum > c) return;

        if(i == n){
            bSum.add(sum);
            return;
        }

        bList(i+1, sum);
        bList(i+1, sum + arr[i]);
    }

}
