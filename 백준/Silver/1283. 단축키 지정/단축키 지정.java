import java.io.*;
import java.util.*;

public class Main {

    public static HashSet<Character> set;
    public static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        set = new HashSet<>();
        sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        String[] arr = new String[n];

        for(int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        for (String s : arr) {
            find(s);
        }

        System.out.println(sb.toString());
    }

    public static void find(String str) {
        String[] arr = str.split(" ");

        StringBuilder sub = new StringBuilder();
        for(int i = 0; i < arr.length; i++) {
            if(!set.contains(arr[i].charAt(0))) {
                set.add(Character.toUpperCase(arr[i].charAt(0)));
                set.add(Character.toLowerCase(arr[i].charAt(0)));

                for(int j = 0; j < i; j++) {
                    sub.append(arr[j]).append(" ");
                }

                sub.append("[").append(arr[i].charAt(0)).append("]").append(arr[i].substring(1)).append(" ");

                for(int j = i+1; j < arr.length; j++) {
                    sub.append(arr[j]).append(" ");
                }

                sb.append(sub.toString()).append("\n");
                return;
            }
        }

        for(int i = 0; i < arr.length; i++) {
            String s = arr[i];
            for(int j = 1; j < s.length(); j++) {
                if(!set.contains(s.charAt(j))) {
                    set.add(Character.toUpperCase(s.charAt(j)));
                    set.add(Character.toLowerCase(s.charAt(j)));

                    for(int k = 0; k < i; k++) {
                        sub.append(arr[k]).append(" ");
                    }

                    sub.append(s.substring(0, j)).append("[").append(s.charAt(j)).append("]").append(s.substring(j+1)).append(" ");

                    for(int k = i+1; k < arr.length; k++) {
                        sub.append(arr[k]).append(" ");
                    }

                    sb.append(sub.toString()).append("\n");
                    return;
                }
            }
        }

        sb.append(str).append("\n");
    }

}
