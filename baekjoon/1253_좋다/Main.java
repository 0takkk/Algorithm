import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        Arrays.sort(arr);

        long ans = 0;
        for(int i = 0; i < n; i++){
            int l = 0;
            int r = n-1;
            if(i == l) l++;
            if(i == r) r--;

            while(l < r){
                int sum = arr[l] + arr[r];
                if(sum == arr[i]){
                    ans += map.get(arr[i]);

                    int idx = i;
                    while(idx < n && arr[i] == arr[idx] ){
                        idx++;
                    }
                    i = idx-1;

                    break;
                }

                if(sum > arr[i]) {
                    r--;
                    if(r == i) r--;
                }
                else {
                    l++;
                    if(l == i) l++;
                }
            }
        }

        System.out.println(ans);
    }

}
