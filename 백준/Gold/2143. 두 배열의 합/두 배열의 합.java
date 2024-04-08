import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        int[] b = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> listA = new ArrayList<>();
        ArrayList<Integer> listB = new ArrayList<>();

        HashMap<Integer, Integer> mapA = new HashMap<>();
        for(int i = 0; i < n; i++) {
            int sum = a[i];
            listA.add(sum);
            mapA.put(sum, mapA.getOrDefault(sum, 0) + 1);
            for(int j = i+1; j < n; j++) {
                sum += a[j];
                listA.add(sum);
                mapA.put(sum, mapA.getOrDefault(sum, 0) + 1);
            }
        }

        HashMap<Integer, Integer> mapB = new HashMap<>();
        for(int i = 0; i < m; i++) {
            int sum = b[i];
            listB.add(sum);
            mapB.put(sum, mapB.getOrDefault(sum, 0) + 1);
            for(int j = i+1; j < m; j++) {
                sum += b[j];
                listB.add(sum);
                mapB.put(sum, mapB.getOrDefault(sum, 0) + 1);
            }
        }

        Collections.sort(listB);

        long ans = 0;
        for (int aPreSum : mapA.keySet()) {
            int target = t - aPreSum;
            int result = binary(target, listB);

            if(result != -1) {
                ans += ((long)mapA.get(aPreSum) * mapB.get(listB.get(result)));
            }
        }

        System.out.println(ans);
    }

    public static int binary(int target, ArrayList<Integer> list) {
        int left = 0;
        int right = list.size()-1;

        while(left <= right) {
            int mid = (left + right) / 2;
            if(list.get(mid) == target) {
                return mid;
            }
            else if(list.get(mid) < target) {
                left = mid+1;
            }
            else {
                right = mid-1;
            }
        }

        return -1;
    }
}