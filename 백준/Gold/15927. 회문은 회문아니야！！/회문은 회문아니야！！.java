import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = br.readLine();
        boolean flag = false;
        int len = str.length();

        if(str.charAt(0) != str.charAt(len-1) || !isPalindrome(str)) {
            System.out.println(len);
            return;
        }

        for(int i = 0; i < len/2; i++) {
            if(str.charAt(i) != str.charAt(i+1)) {
                flag = true;
                break;
            }
        }

        if(flag) System.out.println(len-1);
        else System.out.println(-1);
    }

    public static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length()-1;
        while(left < right) {
            if(str.charAt(left) != str.charAt(right)) return false;
            left++;
            right--;
        }

        return true;
    }
}
