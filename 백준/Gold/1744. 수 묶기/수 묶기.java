import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> plus = new ArrayList<>();
        ArrayList<Integer> minus = new ArrayList<>();
        int zeros = 0;

        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num > 0) {
                plus.add(num);
            }
            else if(num < 0) {
                minus.add(num);
            }
            else {
                zeros++;
            }
        }

        plus.sort(Collections.reverseOrder());
        Collections.sort(minus);

        int ans = 0;
        int idx = 0;

        while(idx < plus.size()) {
            if(idx+1 == plus.size()) {
                ans += plus.get(idx);
            }
            else{
                int sum = plus.get(idx) + plus.get(idx+1);
                int mul = plus.get(idx) * plus.get(idx+1);

                if(mul >= sum) {
                    ans += mul;
                    idx++;
                }
                else {
                    ans += plus.get(idx);
                }
            }
            idx++;
        }

        if(minus.size() % 2 == 0) {
            for(int i = 0; i < minus.size(); i+=2) {
                ans += (minus.get(i) * minus.get(i+1));
            }
        }
        else {
            for(int i = 0; i < minus.size()-1; i+=2) {
                ans += (minus.get(i) * minus.get(i+1));
            }

            if(zeros == 0) ans += minus.get(minus.size()-1);
        }

        System.out.println(ans);
    }

}
