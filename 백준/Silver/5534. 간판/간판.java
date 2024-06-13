import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        String target = br.readLine();

        int ans = 0;
        while(n-->0) {
            String input = br.readLine();
            if(check(input, target)) {
                ans++;
            }
        }

        System.out.println(ans);
    }

    public static boolean check(String input, String target) {
        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == target.charAt(0)) {
                for(int j = i+1; j < input.length(); j++) {
                    if(input.charAt(j) == target.charAt(target.length()-1)) {
                        int avgGap = (j-i) / (target.length()-1);
                        int cnt = 0;
                        while(cnt < target.length()) {
                            if(input.charAt(i + avgGap*cnt) == target.charAt(cnt)) {
                                cnt++;
                            }
                            else break;
                        }
                        if(cnt == target.length()) return true;
                    }
                }
            }
        }

        return false;
    }
}
