import java.io.*;
import java.util.*;

public class Solution {

    public static int n;
    public static char[] arr;
    public static HashSet<String> set;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            set = new HashSet<>();
            arr = br.readLine().toCharArray();

            for(int i = 0; i < n/4; i++){
                makeNum();
                rotate();
            }

            ArrayList<Long> list = new ArrayList<>();
            for (String s : set) {
                list.add(Long.parseLong(s, 16));
            }

            Collections.sort(list);

            sb.append("#").append(tc).append(" ").append(list.get(set.size()-k)).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void makeNum(){
        int size = n/4;
        for(int i = 0; i < 4; i++){
            StringBuilder num = new StringBuilder();
            for(int j = i*size; j < (i+1) * size; j++){
                num.append(arr[j]);
            }

            set.add(num.toString());
        }
    }

    public static void rotate(){
        char tmp = arr[n-1];
        for(int i = n-1; i >= 1; i--){
            arr[i] = arr[i-1];
        }
        arr[0] = tmp;
    }

}
