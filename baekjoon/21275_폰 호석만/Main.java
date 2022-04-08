import java.io.*;
import java.util.*;

public class Main {

    public static HashMap<Character, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        String a = st.nextToken();
        String b = st.nextToken();

        char aMaxChar = '0', bMaxChar = '0';

        for(int i = 0; i < a.length(); i++){
            if(aMaxChar - a.charAt(i) < 0)
                aMaxChar = a.charAt(i);
        }

        for(int i = 0; i < b.length(); i++){
            if(bMaxChar - b.charAt(i) < 0)
                bMaxChar = b.charAt(i);
        }

        map = new HashMap<>();
        for(int i = 0; i <= 9; i++){
            map.put((char)(i+48), i);
        }

        for(int i = 10; i <= 35; i++){
            map.put((char)(i+87), i);
        }

        int aMin = map.get(aMaxChar);  // a 최소 진법
        int bMin = map.get(bMaxChar);  // b 최소 진법

        ArrayList<long[]> list = new ArrayList<>();
        boolean flag = false;

        for(int i = aMin+1; i <= 36; i++){
            long newA = getNum(a, i);
            if(newA >= Long.MAX_VALUE) continue;

            for(int j = bMin+1; j <= 36; j++){
                long newB = getNum(b, j);
                if(newB >= Long.MAX_VALUE) continue;

                if(newA == newB && i != j){
                    long[] arr = {newA, i, j};
                    list.add(arr);

                    if(list.size() >= 2) {
                        flag = true;
                        break;
                    }
                }
            }

            if(flag) break;
        }


        if(flag) System.out.println("Multiple");
        else if(list.size() == 0){
            System.out.println("Impossible");
        }
        else{
            long[] arr = list.get(0);
            System.out.println(arr[0] + " " + arr[1] + " " + arr[2]);
        }
    }

    public static long getNum(String num, int k){
        long ans = 0;
        for(int i = 0; i < num.length(); i++){
            ans += map.get(num.charAt(i)) * Math.pow(k, num.length()-1-i);
        }
        return ans;
    }

}
