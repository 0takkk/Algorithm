import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        TreeMap<Integer, List<String>> rank = new TreeMap<>();

        while(n-->0){
            st = new StringTokenizer(br.readLine());
            String title = st.nextToken();
            int score = Integer.parseInt(st.nextToken());

            List<String> list = rank.getOrDefault(score, new ArrayList<>());
            list.add(title);

            rank.put(score, list);
        }

        StringBuilder sb = new StringBuilder();
        while(m-->0){
            int score = Integer.parseInt(br.readLine());
            sb.append(rank.get(rank.ceilingKey(score)).get(0)).append("\n");
        }

        System.out.println(sb.toString());
    }

}
