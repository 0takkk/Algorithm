import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        char[] ch = br.readLine().toCharArray();

        ArrayList<Integer> lk = new ArrayList<>();
        ArrayList<Integer> rk = new ArrayList<>();

        int kCnt = 0;
        for(int i = 0; i < ch.length; i++){
            if(ch[i] == 'K') kCnt++;
            else lk.add(kCnt);
        }

        kCnt = 0;
        for(int i = ch.length-1; i >= 0; i--){
            if(ch[i] == 'K') kCnt++;
            else rk.add(kCnt);
        }

        rk.sort(Comparator.reverseOrder());

        int left = 0;
        int right = rk.size()-1;
        int max = 0;

        while(left <= right){
            max = Math.max(max, (right - left + 1) + (2 * Math.min(lk.get(left), rk.get(right))));

            if(lk.get(left) < rk.get(right)) left++;
            else right--;
        }

        System.out.println(max);
    }

}
