import java.io.*;
import java.util.*;

public class Main {

    public static char[] ch;
    public static int left, right;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(n-->0){
            ch = br.readLine().toCharArray();

            if(check(0, ch.length-1)){
                sb.append("0\n");
            }else{
                if(reCheck()) sb.append("1\n");
                else sb.append("2\n");
            }
        }

        System.out.println(sb.toString());
    }

    public static boolean check(int l, int r){
        left = l;
        right = r;

        while(left <= right){
            if(ch[left] != ch[right]) return false;

            left++;
            right--;
        }

        return true;
    }

    public static boolean reCheck(){
        int l = left;
        int r = right;

        boolean a = check(l+1, r);
        boolean b = check(l, r-1);

        if(!a && !b) return false;
        return true;
    }

}
