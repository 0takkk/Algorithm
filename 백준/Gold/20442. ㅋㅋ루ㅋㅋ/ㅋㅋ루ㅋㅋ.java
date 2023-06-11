import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        char[] ch = br.readLine().toCharArray();

        ArrayList<Integer> lK = new ArrayList<>();
        ArrayList<Integer> rK = new ArrayList<>();

        int kCnt = 0;
        for(int i = 0; i < ch.length; i++){
            if(ch[i] == 'K') kCnt++;
            else lK.add(kCnt);
        }

        kCnt = 0;
        for(int i = ch.length-1; i >= 0; i--){
            if(ch[i] == 'K') kCnt++;
            else rK.add(kCnt);
        }

        rK.sort(Comparator.reverseOrder());

        int left = 0;
        int right = rK.size()-1;
        int max = 0;

        while(left <= right){
            max = Math.max(max, (right - left + 1) + (2 * Math.min(lK.get(left), rK.get(right))));

            if(lK.get(left) < rK.get(right)) left++;
            else right--;
        }

        System.out.println(max);
    }

}
