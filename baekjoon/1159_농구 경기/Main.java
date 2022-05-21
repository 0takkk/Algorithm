import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        TreeMap<Character, Integer> map = new TreeMap<>();

        while(n-->0){
            String name = br.readLine();

            char c = name.charAt(0);

            map.put(c, map.getOrDefault(c, 0)+1);
        }

        StringBuilder sb = new StringBuilder();
        for (Character c : map.keySet()) {
            if(map.get(c) >= 5){
                sb.append(c);
            }
        }

        if(sb.toString().equals("")) System.out.println("PREDAJA");
        else System.out.println(sb.toString());
    }

}
