import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[] pokemon = new String[n+1];
        HashMap<String, Integer> pokemonMap = new HashMap<>();

        for(int i = 1; i <= n; i++) {
            String name = br.readLine();
            pokemon[i] = name;
            pokemonMap.put(name, i);
        }

        StringBuilder sb = new StringBuilder();
        while(m-->0) {
            String input = br.readLine();
            if(isName(input)) {
                sb.append(pokemonMap.get(input));
            }
            else{
                int idx = Integer.parseInt(input);
                sb.append(pokemon[idx]);
            }

            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static boolean isName(String str) {
        char c = str.charAt(0);
        return 'A' <= c && c <= 'Z';
    }

}
