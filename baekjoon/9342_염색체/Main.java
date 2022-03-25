import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(n-->0){
            String str = br.readLine();

            boolean check = false;
            int a = str.indexOf("A");
            int f = str.indexOf("F");
            int c = str.indexOf("C");
            int aLast = str.lastIndexOf("A");
            int fLast = str.lastIndexOf("F");

            if(str.charAt(str.length()-1) == 'A' || str.charAt(str.length()-1) == 'F' || str.charAt(str.length()-1) == 'C'){
                if(a < f && f < c){
                    if(aLast == f-1 && fLast == c-1){
                        check = true;
                    }
                }
            }

            if(check) sb.append("Infected!\n");
            else sb.append("Good\n");
        }

        System.out.println(sb.toString());
    }

}
